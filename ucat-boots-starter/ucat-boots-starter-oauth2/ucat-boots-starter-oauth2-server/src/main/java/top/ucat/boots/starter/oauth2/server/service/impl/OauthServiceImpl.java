package top.ucat.boots.starter.oauth2.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.ucat.boots.common.exception.BaseException;
import top.ucat.boots.common.utils.BeanValidator;
import top.ucat.boots.starter.db.annotation.Db;
import top.ucat.boots.starter.oauth2.client.beans.oauth.GrantTypeEnum;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthToken;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthTokenDto;
import top.ucat.boots.starter.oauth2.client.service.api.OauthService;
import top.ucat.boots.starter.oauth2.client.service.api.TokenService;

@Service
@Db
public class OauthServiceImpl implements OauthService {

    @Autowired
    private TokenService tokenService;

    @Override
    public OauthToken getAccessToken(OauthTokenDto dto) {
        String message = BeanValidator.validator(dto);
        if (!StringUtils.isEmpty(message)) {
            throw new BaseException(message, 400);
        }
        if (dto.getGrantType().equals(GrantTypeEnum.password.name())) {
            //password模式登录
            OauthToken passwordAccessToken = this.tokenService.getPasswordAccessToken(dto);
            return passwordAccessToken;
        }
        return null;
    }
}
