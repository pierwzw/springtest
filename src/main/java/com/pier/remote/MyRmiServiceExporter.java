package com.pier.remote;

import com.pier.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * @auther zhongweiwu
 * @date 2018/10/18 11:41
 */
public class MyRmiServiceExporter {

    /** 返回一个bean, 由此导出为一个rmi服务 */
    @Bean
    public RmiServiceExporter rmiExporter(UserService userService){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(userService);
        rmiServiceExporter.setServiceName("UserService");
        rmiServiceExporter.setServiceInterface(UserService.class);
        rmiServiceExporter.setRegistryHost("localhost");
        rmiServiceExporter.setRegistryPort(1199);
        return rmiServiceExporter;
    }
}

