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

//控制器類
@Controller
@RequestMapping(path="/user")
public class HelloController {
	//path=value 如果只有一個 可以省略只寫""
	
	@RequestMapping(path="/hello")
	public String sayHello() {
		System.out.println("Hello SpringMVC");
		//是回傳JSP名字
		return "success";
	}
	//method 指指有怎樣的請求 可以進來用這方法
	//所以如果用超連結 GET不能用這個方法 HTTP Status 405 – Method Not Allowed
	@RequestMapping(value="/testRequestMapping",method= {RequestMethod.POST})
	public String testRequestMapping() {
		System.out.println("測試RequestMapping註解");
		//是回傳JSP名字
		return "success";
	}
	
	//@RequestMapping(value="/testparams",params= {"name?456"})
	//如果長這樣要全部一樣
	@RequestMapping(value="/testparams",params= {"name"})
	public String testparams() {
		System.out.println("測試params註解");
		//是回傳JSP名字
		return "success";
	}
	

	@RequestMapping(value="/testAjax")
	public @ResponseBody User testAjax(@RequestBody User user) throws ParseException {
		System.out.println("測試testAjax");
		//客戶端發送AJAX請求 傳JSON 並把JSON封裝到USER中
		System.out.println(user);
		//模擬 查資料庫
		user.setUname("haha");
		user.setAge(40);
		return user;
	}
	
	@RequestMapping("/testForwardOrRedirect")
	public String testForwardOrRedirect() {
		System.out.println("testForwardOrRedirect OK");
		//不能用VIEW 自記寫請求轉發 可以拜訪裡面內容
//		return "forward:/WEB-INF/views/success.jsp";
		//重導 不能進來
		return "redirect:/index.jsp";
	}
	
	
}
