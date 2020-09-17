package tw.gov.bli.dept.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tw.gov.bli.dept.domain.Dept;
import tw.gov.bli.dept.service.DeptService;
import tw.gov.bli.log.logTest;
import tw.gov.bli.user.domain.User;
import tw.gov.bli.user.service.UserService;

@Controller
@RequestMapping("/dept")
public class DeptController {
	private static Logger logger = LoggerFactory.getLogger(logTest.class);
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;

//------------------------------------------------------------------------------------------------------------------//
	//查全部 部門+客戶
			@RequestMapping("/dept_findAll.action")
			public String findAll(Model model) {
				//用service的方法
				List<Dept> list = deptService.findAll();
				model.addAttribute("list",list);
				model.addAttribute("Condition",new Dept());
				//轉JSP的名字
				return "deptlistAll";
			}
//------------------------------------------------------------------------------------------------------------------//

	//進入新增page
			@RequestMapping("/dept_insert_enter.action")
			public String insert_enter(Model model, HttpServletRequest req) {
				//怕有人用幾個暫存之後  沒有要新增 要把他清空
				if(req.getSession().getAttribute("users") != null) {
					ArrayList<User> list = (ArrayList) req.getSession().getAttribute("users");
					list.clear();//清空
				}
				model.addAttribute("dept",new Dept());
				return "deptinsert";
			}
//------------------------------------------------------------------------------------------------------------------//
			//新增部門與員工
			@PostMapping("/deptinsert.action")
			public String insert(@Valid Dept dept,BindingResult result,
					@RequestParam String action,Model model, HttpServletRequest req) {
				//驗證錯誤
				if(result.hasErrors()) {
					model.addAttribute("message" , "有錯誤!!! 請再次核對");
					   return "deptinsert";
				}
				//暫存客戶
				if("暫存客戶".equals(action)) {
					//第一次新增一個客戶 給他NEW新的
					if(req.getSession().getAttribute("dept") == null) {
						dept.getUsers().add(dept.getUser());
						req.getSession().setAttribute("dept", dept);
					}else {
					//之後建立的 沿用 加上拿回之前的LIST
						Dept sdept = (Dept) req.getSession().getAttribute("dept");
						sdept.getUsers().add(dept.getUser());
					}
					
					model.addAttribute("message" , "暫存成功");
					
					return "deptinsert";
				}
				//建立單位與所屬客戶
				if("建立單位與所屬客戶".equals(action)) {
					Dept sdept =  (Dept) req.getSession().getAttribute("dept");
					sdept.getUsers().add(dept.getUser());
					
					deptService.insert(sdept);
					
					//用service的方法  再拿到all
					List<Dept> listAll = deptService.findAll();
					model.addAttribute("list",listAll);
					model.addAttribute("Condition",new Dept());
					model.addAttribute("message" , "全部新增成功");

					return "deptlistAll";
				}
					return "deptinsert";
				}
//------------------------------------------------------------------------------------------------------------------//
			
			 // 開始修改
			@RequestMapping("/updateUser.action")
			public String updateUser(@RequestParam("index") int value,Model model,HttpServletRequest req) {
				//拿到全部的 users
				Dept sdept =(Dept) req.getSession().getAttribute("dept");
				//拿到你要改的那個user index
				User user = sdept.getUsers().get(value);
				//把這個放回去上面
				sdept.setUser(user);
//				userService.update(user);//不行 因為還沒有deptno
				//已經放上去修改 就讓他移除 一旦修改原暫存會清除 記得不管有沒有改資料 都要再暫存客戶
				//modelAttribute="dept" 把你要改的放上去要修改的地方
				model.addAttribute("dept",sdept);
				return "deptinsert";
			}	
//------------------------------------------------------------------------------------------------------------------//
			
			 // 刪除
			@RequestMapping("/deleteUser.action")
			public String deleteUser(@RequestParam("index") int value,Model model,HttpServletRequest req) {
				//拿到全部的 users
				List<User> list =(List<User>) req.getSession().getAttribute("users");
				//從LIST移除此筆
				list.remove(value);
				//傳回dept
				Dept dept = (Dept) req.getSession().getAttribute("dept");
				model.addAttribute("dept",dept);
				return "deptinsert";
			}	
			

}
