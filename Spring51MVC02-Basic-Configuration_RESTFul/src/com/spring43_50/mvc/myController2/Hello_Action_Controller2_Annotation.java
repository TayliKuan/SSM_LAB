package com.spring43_50.mvc.myController2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Hello_Action_Controller2_Annotation {

//  @RequestMapping
//	@RequestMapping("/")
	@RequestMapping(method = RequestMethod.GET, value="/helloAction/sayHello1.do")
	public String method1(ModelMap model) {
		System.out.println("RequestMapping-sayHello1被呼叫:");
		model.addAttribute("greetingKey", "Hello World, 世界你好!");
		return "welcome/back-view"; //Internal Resource View (內部資源視圖)
	}


	@RequestMapping(method = RequestMethod.GET, value="/helloAction/sayHello2.do")
	public String method2(ModelMap model, @RequestParam("ename") String ename) {
		System.out.println("RequestMapping-sayHello2被呼叫: ename="+ename);
		model.addAttribute("greetingKey", ename);
		return "welcome/back-view"; //Internal Resource View (內部資源視圖)
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="/helloAction/sayHello3_Redirect.do")
	public String method3_Redirect(ModelMap model, @RequestParam("ename") String ename) {
		//try { 
			System.out.println("RequestMapping-sayHello3_Redirect被呼叫: ename="+ename);
			// 以下Query String的中文處理程式碼可完全省去，參見Servlet講義p255
			//System.out.println("RequestMapping-sayHello3_Redirect被呼叫: ename="+new String(ename.getBytes("ISO-8859-1"),"UTF-8"));
		//} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		//} 
		model.addAttribute("greetingKey", ename);
	    return "redirect:/welcome/front-view.jsp";
	}

}
