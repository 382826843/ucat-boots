package top.ucat.boots.starter.oauth2.client.config;

import com.alibaba.fastjson.JSONObject;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang3.StringUtils;
import top.ucat.boots.common.exception.BaseException;
import top.ucat.boots.common.result.Result;
import top.ucat.boots.starter.oauth2.client.beans.oauth.FeginExceptionBo;

import static feign.FeignException.errorStatus;


public class FeginErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
//        Response ReturnResponse = null;
        try {
            Response.Body body = response.body();
            if (body != null) {
                String bodyStr = Util.toString(body.asReader());
                FeginExceptionBo bo = JSONObject.parseObject(bodyStr, FeginExceptionBo.class);
                if (StringUtils.isEmpty(bo.getMessage())) {
                    Result result = JSONObject.parseObject(bodyStr, Result.class);
                    return new BaseException(response.status(), result.getMsg(), methodKey);
                }
//                ReturnResponse = response.toBuilder().build();
                return new BaseException(response.status(), bo.getMessage(), methodKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorStatus(methodKey, response);
    }
}