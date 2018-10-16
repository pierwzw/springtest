package com.pier;

import com.pier.aware.ApplicationContextHolder;
import com.pier.bean.BeanInit;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther zhongweiwu
 * @date 2018/9/4 15:57
 */
public class MainTest {

    public static String getTableName(String canalId) {
        int index = Math.abs(canalId.hashCode()) % 40 + 1;
        return "vip_add_ppcard_" + index;
    }
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-context.xml"});
        //BeanInit testBeanInit = (BeanInit) context.getBean("beanInit");
        BeanInit testBeanInit = ApplicationContextHolder.getBean("testInit");
        testBeanInit.say();
        try {
            testBeanInit.toDo();
        } catch (IllegalAccessException e) {
        }
        //ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        // testBeanInit2 = (BeanInit2) context2.getBean("testInit2");
        //testBeanInit2.setAge("20");
        //testBeanInit2.setName("pier");
        //testBeanInit2.haha();
        context.destroy();
    }
}
