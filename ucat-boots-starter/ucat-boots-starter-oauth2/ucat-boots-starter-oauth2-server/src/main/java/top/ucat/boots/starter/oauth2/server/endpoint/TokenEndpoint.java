package top.ucat.boots.starter.oauth2.server.endpoint;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenEndpoint {

    @PostMapping("/oauth/token")
    public void postToken() {

    }

}
