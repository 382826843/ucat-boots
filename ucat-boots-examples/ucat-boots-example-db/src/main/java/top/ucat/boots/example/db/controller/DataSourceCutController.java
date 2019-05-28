package top.ucat.boots.example.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ucat.boots.starter.db.annotation.Db;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cut")
public class DataSourceCutController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/test1")
    public Object testDb() {
        //默认数据源为default
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from sys_test");
        return maps;
    }

    @GetMapping("/test2")
    @Db("test2")
    public Object testDb1() {
        List<Map<String, Object>> maps1 = jdbcTemplate.queryForList("select * from sys_test");
        return maps1;
    }
}
