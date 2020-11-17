package com.example.demo.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 防止重复提交注解
 */
public @interface RepeatSubmit {

    /**
     * 设置请求锁定时间,单位(秒)
     * @return
     */
    int lockTime() default 5;
}