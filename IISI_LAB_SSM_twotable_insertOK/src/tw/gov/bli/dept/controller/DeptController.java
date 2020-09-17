package tw.gov.bli.dept.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

	//查全部
			@RequestMapping("/dept_findAll.action")
			public String findAll(Model model) {
				//用service的方法
				List<Dept> list = deptService.findAll();
				model.addAttribute("list",list);
				model.addAttribute("Condition",new Dept());
				//轉JSP的名字
				return "deptlistAll";
			}
	//進入新增page
			@RequestMapping("/dept_insert_enter.action")
			public String insert_enter(Model model) {
				model.addAttribute("dept",new Dept());
				return "deptinsert";
			}

			
			//新增部門與員工
			@PostMapping("/deptinsert.action")
			public String insert(@Valid Dept dept,BindingResult result,
					@RequestParam String action,Model model, HttpServletRequest req) {
				if(result.hasErrors()) {
					model.addAttribute("message" , "有錯誤!!! 請再次核對");
					   return "deptinsert";
				}
				if("暫存客戶".equals(action)) {
					//第一次新增一個客戶 給他NEW新的
					if(req.getSession().getAttribute("users") == null) {
						List<User> list = new ArrayList<>();//給一個容器裝USER
						list.add(dept.getUser());//拿到一個user	
						req.getSession().setAttribute("users", list);
						req.getSession().setAttribute("dept", dept);
					}else {
					//之後建立的 沿用 加上拿回之前的LIST
						ArrayList<User> list = (ArrayList) req.getSession().getAttribute("users");
						list.add(dept.getUser());
						req.getSession().setAttribute("dept", dept);					
					}
					
					model.addAttribute("message" , "暫存成功");
					
					return "deptinsert";
				}
				
				if("建立單位與所屬客戶".equals(action)) {
					ArrayList<User> list = (ArrayList) req.getSession().getAttribute("users");
					dept.setUsers(list);
					deptService.insert(dept);
					list.clear();//清空

					//用service的方法  再拿到all
					List<Dept> listAll = deptService.findAll();
					model.addAttribute("list",listAll);
					model.addAttribute("Condition",new Dept());
					model.addAttribute("message" , "全部新增成功");

					return "deptlistAll";
				}
					return "deptinsert";
				}
			
			
			
//			@PostMapping("/deptinsert.action")
//			public String insert(@Valid Dept dept,BindingResult result,
// String action,Model model,@RequestParam int usersSize) {
//				
//				if(result.hasErrors()) {
//					model.addAttribute("usersSize",usersSize);
//					model.addAttribute("message" , "輸入錯誤錯誤訊息在下面");
//					   return "deptinsert";
//				}
//				if("建立單位與所屬客戶".equals(action)) {
//					DeptService svc = new DeptService();
//					svc.insert(dept);//把上面dept放入
//					model.addAttribute("message" , "全部新增成功");
//					
//					return deptadd(model);
//				}
//				if("存一個客戶".equals(action)) {
//					
//					model.addAttribute("usersSize",usersSize+1);
//					
//					return "deptinsert";
//				}
//					return "deptlistAll";
//				}
			
//			
//			public String deptadd(Model model) {
//				Dept dept = new Dept();
//				User user = new User();
//				dept.getUser().add(user);//叫出dept中可以裝users的容器
//				model.addAttribute("dept" , dept);
//				model.addAttribute("usersSize",0);//變回0 給下次進去的人用
//				return "deptinsert";
//				
//			}
		
}
