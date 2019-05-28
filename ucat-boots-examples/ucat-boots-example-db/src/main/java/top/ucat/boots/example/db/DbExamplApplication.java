package top.ucat.boots.example.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.ucat.boots.example.db.mapper")
public class DbExamplApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbExamplApplication.class, args);
    }
}
