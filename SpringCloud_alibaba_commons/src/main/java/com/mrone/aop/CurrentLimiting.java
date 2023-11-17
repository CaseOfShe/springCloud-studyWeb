package com.mrone.aop;

import java.lang.annotation.*;

/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-09-07 13:14
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentLimiting {
    long time() default 60000; // 限制时间 单位：毫秒(当前一分钟）
    int value() default 5; // 允许请求的次数
}