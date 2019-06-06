package top.ucat.boots.starter.oauth2.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.ucat.boots.common.exception.BaseException;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthRedisKey;
import top.ucat.boots.starter.oauth2.client.entity.OauthUserCredentials;
import top.ucat.boots.starter.oauth2.server.dao.OauthUserCredentialsDao;
import top.ucat.boots.starter.oauth2.client.service.api.UserCredentialsService;
import top.ucat.boots.starter.oauth2.server.utils.KeysUtil;
import top.ucat.starter.redis.service.RedisService;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OauthUserCredentialsDao oauthUserCredentialsDao;

    @Autowired
    private RedisService redisService;

    @Override
    public OauthUserCredentials getUserCredentials(String userCode, String userCodeType, String systemType) {
        String key = KeysUtil.getUserSystemKey(userCode, userCodeType, systemType);
        OauthUserCredentials userCredentials = (OauthUserCredentials) redisService.getHashObj(OauthRedisKey.OAUTH_USER_CREDENTIALS, key, () -> {
            OauthUserCredentials credentials = oauthUserCredentialsDao.getOauthUserCredential(userCode, userCodeType, systemType);
            credentials = credentials == null ? new OauthUserCredentials() : credentials;
            return credentials;
        }, 28800);
        return StringUtils.isEmpty(userCredentials.getId()) ? null : userCredentials;
    }


    @Override
    public OauthUserCredentials saveUserCredentials(String userId, String userCode, String userCodeType, String systemType) {

        OauthUserCredentials credential = oauthUserCredentialsDao.getOauthUserCredential(userCode, userCodeType, systemType);
        if (credential == null) {
            credential = new OauthUserCredentials();
            credential.setUserId(userId);
            credential.setUserCode(userCode);
            credential.setUserCodeType(userCodeType);
            credential.setSystemType(systemType);
            int i = oauthUserCredentialsDao.saveOauthUserCredential(credential);
            if (i > 0) {
                redisTemplate.boundHashOps(OauthRedisKey.OAUTH_USER_CREDENTIALS).delete(KeysUtil.getUserSystemKey(userCode, userCodeType, systemType));
                return credential;
            }
        } else {
            throw new BaseException("创建失败,凭证已存在", 500);
        }
        throw new BaseException("创建凭证失败", 500);
    }
}
