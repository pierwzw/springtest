package com.pier.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @auther zhongweiwu
 * @date 2018/11/3 16:53
 */
@Intercepts({@Signature(
    type = StatementHandler.class,
    method = "prepare",
    args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {

    Properties props = null;

    /**
     * 代理拦截对象方法的内容
     */
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("before......");
        Object obj = invocation.proceed();
        System.out.println("after......");
        return obj;
    }

    /**
     * 生成对象的代理（这里常用Mybatis提供的Plugin类的wrap方法）
     * @param target
     * @return
     */
    public Object plugin(Object target) {
        System.out.println("invoke generate proxy object......");
        return Plugin.wrap(target, this);
    }

    /**
     * 获取插件配置的属性<>plugin/>
     * @param properties
     */
    public void setProperties(Properties properties) {
        System.out.println(properties.get("dbType"));
        this.props = properties;
    }
}
