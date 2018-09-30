package com.pier.service;


import com.pier.bean.UserBean;

public interface ILoginService {

    UserBean Login(String username, String password);
    
    
}