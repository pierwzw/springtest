package com.pier.remote;

import com.pier.bean.User;
import com.pier.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * @auther zhongweiwu
 * @date 2018/10/18 11:53
 */
public class RmiClient {

    @Autowired
    private UserService userService;

    @Bean
    public RmiProxyFactoryBean getRmiProxyFactoryBean(){
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost/UserService");
        factoryBean.setServiceInterface(UserService.class);
        return factoryBean;
    }

    public User getUser(String username){
        return userService.getUser(username);
    }
}
