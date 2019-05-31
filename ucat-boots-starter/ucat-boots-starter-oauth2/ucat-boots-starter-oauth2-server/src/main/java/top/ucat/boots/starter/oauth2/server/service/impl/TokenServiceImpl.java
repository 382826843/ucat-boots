package top.ucat.boots.starter.oauth2.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.ucat.boots.common.exception.BaseException;
import top.ucat.boots.starter.oauth2.client.beans.oauth.*;
import top.ucat.boots.starter.oauth2.client.entity.OauthClientDetails;
import top.ucat.boots.starter.oauth2.client.entity.OauthUserCredentials;
import top.ucat.boots.starter.oauth2.server.service.api.ClientService;
import top.ucat.boots.starter.oauth2.server.service.api.TokenService;
import top.ucat.boots.starter.oauth2.server.service.api.UserCredentialsService;
import top.ucat.boots.starter.oauth2.server.service.api.UserDetailsService;

import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserCredentialsService userCredentialsService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public OauthToken getPasswordAccessToken(OauthTokenDto dto) {
        //1.判断当前client是否有效,无效抛出异常
        //2.寻找当前登录方式的用户凭证
        OauthClientDetails client = this.clientService.getClient(dto);
        if (client != null) {
            OauthUserCredentials userCredential = this.userCredentialsService.getUserCredentials(dto.getUserCode(), UserCodeTypeEnum.userCode.name(), dto.getSystemType());
            if (userCredential == null) {
                throw new BaseException("当前用户不存在", 400);
            }
            SystemUser systemUser = userDetailsService.getSystemUser(userCredential);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(dto.getPassword(), systemUser.getPassword())) {
                throw new BaseException("密码不正确 ", 400);
            }
            systemUser.setUserId(userCredential.getUserId());
            systemUser.setUserCode(dto.getUserCode());
            systemUser.setSystemType(dto.getSystemType());
            systemUser.setUserCodeType(UserCodeTypeEnum.userCode.name());
            //密码正确，生成token
            OauthToken accessToken = tokenService.createLoginAccessToken(client, systemUser);
            return accessToken;
        }
        throw new BaseException("客户端不存在 ", 400);
    }

    @Override
    public OauthToken createLoginAccessToken(OauthClientDetails clientDetails, SystemUser systemUser) {
        String accessToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        systemUser.setAccessToken(accessToken);
        systemUser.setRefreshToken(refreshToken);
        systemUser.setExpires((long) clientDetails.getAccessTokenValidity());
        //删除之前登录的token
        this.deleteLoginToken(systemUser);
        //保存token
        this.saveLoginToken(systemUser);
        OauthToken oauthToken = new OauthToken(accessToken, TokenTypeEnum.broker.name(), refreshToken, clientDetails.getScope(), clientDetails.getAccessTokenValidity());
        return oauthToken;
    }


    /**
     * 保存登录的etoken
     *
     * @param systemUser
     */
    private void saveLoginToken(SystemUser systemUser) {
        HashOperations ops = this.redisTemplate.opsForHash();
        ops.put(OauthRedisKey.accessToken, systemUser.getAccessToken(), systemUser);
        ops.put(OauthRedisKey.refreshToken, systemUser.getRefreshToken(), systemUser);
        ops.put(OauthRedisKey.userList, this.getLoginTokenRedisKey(systemUser.getUserCode(), systemUser.getUserCodeType(), systemUser.getSystemType()), systemUser);
    }

    /**
     * 删除登录的token
     *
     * @param systemUser
     */
    private void deleteLoginToken(SystemUser systemUser) {
        HashOperations<String, String, SystemUser> ops = this.redisTemplate.opsForHash();
        String loginTokenKey = this.getLoginTokenRedisKey(systemUser.getUserCode(), systemUser.getUserCodeType(), systemUser.getSystemType());
        SystemUser oldUser = ops.get(OauthRedisKey.userList, loginTokenKey);
        if (oldUser == null) {
            return;
        }
        ops.delete(OauthRedisKey.userList, loginTokenKey);
        ops.delete(OauthRedisKey.accessToken, oldUser.getAccessToken());
        ops.delete(OauthRedisKey.refreshToken, oldUser.getRefreshToken());

    }


    private String getLoginTokenRedisKey(String userCode, String userCodeType, String systemType) {
        StringBuilder builder = new StringBuilder();
        builder.append("u:").append(userCode).append(",ut:").append(userCodeType).append(",st:").append(systemType);
        return builder.toString();
    }

}
