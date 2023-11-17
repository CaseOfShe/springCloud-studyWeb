package com.mrone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: SpringCloudAlibaba_parent
 * @description:
 * @author: Mr.One
 * @create: 2022-08-15 15:25
 **/
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.mrone.mapper")
public class UsersApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class,args);
    }
}
