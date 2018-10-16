package com.pier.service;

import com.pier.bean.UserBean;
import com.pier.service.impl.LoginServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionHolder;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class MapperTest {

	@Autowired
	private LoginServiceImpl loginServiceImpl;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Test
	public void testMapper() {
		String username = "root";
		String password = "123456";
		long start = System.currentTimeMillis();
		UserBean user = loginServiceImpl.login(username, password);
		System.out.println("--------------first select cost:" + (System.currentTimeMillis() - start) + "ms.-------------");
		long start2 = System.currentTimeMillis();
		UserBean user2 = loginServiceImpl.login(username, password);
		System.out.println("--------------second select cost:" + (System.currentTimeMillis() - start2) + "ms.-------------");
	}
}
