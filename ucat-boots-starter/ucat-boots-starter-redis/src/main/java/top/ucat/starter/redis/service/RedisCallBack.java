package top.ucat.starter.redis.service;

public interface RedisCallBack {
    Object callback(String key) throws Exception;
}
