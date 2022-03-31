package com.consmation.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SaddyFire
 * @date 2022/3/24
 * @TIME:17:47
 */
@SpringBootApplication
@Slf4j
public class InterfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterfaceApplication.class,args);
        log.info("启动成功");
    }
}
