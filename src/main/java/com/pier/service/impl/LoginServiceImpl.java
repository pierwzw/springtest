package com.pier.service.impl;

import com.pier.aspect.MethodLog;
import com.pier.bean.BeanInit;
import com.pier.bean.UserBean;
import com.pier.mapper.UserMapper;
import com.pier.service.ILoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements ILoginService {
    
    @Resource
    private UserMapper um;

    @MethodLog(description = "login method", clazz = LoginServiceImpl.class)
    public UserBean Login(String username, String password) {
        return um.login(username, password);
    }
}