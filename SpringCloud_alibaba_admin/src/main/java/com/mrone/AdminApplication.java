package com.mrone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @program: studyweb
 * @description:
 * @author: Mr.One
 * @create: 2022-09-08 16:41
 **/
@SpringBootApplication
@MapperScan("com.mrone.mapper")
@EnableFeignClients
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
