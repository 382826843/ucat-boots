package top.ucat.boots.starter.oauth2.client.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * oauth2的client表
 * </p>
 *
 * @author Jun
 * @since 2019-04-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientId;
    private String clientName;
    //密码
    private String clientSecret;
    private String systemType;
    private String resourceIds;
    private String scope;
    //支持的授权方式
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    //access_token有效期（单位秒）
    private Integer accessTokenValidity;
    //refresh_token有效期（单位秒）
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
    private Date createtime;
    private Date updatetime;
}
