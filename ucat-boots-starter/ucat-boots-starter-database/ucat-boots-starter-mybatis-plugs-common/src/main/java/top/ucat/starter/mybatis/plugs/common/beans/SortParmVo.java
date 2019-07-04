package top.ucat.starter.mybatis.plugs.common.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Jun
 * @Date: 2019/7/3 13:57
 * @Description:
 */
@Data
public class SortParmVo implements Serializable {
    private String prop;
    private String order;
}
