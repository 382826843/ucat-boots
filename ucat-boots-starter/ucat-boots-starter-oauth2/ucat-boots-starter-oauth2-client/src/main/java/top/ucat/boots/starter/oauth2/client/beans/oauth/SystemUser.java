package top.ucat.boots.starter.oauth2.client.beans.oauth;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SystemUser<T> implements Serializable {

    private String userCode;
    private String password;
    private String accessToken;
    private String refreshToken;
    private String systemType;
    private String userCodeType;
    private String userId;
    private Long expires;
    private T systemUser;
    private Map parmMap;

    public SystemUser(String userCode, String password) {
        this.userCode = userCode;
        this.password = password;
    }
}
