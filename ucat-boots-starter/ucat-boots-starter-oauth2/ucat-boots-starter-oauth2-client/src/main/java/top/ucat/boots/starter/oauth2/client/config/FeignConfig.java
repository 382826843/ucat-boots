package top.ucat.boots.starter.oauth2.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.ucat.boots.starter.oauth2.client.beans.oauth.Oauth2AccessToken;

import javax.servlet.http.HttpServletRequest;

/**
 * Feign配置
 * 使用FeignClient进行服务间调用，传递headers信息
 */
@Configuration
public class FeignConfig implements RequestInterceptor {

    @Autowired
    private SystemLoginHandlerInterceptor systemLoginHandlerInterceptor;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //添加token
        String authToken = systemLoginHandlerInterceptor.getAuthToken(request);
        requestTemplate.header("Authorization", Oauth2AccessToken.BEARER_TYPE + " " + authToken);
    }
}