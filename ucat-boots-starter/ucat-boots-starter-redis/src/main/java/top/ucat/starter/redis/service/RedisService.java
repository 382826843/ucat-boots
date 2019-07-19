package top.ucat.starter.redis.service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public interface RedisService {

    /**
     * 获取redis中的key,当key为空时,调用 生产者方法获取对应值并且保存,默认保存7200秒
     *
     * @param redisKey key
     * @param supplier 生产者方法
     * @return
     */
    Object getObj(String redisKey, Supplier supplier);

    /**
     * 获取redis中的key,当key为空时,调用 生产者方法获取对应值并且保存,默认保存单位为秒
     *
     * @param redisKey key
     * @param supplier 生产者方法
     * @param time     保存时间
     * @return
     */
    Object getObj(String redisKey, Supplier supplier, long time);

    /**
     * 获取redis中的key,当key为空时,调用 生产者方法获取对应值并且保存
     *
     * @param redisKey key
     * @param supplier 生产者方法
     * @param time     保存时间
     * @param unit     保存时间单位
     * @return
     */
    Object getObj(String redisKey, Supplier supplier, long time, TimeUnit unit);

    /**
     * 获取redis中的hashkey,当key为空时,调用 生产者方法获取对应值并且保存
     *
     * @param key1
     * @param key2
     * @param supplier
     * @return
     */
    Object getHashObj(String key1, String key2, Supplier supplier);

    Object getHashObj(String key1, String key2, Supplier supplier, long time);

    Object getHashObj(String key1, String key2, Supplier supplier, long time, TimeUnit unit);


}
