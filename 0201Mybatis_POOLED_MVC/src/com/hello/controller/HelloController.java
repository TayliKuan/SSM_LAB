package com.hello.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.domain.User;

//�����
@Controller
@RequestMapping(path="/user")
public class HelloController {
	//path=value �p�G�u���@�� �i�H�ٲ��u�g""
	
	@RequestMapping(path="/hello")
	public String sayHello() {
		System.out.println("Hello SpringMVC");
		//�O�^��JSP�W�r
		return "success";
	}
	//method ��������˪��ШD �i�H�i�ӥγo��k
	//�ҥH�p�G�ζW�s�� GET����γo�Ӥ�k HTTP Status 405 �V Method Not Allowed
	@RequestMapping(value="/testRequestMapping",method= {RequestMethod.POST})
	public String testRequestMapping() {
		System.out.println("����RequestMapping����");
		//�O�^��JSP�W�r
		return "success";
	}
	
	//@RequestMapping(value="/testparams",params= {"name?456"})
	//�p�G���o�˭n�����@��
	@RequestMapping(value="/testparams",params= {"name"})
	public String testparams() {
		System.out.println("����params����");
		//�O�^��JSP�W�r
		return "success";
	}
	

	@RequestMapping(value="/testAjax")
	public @ResponseBody User testAjax(@RequestBody User user) throws ParseException {
		System.out.println("����testAjax");
		//�Ȥ�ݵo�eAJAX�ШD ��JSON �ç�JSON�ʸ˨�USER��
		System.out.println(user);
		//���� �d��Ʈw
		user.setUname("haha");
		user.setAge(40);
		return user;
	}
	
	@RequestMapping("/testForwardOrRedirect")
	public String testForwardOrRedirect() {
		System.out.println("testForwardOrRedirect OK");
		//�����VIEW �۰O�g�ШD��o �i�H���X�̭����e
//		return "forward:/WEB-INF/views/success.jsp";
		//���� ����i��
		return "redirect:/index.jsp";
	}
	
	
}
