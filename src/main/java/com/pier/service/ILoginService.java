package com.pier.service;


import com.pier.bean.UserBean;

public interface ILoginService {

    UserBean login(String username, String password);
    
    
}