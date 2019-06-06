package top.ucat.boots.starter.oauth2.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.ucat.boots.common.exception.BaseException;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthRedisKey;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthTokenDto;
import top.ucat.boots.starter.oauth2.client.entity.OauthClientDetails;
import top.ucat.boots.starter.oauth2.server.dao.OauthClientDetailsDao;
import top.ucat.boots.starter.oauth2.client.service.api.ClientService;
import top.ucat.starter.redis.service.RedisService;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private OauthClientDetailsDao oauthClientDetailsDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;


    @Override
    public OauthClientDetails getClient(OauthTokenDto dto) {

        if (StringUtils.isEmpty(dto.getClientId())) {
            //clientId为空
            throw new BaseException("客户端id不能为空", 401);
        }
        if (StringUtils.isEmpty(dto.getClientSecret())) {
            //clientSecret为空
            throw new BaseException("客户端秘钥不能为空", 401);
        }

        //获取客户端凭证
        OauthClientDetails clientDetails = (OauthClientDetails) redisService.getHashObj(OauthRedisKey.OAUTH_CLIENT_DETAIL, dto.getClientId(), () -> {
            OauthClientDetails detail = oauthClientDetailsDao.getOauthClientDetailByClientId(dto.getClientId());
            detail = detail == null ? new OauthClientDetails() : detail;
            return detail;
        });

        if (!StringUtils.isEmpty(clientDetails.getClientId())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(dto.getClientSecret(), clientDetails.getClientSecret())) {
                //当前client存在
                return clientDetails;
            } else {
                throw new BaseException("客户端秘钥不正确", 401);
            }
        }
        throw new BaseException("客户端不存在", 401);
    }
}
