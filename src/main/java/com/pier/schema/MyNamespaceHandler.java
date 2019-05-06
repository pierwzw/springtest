package com.pier.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @auther zhongweiwu
 * @date 2019/3/21 10:45
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
    }
}
