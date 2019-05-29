package top.ucat.starter.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisReturnService {

    @Autowired
    RedisValueService redisValueService;

    public Object getValue(String key, RedisCallBack callBack) throws Exception {
        Object value = redisValueService.getValue(key);
        if (value == null) {
            value = callBack.callback(key);
        }
        return value;
    }

    public Object getValue(String key, RedisCallBack callBack, boolean isSave) throws Exception {
        Object value = getValue(key, callBack);
        if (isSave) {
            redisValueService.SetValue(key, value);
        }
        return value;
    }

    public Object getValue(String key, RedisCallBack callBack, long time) throws Exception {
        Object value = this.getValue(key, callBack, time, TimeUnit.SECONDS);
        return value;
    }

    public Object getValue(String key, RedisCallBack callBack, long time, TimeUnit unit) throws Exception {
        Object value = getValue(key, callBack);
        redisValueService.SetValue(key, value, time, unit);
        return value;
    }

}
