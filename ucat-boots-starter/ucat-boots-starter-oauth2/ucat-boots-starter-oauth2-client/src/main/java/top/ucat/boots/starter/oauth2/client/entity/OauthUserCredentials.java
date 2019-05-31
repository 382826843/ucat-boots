package top.ucat.boots.starter.oauth2.client.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Jun
 * @since 2019-04-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OauthUserCredentials implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String userCode;
    private String userCodeType;
    private String systemType;

    private Date createtime;
    private Date updatetime;

}
