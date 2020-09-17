package tw.gov.bli.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.gov.bli.service.UserService;

public class TestSpring {
	
	@Test
	public void run1() {
		//加載spring配置文件 
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//獲取Service
		UserService as = (UserService) ac.getBean("UserService");
		//使用方法
		as.findAll();
	}
}
