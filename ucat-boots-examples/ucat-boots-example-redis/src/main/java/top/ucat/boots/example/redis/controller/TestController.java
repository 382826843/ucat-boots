package top.ucat.boots.example.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("put")
    public Object put() {
        Map map = new HashMap();
        map.put("1", "a");
        map.put("2", "s");
        map.put("3", "d");
        redisTemplate.opsForHash().put("t", "t1", map);
        return null;
    }

    @RequestMapping("get")
    public Object get() {
        Object o = redisTemplate.opsForHash().get("t", "t1");
        return o;
    }
}
