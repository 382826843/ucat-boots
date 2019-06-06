package top.ucat.boots.starter.oauth2.server.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.ucat.boots.common.result.Result;
import top.ucat.boots.common.result.SystemResult;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthToken;
import top.ucat.boots.starter.oauth2.client.beans.oauth.OauthTokenDto;
import top.ucat.boots.starter.oauth2.client.service.api.OauthService;

@RestController
public class TokenEndpoint {

    @Autowired
    private OauthService oauthService;

    @PostMapping("/oauth/token")
    public Result postToken(@RequestBody OauthTokenDto dto) {
        OauthToken accessToken = oauthService.getAccessToken(dto);
        return SystemResult.OK.getResult(accessToken);
    }
}
