package top.ucat.boots.starter.oauth2.client.beans.oauth;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OauthTokenDto implements Serializable {
    @NotBlank(message = "请输入账号")
    private String userCode;

    @NotBlank(message = "请输入密码")
    private String password;

    @NotBlank(message = "请提供授权类型")
    private String grantType;

    @NotBlank(message = "请提供客户端Id")
    private String clientId;

    @NotBlank(message = "请提供客户端秘钥")
    private String clientSecret;

    @NotBlank(message = "请提供系统类别")
    private String systemType;
}
