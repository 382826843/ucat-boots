package top.ucat.boots.starter.oauth2.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.ucat.boots.common.exception.BaseException;
import top.ucat.boots.starter.oauth2.client.beans.oauth.Oauth2AccessToken;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthRedisKey;
import top.ucat.boots.starter.oauth2.client.beans.oauth.SystemUser;
import top.ucat.boots.starter.oauth2.client.beans.oauth.UserThreadLocal;
import top.ucat.boots.starter.oauth2.client.service.api.SystemUserService;
import top.ucat.boots.starter.oauth2.client.service.api.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class SystemLoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SystemUserService systemUserService;

    //前置方法
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


        StringBuffer requestURL = httpServletRequest.getRequestURL();

        //清空上一次链接时的user信息
        UserThreadLocal.set(null);

        String accessToken = this.getAuthToken(httpServletRequest);
        if (StringUtils.isEmpty(accessToken)) {
            throw new BaseException("用户凭证失效,请登录", 401);
        }

        SystemUser user = systemUserService.getSystemUserByToken(accessToken);
        if (user == null) {
            throw new BaseException("用户凭证超时,请登录", 401);
        }
        UserThreadLocal.set(user);
//        //redis中信息续费,八个小时
        redisTemplate.opsForValue().set(OauthRedisKey.accessToken + "_" + accessToken, user, 28000);
        return true;
    }



    public String getAuthToken(HttpServletRequest request) {

        //url上的access_token
        String accessToken = request.getParameter(Oauth2AccessToken.ACCESS_TOKEN);
        if (!StringUtils.isEmpty(accessToken)) {
            return accessToken;
        }
        // 头部的Authorization值以Bearer开头
        accessToken = request.getHeader("Authorization");
        if ((!StringUtils.isEmpty(accessToken)) && accessToken.startsWith(Oauth2AccessToken.BEARER_TYPE)) {
            //Bearer 一共7个字符
            accessToken = accessToken.substring(7);
            return accessToken;
        }
        return null;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
