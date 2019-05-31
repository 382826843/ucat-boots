package top.ucat.boots.starter.oauth2.server.service.api;

import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthTokenDto;
import top.ucat.boots.starter.oauth2.client.entity.OauthClientDetails;

public interface ClientService {
    /**
     * 获取当前的client
     *
     * @param dto
     * @return
     */
    OauthClientDetails getClient(OauthTokenDto dto);
}
