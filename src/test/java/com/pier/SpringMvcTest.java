package com.pier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml"})
public class SpringMvcTest {

	@Test
	public void hessianServer(){

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mvc.xml");
		UserService userService = (UserService) context.getBean("webHessianService");
		userService.getAllUsers();
	}
}
