package com.pier.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import com.pier.aspect.Monitor;
import org.springframework.stereotype.Component;

/**
 * @auther zhongweiwu
 * @date 2018/9/5 11:49
 */
/*@Component*/
public class MyBeanPostProsessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization:bean:"+o.getClass()+", beanName:" + s);
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization:bean:"+o.getClass()+", beanName:" + s);
        if (o.getClass().isAnnotationPresent(Monitor.class)) {
            System.out.println("this is a bean");
        }
        return o;
    }
}
