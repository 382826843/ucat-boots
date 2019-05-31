package top.ucat.boots.starter.oauth2.server.service.api;


import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthToken;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthTokenDto;

public interface OauthService {

    /**
     * 获取Accesstoken
     *
     * @param dto
     * @return
     */
    OauthToken getAccessToken(OauthTokenDto dto);



}
