package top.ucat.starter.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Object getObj(String redisKey, Supplier supplier, long time, TimeUnit unit) {
        ValueOperations valueOperations = this.redisTemplate.opsForValue();
        Object o = valueOperations.get(redisKey);
        if (o == null) {
            o = supplier.get();
            valueOperations.set(redisKey, o, time, unit);
        }
        return o;
    }

    @Override
    public Object getObj(String redisKey, Supplier supplier, long time) {
        return this.getObj(redisKey, supplier, time, TimeUnit.SECONDS);
    }

    @Override
    public Object getObj(String redisKey, Supplier supplier) {
        return this.redisService.getObj(redisKey, supplier, 7200);
    }


    @Override
    public Object getHashObj(String key1, String key2, Supplier supplier) {
        return this.redisService.getHashObj(key1, key2, supplier, 7200);
    }

    @Override
    public Object getHashObj(String key1, String key2, Supplier supplier, long time) {
        return this.getHashObj(key1, key2, supplier, time, TimeUnit.SECONDS);
    }

    @Override
    public Object getHashObj(String key1, String key2, Supplier supplier, long time, TimeUnit unit) {
        BoundHashOperations<String, String, Object> ops = redisTemplate.boundHashOps(key1);
        Object o = ops.get(key2);
        if (o == null) {
            o = supplier.get();
            ops.put(key2, o);
            redisTemplate.expire(key1, time, unit);
        }
        return o;
    }
}
