package top.ucat.boots.starter.oauth2.server.service.api;


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
}
