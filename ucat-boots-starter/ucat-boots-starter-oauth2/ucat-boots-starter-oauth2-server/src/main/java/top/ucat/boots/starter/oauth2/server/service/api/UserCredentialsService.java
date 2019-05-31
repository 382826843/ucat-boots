package top.ucat.boots.starter.oauth2.server.service.api;


import top.ucat.boots.starter.oauth2.client.entity.OauthUserCredentials;

public interface UserCredentialsService {

    /**
     * 获取用户凭证
     *
     * @param userCode
     * @param userCodeType
     * @param systemType
     * @return
     */
    OauthUserCredentials getUserCredentials(String userCode, String userCodeType, String systemType);

    /**
     * 创建用户凭证
     *
     * @param userId
     * @param userCode
     * @param userCodeType
     * @param systemType
     * @return
     */
    OauthUserCredentials saveUserCredentials(String userId, String userCode, String userCodeType, String systemType);
}
