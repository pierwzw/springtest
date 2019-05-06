package com.pier.impl;

import com.pier.WebHessianService;
import org.springframework.stereotype.Service;

/**
 * @auther zhongweiwu
 * @date 2018/10/18 15:21
 */
@Service("WebHessianService")
public class WebHessianServiceImpl implements WebHessianService {
    public void test() {
        System.out.println("haha this is a hessian");
    }
}
