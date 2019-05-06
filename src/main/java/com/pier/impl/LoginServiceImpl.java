package com.pier.impl;

import com.pier.bean.User;
import com.pier.mapper.UserMapper;
import com.pier.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements ILoginService {
    
    @Autowired
    private UserMapper um;

    //@MethodLog(description = "login method", clazz = LoginServiceImpl.class)
    @Transactional
    public User login(String username, String password) {
        return um.login(username, password);
    }

    @Transactional
    public int register(User user) throws Exception {
        System.out.println("userid:"+user.getId());
        return um.insertUser(user);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-context.xml"});
        LoginServiceImpl service = context.getBean(LoginServiceImpl.class);
        service.login("root", "644886");
    }
}