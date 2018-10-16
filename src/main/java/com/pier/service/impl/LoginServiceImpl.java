package com.pier.service.impl;

import com.pier.aspect.MethodLog;
import com.pier.bean.BeanInit;
import com.pier.bean.UserBean;
import com.pier.mapper.UserMapper;
import com.pier.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements ILoginService {
    
    @Autowired
    private UserMapper um;

    //@MethodLog(description = "login method", clazz = LoginServiceImpl.class)
    @Transactional
    public UserBean login(String username, String password) {
        return um.login(username, password);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-context.xml"});
        LoginServiceImpl service = context.getBean(LoginServiceImpl.class);
        service.login("root", "644886");
    }
}