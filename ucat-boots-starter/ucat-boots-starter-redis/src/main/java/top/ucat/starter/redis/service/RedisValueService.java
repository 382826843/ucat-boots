package top.ucat.starter.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisValueService {

    @Autowired
    private RedisTemplate redisTemplate;


    public BoundValueOperations getBoundOps(String key) {
        BoundValueOperations ops = redisTemplate.boundValueOps(key);
        return ops;
    }

    public ValueOperations getOps() {
        ValueOperations ops = redisTemplate.opsForValue();
        return ops;
    }

    public void SetValue(String key, Object value, long time) {
        this.SetValue(key, value, time, TimeUnit.SECONDS);
    }

    public void SetValue(String key, Object value, long time, TimeUnit unit) {
        BoundValueOperations ops = getBoundOps(key);
        ops.set(value, time, unit);
    }

    public void SetValue(String key, Object value) {
        BoundValueOperations ops = getBoundOps(key);
        ops.set(value);
    }

    public Object getValue(String key) {
        BoundValueOperations ops = getBoundOps(key);
        return ops.get();
    }
}
