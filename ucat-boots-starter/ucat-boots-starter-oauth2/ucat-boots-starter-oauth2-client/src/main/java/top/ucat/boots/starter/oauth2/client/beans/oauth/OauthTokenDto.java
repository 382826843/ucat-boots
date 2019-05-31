package top.ucat.boots.starter.oauth2.client.beans.oauth;

import lombok.Data;

import java.io.Serializable;

@Data
public class OauthTokenDto implements Serializable {
    private String userCode;
    private String password;
    private String grantType;
    private String clientId;
    private String clientSecret;
    private String systemType;
}
