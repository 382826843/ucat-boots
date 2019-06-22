package top.ucat.boots.starter.oauth2.client.service.api;

import top.ucat.boots.starter.oauth2.client.beans.oauth.SystemUser;

/**
 * @Auther: Jun
 * @Date: 2019/6/22 10:31
 * @Description:
 */
public interface SystemUserService {

    SystemUser getSystemUserByToken(String accessToken);
}
