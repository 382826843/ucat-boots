package top.ucat.boots.common.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
    private int status;
    private String modelKey;


    public BaseException() {
    }

    public BaseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseException(String message) {
        super(message);
    }


    public BaseException(int status, String message, String modelKey) {
        super(message);
        this.status = status;
        this.modelKey = modelKey;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, String modelKey, Throwable cause) {
        super(message, cause);
        this.modelKey = modelKey;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}