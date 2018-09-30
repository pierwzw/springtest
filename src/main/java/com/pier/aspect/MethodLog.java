package com.pier.aspect;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther zhongweiwu
 * @date 2018/9/5 16:22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodLog {

    /**
     * 是否需要该注解
     */
    boolean required() default true;

    /**
     * 记录操作描述
     */
    String description() default "";

    /**
     * 增删改的数据的类型
     */
    Class<?> clazz() default Object.class;
}
