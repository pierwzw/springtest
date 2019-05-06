package com.pier;


import com.pier.bean.User;

public interface ILoginService {

    User login(String username, String password);

    int register(User user) throws Exception;
}