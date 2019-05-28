package top.ucat.boots.example.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.ucat.starter.mybatis.plugs.common.dto.AbstractBaseEntity;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_test")
public class SysTest extends AbstractBaseEntity implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
}
