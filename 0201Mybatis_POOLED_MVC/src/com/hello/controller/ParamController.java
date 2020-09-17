package com.hello.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hello.domain.Account;
import com.hello.domain.User;

//�����
@Controller
@RequestMapping(path="/param")
public class ParamController {

	//�ШD�Ѽƭ̪��j�w
	
	@RequestMapping("/testParam")
	public String testParam() {
		System.out.println("����F");
		return "success";
	}
	
	@RequestMapping("/testParam2")
	public String testParam2(String name) {
		System.out.println("����F");
		System.out.println("name="+name);
		return "success";
	}
	
	@RequestMapping("/testParam3")
	public String testParam3(String name,String password) {
		System.out.println("����F");
		System.out.println("name="+name);
		System.out.println("password"+password);
		return "success";
	}
	
	//���Ʃ�JJAVA BEAN
	@RequestMapping("/saveAccount")
	public String saveAccount(Account account) {
		System.out.println("����F");
		System.out.println(account);
		return "success";
	}
	
	//�ۭq�q�����ഫ��
	@RequestMapping("/saveUser")
	public String saveUser(User user) {
		System.out.println("����F");
		System.out.println(user);
		return "success";
	}
	
	//��ͪ�API
	@RequestMapping("/testServlet")
	public String testServlet(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("����F");
		System.out.println(request);
		HttpSession session = request.getSession();
		System.out.println(session);
		ServletContext servletContext = session.getServletContext();
		System.out.println(servletContext);
		System.out.println(response);
		return "success";
	}
}
