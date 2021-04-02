package com.aceplaying.game.gm_server.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1
 *
 * @author xhy
 * @date 2021/3/24 18:04
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello,Spring Boot!";
    }
}
