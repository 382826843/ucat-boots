package top.ucat.boots.starter.oauth2.client.beans.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OauthToken implements Serializable {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private Integer expires_time;
}
