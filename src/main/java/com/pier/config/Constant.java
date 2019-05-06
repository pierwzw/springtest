package com.pier.config;

import com.pier.util.ConfigUtil;
import java.util.Properties;

/**
 * @auther zhongweiwu
 * @date 2018/10/18 15:25
 */
public class Constant {
	private static final Properties PROPERTIES = ConfigUtil.loadProperties("/constant.properties");

	public static final String HESSIAN_AUTH = PROPERTIES.getProperty("hessianAuth");
}
