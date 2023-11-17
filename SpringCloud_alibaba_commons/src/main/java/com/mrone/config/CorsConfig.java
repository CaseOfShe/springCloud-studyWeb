//package com.mrone.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @program: studyweb
// * @description:
// * @author: Mr.One
// * @create: 2022-09-11 17:21
// **/
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") //允许跨域访问路径
//                .allowedOrigins("*") //允许跨域访问的源
//                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE") //允许请求方式
//                .maxAge(168000) //预检间隔时间
//                .allowedHeaders("*") //允许请求头
//                .allowCredentials(true); //是否发送cookie
//    }
//}
