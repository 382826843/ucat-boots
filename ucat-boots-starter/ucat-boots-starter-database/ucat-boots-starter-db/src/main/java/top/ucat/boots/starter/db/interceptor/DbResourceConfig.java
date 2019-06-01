package top.ucat.boots.starter.db.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ucat.boots.common.utils.PermitAllUrl;

@Configuration
public class DbResourceConfig implements WebMvcConfigurer {

    @Autowired
    DbResourceHandlerInterceptor dbResourceHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置默认数据源
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(dbResourceHandlerInterceptor);
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns(PermitAllUrl.permitAllUrl());
    }
}
