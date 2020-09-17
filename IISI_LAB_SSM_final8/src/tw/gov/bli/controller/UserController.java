package tw.gov.bli.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.gov.bli.dto.UserCase;
import tw.gov.bli.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController{//就是servlet
	
	//控制器呼叫 Service
	//注入 spring(service與DAO) 即可以用
	@Autowired
	private UserService userService;
//------------------------------------------------------------------------------------------------------------------//
	//查全部
		@RequestMapping("/user_findAll.action")
		public String findAll(Model model) {
			//用service的方法
			List<UserCase> list = userService.findAll();
			model.addAttribute("list",list);
			model.addAttribute("Condition",new UserCase());
			//轉JSP的名字
			return "listAll";
		}
//------------------------------------------------------------------------------------------------------------------//	
	//進入新增page
		@RequestMapping("/user_insert_enter.action")
		public String insert_enter(Model model) {
			model.addAttribute("userCase",new UserCase());
			return "insert";
		}
//------------------------------------------------------------------------------------------------------------------//		
	//新增
		@RequestMapping("/user_insert.action")
		public String insert(@Valid UserCase userCase,BindingResult result,Model model) {
			if(result.hasErrors()) {
				   return "insert";
			}
			userService.insert(userCase);
				return findAll( model);
			}
//------------------------------------------------------------------------------------------------------------------//	
	//進入修改page
		@RequestMapping("/user_findByPrimaryKey.action")
		public String findByPrimaryKey(String uno,Model model) {
			UserCase aUser = userService.findByPrimaryKey(uno);
			model.addAttribute("userCase",aUser);//從form:form過來的 modelAttribute 空的
			return "update";
		}
//------------------------------------------------------------------------------------------------------------------//	
	//修改
		@RequestMapping("/user_update.action")
		public String update(@Valid UserCase userCase,BindingResult result,Model model,String uno) {
			if(result.hasErrors()) {
				model.addAttribute("userCase",userCase);//error msg
				return "update";
			}
			userService.update(userCase);
			return findAll( model);
		}
//------------------------------------------------------------------------------------------------------------------//		
	//刪除一個
		@RequestMapping("/user_delete.action")
		public String delete(@Valid UserCase userCase,BindingResult result,String uno,Model model) {
			userService.delete(uno);
			return findAll( model);
		}	
//------------------------------------------------------------------------------------------------------------------//		
	//批次刪除
		@RequestMapping("/user_deleteMany.action")
		public String deleteMany(@Valid UserCase userCase,BindingResult result,HttpServletRequest request,Model model) {
			String[] unoArray = request.getParameterValues("deleteMany");
			if(unoArray.length==1) {
				System.out.println("請選擇要刪除的用戶");
			}else {
				for(int i = 0;i<unoArray.length;i++) {
					userService.delete(unoArray[i]);
				}
			}
			return findAll( model);
		}	
//------------------------------------------------------------------------------------------------------------------//		
	//複合查詢
		@RequestMapping("/user_findUserByCondition.action")
		public String findUserByCondition(@Valid UserCase userCase,BindingResult result ,Model model,String birth) throws ParseException {
			
			userCase.setUsername(userCase.getUsername());
			userCase.setSex(userCase.getSex());
			userCase.setUserid(userCase.getUserid());
			try {
				userCase.setBirthday(java.sql.Date.valueOf(birth));
			} catch (Exception e) {
				userCase.setBirthday(null);
			}
			model.addAttribute("Condition",userCase);
			//用service的方法
			List<UserCase> list = userService.findUserByCondition(userCase);
			model.addAttribute("list",list);
			return "listAll";
		}	

}
