package top.ucat.starter.redis.service;

import java.util.function.Supplier;

public interface RedisService {


    Object getObj(String redisKey, Supplier supplier, long time);

    Object getObj(String redisKey, Supplier supplier);

    Object getHashObj(String key1, String key2, Supplier supplier, long time);

    Object getHashObj(String key1, String key2, Supplier supplier);
}
