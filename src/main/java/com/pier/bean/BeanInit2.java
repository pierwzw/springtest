package com.pier.bean;

import org.springframework.beans.BeansException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @auther zhongweiwu
 * @date 2018/9/4 14:52
 */
public class BeanInit2/* implements BeanPostProcessor*/{
    private String age;
    private String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeanInit2(){
        System.out.println("TestBeanInit2");
    }

    public void haha(){
        System.out.println("haha2:"+age+ ":" + name);
    }

    /**
     * no work
     */
    @PostConstruct
    public void postConstruct(){
        System.out.println("postConstruct2");
    }

    /**
     * no work
     */
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization2");
        return o;
    }

    /**
     * no work
     */
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization2");
        return o;
    }

    /**
     * no work
     */
    @PreDestroy
    public void preDestroy(){
        System.out.println("preDestroy2");
    }
}
