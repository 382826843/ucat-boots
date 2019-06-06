package top.ucat.boots.starter.oauth2.client.beans.oauth;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FeginExceptionBo implements Serializable {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
