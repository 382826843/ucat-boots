package top.ucat.boots.starter.mybatis.plugs.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //新增时添加自动填充新增时间和修改时间
        Date date = new Date();
        setFieldValByName("createtime", date, metaObject);
        setFieldValByName("updatetime", date, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //修改时添加自动填充修改时间
//        setFieldValByName("updatetime", new Date(), metaObject);
    }
}
