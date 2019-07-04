package top.ucat.boots.common.result;

import java.util.function.Supplier;

public enum SystemResult {
    OK(200, "调用成功"),
    CREATED(201, "新建成功"),
    BAD_REQUEST(400, "调用失败"),
    UNAUTHORIZED(401, "用户失效"),
    FORBIDDEN(403, "用户无权限访问"),
    ERROR(500, "系统内部错误");

    SystemResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private Integer status;
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result getResult(Integer status, String msg, String errMsg, Object o) {
        return new Result(status, msg, errMsg, o);
    }

    public Result getResult(String msg, String errMsg, Object o) {
        return new Result(this.getStatus(), msg, errMsg, o);
    }

    public Result getResult(Integer status, String msg) {
        return this.getResult(status, msg, null, null);
    }

    public Result getResult(String msg) {
        return this.getResult(this.getStatus(), msg, null, null);
    }

    public Result getResult(Object data) {
        return this.getResult(this.getStatus(), this.getMsg(), null, data);
    }

    public Result getResult(String errMsg, Object data) {
        return this.getResult(this.getStatus(), this.getMsg(), errMsg, data);
    }

    public Result getResult() {
        return this.getResult(this.getMsg());
    }

    public static Result getSystemResult(Supplier<Result> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            e.printStackTrace();
            return SystemResult.ERROR.getResult(e.getLocalizedMessage(), null);
        }
    }

}
