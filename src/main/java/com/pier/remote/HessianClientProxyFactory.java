package com.pier.remote;

import com.caucho.hessian.client.HessianProxyFactory;
import com.pier.config.Constant;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @auther zhongweiwu
 * @date 2018/10/18 15:41
 */
public class HessianClientProxyFactory extends HessianProxyFactory {

    private final String hessianAuth;

    public HessianClientProxyFactory() {
        this.hessianAuth = Constant.HESSIAN_AUTH;
    }
}