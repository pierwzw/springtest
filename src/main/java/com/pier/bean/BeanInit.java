package com.pier.bean;

import com.pier.aspect.Error;
import com.pier.aspect.MethodLog;
import com.pier.aspect.Monitor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @auther zhongweiwu
 * @date 2018/9/4 14:52
 */
@Monitor
public class BeanInit implements InitializingBean, DisposableBean{
    private String age;
    private String name;

   /* @Autowired
    private BeanInit2 beanInit2;*/

    public BeanInit(){
        System.out.println("TestBeanInit");
    }

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

    public void haha(){
        say();
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean");
    }

    public void init(){
        System.out.println("init-method");
    }


    /**
     * no work
     */
    @PostConstruct
    public void postConstruct(){
        System.out.println("postConstruct");
    }

    public void destroy(){
        System.out.println("destroy");
    }

    public void destroy2(){
        System.out.println("destroy2");
    }

    /**
     * no work
     */
    @PreDestroy
    public void preDestroy(){
        System.out.println("preDestroy");
    }

    public String say(){
        return "haha:"+getAge()+ ":" + getName();
    }

    @Error
    public void toDo() throws IllegalAccessException {
        throw new IllegalAccessException("cannot access this way!");
    }
}
