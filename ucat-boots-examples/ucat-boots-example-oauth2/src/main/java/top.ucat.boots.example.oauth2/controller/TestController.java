package top.ucat.boots.example.oauth2.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestController {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");

        System.out.println(encode );
    }
}
