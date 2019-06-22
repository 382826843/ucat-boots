package top.ucat.boots.example.oauth2.service;

import org.springframework.stereotype.Service;
import top.ucat.boots.starter.oauth2.client.beans.oauth.SystemUser;
import top.ucat.boots.starter.oauth2.client.entity.OauthUserCredentials;
import top.ucat.boots.starter.oauth2.client.service.api.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public SystemUser getSystemUser(OauthUserCredentials credential) {
        SystemUser systemUser = SystemUser.builder().password("$2a$10$uhv.nuclzMZzYOIjT7JypuJnG0NzrgW/2dwTlwN4pvAxGf9qS5cmq").build();
        return systemUser;
    }

    @Override
    public SystemUser getSystemUserByToken(String accessToken) {
        return null;
    }
}
