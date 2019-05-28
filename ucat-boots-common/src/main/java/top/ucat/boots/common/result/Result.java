package top.ucat.boots.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result<T> implements Serializable {
    private Integer status;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errMsg;
    private T data;
}
