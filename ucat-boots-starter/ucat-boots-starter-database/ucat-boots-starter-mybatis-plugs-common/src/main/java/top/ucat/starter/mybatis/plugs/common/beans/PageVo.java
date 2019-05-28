package top.ucat.starter.mybatis.plugs.common.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo implements Serializable {
    private Long total;
    private List data;
}
