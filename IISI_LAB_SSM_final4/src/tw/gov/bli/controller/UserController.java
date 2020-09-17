package tw.gov.bli.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.gov.bli.domain.User;
import tw.gov.bli.dto.UserCase;
import tw.gov.bli.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController{//就是servlet
	
	//控制器呼叫 Service
	//注入 spring(service與DAO) 即可以用
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user_findAll.action")
	public String findAll(Model model) {
		System.out.println("表現層controller 查詢所有帳戶");
		//用service的方法
		List<UserCase> list = userService.findAll();
		System.out.println("All的list.size()="+list.size());
		model.addAttribute("list",list);
		//轉JSP的名字
		return "listAll";
	}
	
	//新增進入
		@RequestMapping("/user_insert_enter.action")
		public String insert_enter() {
			return "insert";
		}
		
	//新增
	@RequestMapping("/user_insert.action")
	public String insert(UserCase userCase,Model model) {
		userService.insert(userCase);
		//用service的方法
				List<UserCase> list = userService.findAll();
				model.addAttribute("list",list);
				return "listAll";
		}
	
	//進入修改
			@RequestMapping("/user_findByPrimaryKey.action")
			public String findByPrimaryKey(String uno,Model model) {

				UserCase aUser = userService.findByPrimaryKey(uno);
				model.addAttribute("aUser",aUser);
				return "update";
			}
	
	//修改
		@RequestMapping("/user_update.action")
		public String update(UserCase userCase,Model model) {
			userService.update(userCase);
			//用service的方法
			List<UserCase> list = userService.findAll();
			model.addAttribute("list",list);
			return "listAll";
		}
		
	//刪除
		@RequestMapping("/user_delete.action")
		public String delete(String uno,Model model) {
			userService.delete(uno);
			//用service的方法
			List<UserCase> list = userService.findAll();
			model.addAttribute("list",list);
			return "listAll";
		}	
		
		//刪除
		@RequestMapping("/user_deleteMany.action")
		public String deleteMany(HttpServletRequest request,Model model) {
			String[] unoArray = request.getParameterValues("deleteMany");
			System.out.println("unoArray.length="+unoArray.length);
			for(int i = 0;i<unoArray.length;i++) {
				userService.delete(unoArray[i]);
				System.out.println("unoArray[?]="+unoArray[i]);
			}
			
			//用service的方法
			List<UserCase> list = userService.findAll();
			model.addAttribute("list",list);
			System.out.println("list.size="+list.size());

			return "listAll";
		}	
		
	//複合查詢
			@RequestMapping("/user_findUserByCondition.action")
			public String findUserByCondition(Model model,UserCase userCase,String birth) throws ParseException {
				
				userCase.setUsername(userCase.getUsername());
				userCase.setSex(userCase.getSex());
				userCase.setUserid(userCase.getUserid());
				try {
					userCase.setBirthday(java.sql.Date.valueOf(birth));
					  } catch (Exception e) {
						  userCase.setBirthday(null);
					  }
				
				//用service的方法
				List<UserCase> list = userService.findUserByCondition(userCase);
				System.out.println("list.size()="+list.size());
				model.addAttribute("list",list);
				return "listAll";
			}	
			

}
