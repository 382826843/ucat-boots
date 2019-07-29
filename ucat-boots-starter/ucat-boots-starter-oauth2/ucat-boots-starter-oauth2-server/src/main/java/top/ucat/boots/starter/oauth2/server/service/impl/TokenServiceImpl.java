package top.ucat.boots.starter.oauth2.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.ucat.boots.common.exception.BaseException;
import top.ucat.boots.starter.oauth2.client.beans.oauth.*;
import top.ucat.boots.starter.oauth2.client.entity.OauthClientDetails;
import top.ucat.boots.starter.oauth2.client.entity.OauthUserCredentials;
import top.ucat.boots.starter.oauth2.client.service.api.ClientService;
import top.ucat.boots.starter.oauth2.client.service.api.TokenService;
import top.ucat.boots.starter.oauth2.client.service.api.UserCredentialsService;
import top.ucat.boots.starter.oauth2.client.service.api.UserDetailsService;
import top.ucat.boots.starter.oauth2.server.utils.KeysUtil;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
            if (systemUser == null) {
                throw new BaseException("找不到用户 ", 401);
            }

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
        this.saveLoginToken(systemUser, clientDetails);
        OauthToken oauthToken = new OauthToken(accessToken, TokenTypeEnum.broker.name(), refreshToken, clientDetails.getScope(), clientDetails.getAccessTokenValidity());
        return oauthToken;
    }


    /**
     * 保存登录的etoken
     *
     * @param systemUser
     */
    private void saveLoginToken(SystemUser systemUser, OauthClientDetails clientDetails) {
        ValueOperations valueOperations = this.redisTemplate.opsForValue();
        ValueOperations ops = this.redisTemplate.opsForValue();
        Integer refreshTokenValidity = StringUtils.isEmpty(clientDetails.getRefreshTokenValidity()) ? 30 : clientDetails.getRefreshTokenValidity();
        Integer accessTokenValidity = StringUtils.isEmpty(clientDetails.getAccessTokenValidity()) ? 30 : clientDetails.getAccessTokenValidity();
        ops.set(OauthRedisKey.accessToken + "_" + systemUser.getAccessToken(), systemUser, accessTokenValidity, TimeUnit.SECONDS);
        ops.set(OauthRedisKey.refreshToken + "_" + systemUser.getRefreshToken(), systemUser, refreshTokenValidity, TimeUnit.DAYS);
        ops.set(KeysUtil.getUserSystemKey(systemUser.getUserCode(), systemUser.getUserCodeType(), systemUser.getSystemType()), systemUser);
    }

    /**
     * 删除登录的token
     *
     * @param systemUser
     */
    private void deleteLoginToken(SystemUser systemUser) {
        String userSystemKey = KeysUtil.getUserSystemKey(systemUser.getUserCode(), systemUser.getUserCodeType(), systemUser.getSystemType());
        ValueOperations<String, SystemUser> valueOperations = this.redisTemplate.opsForValue();
        SystemUser oldUser = valueOperations.get(userSystemKey);
        if (oldUser == null) {
            return;
        }
        redisTemplate.delete(userSystemKey);
        redisTemplate.delete(OauthRedisKey.accessToken + "_" + oldUser.getAccessToken());
        redisTemplate.delete(OauthRedisKey.refreshToken + "_" + oldUser.getRefreshToken());
    }


}
