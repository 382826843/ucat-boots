package top.ucat.starter.mybatis.plugs.common.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Jun
 * @Date: 2019/6/5 11:01
 * @Description:
 */
@Data
public class BaseControllerPageListBo implements Serializable {
    private Integer page;
    private Integer rows;
    private String like;
    private String equal;
    private String unequal;
}
