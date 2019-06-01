package top.ucat.boots.starter.oauth2.client.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.ucat.boots.common.exception.BaseException;
import top.ucat.boots.common.result.Result;
import top.ucat.boots.common.result.SystemResult;

import javax.servlet.http.HttpServletResponse;


@RestControllerAdvice
//@Order(1)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> exceptionHandler(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        Result result = null;
//        if (e instanceof RetryableException) {
        if (false) {
            result = new Result(500, "接口调用超时", e.getLocalizedMessage(), null);
        } else if (e instanceof BaseException) {
            result = new Result(((BaseException) e).getStatus(), e.getLocalizedMessage(), e.getLocalizedMessage(), null);
        } else {
            result = SystemResult.ERROR.getResult(e.getMessage(), null);
        }
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}