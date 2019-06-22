package top.ucat.boots.starter.oauth2.client.service.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthRedisKey;
import top.ucat.boots.starter.oauth2.client.beans.oauth.SystemUser;
import top.ucat.boots.starter.oauth2.client.entity.OauthUserCredentials;

public interface UserDetailsService {


    /**
     * 获取系统的用户
     * 用户验证密码
     *
     * @param credential
     * @return
     */
    SystemUser getSystemUser(OauthUserCredentials credential);

    SystemUser getSystemUserByToken(String accessToken);


}
