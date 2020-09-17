package com.hello.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hello.domain.Account;
import com.hello.domain.User;

//控制器類
@Controller
@RequestMapping(path="/param")
public class ParamController {

	//請求參數們的綁定
	
	@RequestMapping("/testParam")
	public String testParam() {
		System.out.println("執行了");
		return "success";
	}
	
	@RequestMapping("/testParam2")
	public String testParam2(String name) {
		System.out.println("執行了");
		System.out.println("name="+name);
		return "success";
	}
	
	@RequestMapping("/testParam3")
	public String testParam3(String name,String password) {
		System.out.println("執行了");
		System.out.println("name="+name);
		System.out.println("password"+password);
		return "success";
	}
	
	//把資料放入JAVA BEAN
	@RequestMapping("/saveAccount")
	public String saveAccount(Account account) {
		System.out.println("執行了");
		System.out.println(account);
		return "success";
	}
	
	//自訂義類型轉換器
	@RequestMapping("/saveUser")
	public String saveUser(User user) {
		System.out.println("執行了");
		System.out.println(user);
		return "success";
	}
	
	//原生的API
	@RequestMapping("/testServlet")
	public String testServlet(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("執行了");
		System.out.println(request);
		HttpSession session = request.getSession();
		System.out.println(session);
		ServletContext servletContext = session.getServletContext();
		System.out.println(servletContext);
		System.out.println(response);
		return "success";
	}
}
