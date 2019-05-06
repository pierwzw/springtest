package com.pier;

import com.pier.bean.User;

import java.util.List;

/**
 * @auther zhongweiwu
 * @date 2018/10/18 11:29
 */
public interface UserService {

    List<User> getUsers(int count);

    void saveUser(User user);

    User getUser(String username);

    void deleteUser(String username);

    List<User> getAllUsers();
}
