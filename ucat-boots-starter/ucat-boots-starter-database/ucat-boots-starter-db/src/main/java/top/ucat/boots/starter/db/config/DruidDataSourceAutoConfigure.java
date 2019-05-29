package top.ucat.boots.starter.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidFilterConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidSpringAopConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidStatViewServletConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidWebStatFilterConfiguration;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import top.ucat.boots.starter.db.beans.DataSourceEnum;
import top.ucat.boots.starter.db.beans.DynamicDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnClass({DruidDataSource.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({DruidStatProperties.class, DataSourceProperties.class})
@Import({DruidSpringAopConfiguration.class, DruidStatViewServletConfiguration.class, DruidWebStatFilterConfiguration.class, DruidFilterConfiguration.class})
@Slf4j
@ConfigurationProperties(prefix = "spring.datasource")
@Data
@NoArgsConstructor
public class DruidDataSourceAutoConfigure {

    private Map<String, String> multi;

    @Bean(initMethod = "init")
    public DataSource dataSource() {
        log.info("UCAT Init DruidDataSource");
        return new DruidDataSourceWrapper();
    }


    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource() throws Exception {
        //多数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource dafaultDataSource = this.dataSource();
        Map targetDataSourcesMap = new HashMap();
        targetDataSourcesMap.put(DataSourceEnum.DEFAULT.getKey(), dafaultDataSource);
        //设置多数据源
        this.setMultiDataSource(targetDataSourcesMap);
        dynamicDataSource.setTargetDataSources(targetDataSourcesMap);
        dynamicDataSource.setDefaultTargetDataSource(dafaultDataSource);
        return dynamicDataSource;
    }


    public DruidDataSource setDataSource(String url, String userName, String pwd) {
        DruidDataSource newDataSource = ((DruidDataSourceWrapper) this.dataSource()).cloneDruidDataSource();
        newDataSource.setUrl(url);
        newDataSource.setUsername(userName);
        newDataSource.setPassword(pwd);
        return newDataSource;
    }


    private void setMultiDataSource(Map targetDataSourcesMap) throws Exception {
        if (multi != null && !multi.isEmpty()) {
            //不是空,有多数据源
            Map<String, Map<String, String>> multiMap = new HashMap();
            for (Map.Entry<String, String> entry : multi.entrySet()) {
                String key = entry.getKey();
                String[] keyArr = key.split("\\.");
                if (keyArr.length != 2) {
                    throw new Exception("请按照要求填写多数据源配置");
                }
                Map<String, String> contentMap;
                if (multiMap.containsKey(keyArr[0])) {
                    contentMap = multiMap.get(keyArr[0]);
                } else {
                    contentMap = new HashMap<String, String>();
                    multiMap.put(keyArr[0], contentMap);
                }
                contentMap.put(keyArr[1], entry.getValue());
            }
            if (!multiMap.isEmpty()) {
                multiMap.forEach((dataSourceTitle, valueMap) -> {
                    DataSource dataSource = this.setDataSource(valueMap.get("url"), valueMap.get("username"), valueMap.get("password"));
                    targetDataSourcesMap.put(dataSourceTitle, dataSource);
                });
            }
        }
    }


}