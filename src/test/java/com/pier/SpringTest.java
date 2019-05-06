package com.pier;

import com.caucho.hessian.client.HessianProxyFactory;
import com.pier.bean.People;
import com.pier.bean.User;
import com.pier.impl.LoginServiceImpl;
import com.pier.mq.rabbitmq.producer.Producer;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.net.MalformedURLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SpringTest {

	@Autowired
	private LoginServiceImpl loginServiceImpl;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Test
	public void testMapper() {
		String username = "root";
		String password = "123456";
		long start = System.currentTimeMillis();
		User user = loginServiceImpl.login(username, password);
		System.out.println("--------------first select cost:" + (System.currentTimeMillis() - start) + "ms.-------------");
		long start2 = System.currentTimeMillis();
		User user2 = loginServiceImpl.login(username, password);
		System.out.println("--------------second select cost:" + (System.currentTimeMillis() - start2) + "ms.-------------");
	}

	@Test
	public void hessian() {
		String url = "http://127.0.0.1/Hessian/haha";
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			UserService service = (UserService) factory.create(UserService.class, url);
			service.getAllUsers();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private Producer producer;

	@Test
	public void testMq() throws InterruptedException {
	    int i=0;
		producer.init();
		while(true){
			producer.sendMessage("message:" + i++);
			Thread.sleep(500);
		}
	}

	@Resource
	private People people;

	@Test
    public void testSchema(){

        System.out.println(people.getSex());
        System.out.println(people.getName());
        System.out.println(people.getAge());
    }
}
