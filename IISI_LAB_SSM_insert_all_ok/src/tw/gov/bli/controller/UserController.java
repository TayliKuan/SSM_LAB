package tw.gov.bli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.gov.bli.domain.User;
import tw.gov.bli.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {//就是servlet
	
	//控制器呼叫 Service
	//注入 spring(service與DAO) 即可以用
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		System.out.println("表現層 查詢所有帳戶");
		//用service的方法
		List<User> list = userService.findAll();
		model.addAttribute("list",list);
		//轉JSP的名字
		return "list";
	}
	
	//新增
	@RequestMapping("/insert")
	public String insert(User user,Model model) {
		userService.insert(user);
		//用service的方法
				List<User> list = userService.findAll();
				model.addAttribute("list",list);
		return "list";
	}
}
