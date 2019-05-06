package com.pier.remote.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.pier.UserService;

import java.net.MalformedURLException;

/**
 * @auther zhongweiwu
 * @date 2018/10/18 18:14
 */
public class HessianClient {
    public static void main(String[] args) {
        /*ApplicationContext contex = new ClassPathXmlApplicationContext("hessian-client.xml");
        // 获得客户端的Hessian代理工厂bean
        UserService userService = (UserService) contex.getBean("hessianClient");
        userService.getAllUsers();*/

        //Spring Hessian代理Servelet
        String url = "http://localhost:8080/hessian/haha";
        HessianProxyFactory factory = new HessianProxyFactory();
        UserService userService = null;
        try {
            userService = (UserService) factory.create(UserService.class, url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        userService.getAllUsers();
    }
}
