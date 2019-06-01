package top.ucat.boots.starter.oauth2.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ucat.boots.common.utils.PermitAllUrl;

public class LoginConfig implements WebMvcConfigurer {
    @Value("${ucat.oauth.permitUrl:''}")
    private String[] permitUrl;


    // TODO: 2019/6/1 待完善从动态配置中获取参数
    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    public String getPermitUrl() {
        return configurableApplicationContext.getEnvironment().getProperty("ucat.oauth.permitUrl");
    }

    @Autowired
    SystemLoginHandlerInterceptor systemLoginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(systemLoginHandlerInterceptor);
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns(PermitAllUrl.permitAllUrl(permitUrl));

    }
}
