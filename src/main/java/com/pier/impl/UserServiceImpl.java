package com.pier.impl;

import com.pier.UserService;
import com.pier.bean.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther zhongweiwu
 * @date 2018/10/18 11:29
 */
@Service
public class UserServiceImpl implements UserService {
    public List<User> getUsers(int count) {
        return null;
    }

    public void saveUser(User user) {

    }

    public User getUser(String username) {
        return null;
    }

    public void deleteUser(String username) {

    }

    public List<User> getAllUsers() {
        System.out.println("ha-------ha");
        return null;
    }

    public static void main(String[] args) {
        /*String str = ",,,a,b,c,,,";
        //String[] ary = str.split(",");
        String[] ary = StringUtils.split(str, ",");
        //预期大于3，结果是3
        System.out.println(ary.length);*/

        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        a.add("3");
        for (String temp : a) {
            if("3".equals(temp)){
                a.remove(temp);
            }
        }
    }
}
