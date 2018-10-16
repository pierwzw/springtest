package com.pier.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * @auther zhongweiwu
 * @date 2018/9/11 19:43
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware, BeanNameAware, BeanFactoryAware,
        MessageSourceAware, ApplicationEventPublisherAware, ResourceLoaderAware {

    private static ApplicationContext applicationContext;
    private String beanName;
    private static BeanFactory beanFactory;
    private MessageSource messageSource;
    private ApplicationEventPublisher applicationEventPublisher;
    private ResourceLoader resourceLoader;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName){
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        ApplicationContextHolder.beanFactory = beanFactory;
    }

    public void setBeanName(String name) {
        this.beanName = beanName;
    }

    public String getBeanName(){
        return beanName;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
