package top.ucat.boots.starter.oauth2.server.service.api;

import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthToken;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthTokenDto;
import top.ucat.boots.starter.oauth2.client.beans.oauth.SystemUser;
import top.ucat.boots.starter.oauth2.client.entity.OauthClientDetails;

public interface TokenService {

    /**
     * 通过账号密码的方式获取token
     *
     * @param dto
     * @return
     */
    OauthToken getPasswordAccessToken(OauthTokenDto dto);


    /**
     * 生成登录token
     *
     * @param client
     * @param systemUser
     * @return
     */
    OauthToken createLoginAccessToken(OauthClientDetails client, SystemUser systemUser);
}
