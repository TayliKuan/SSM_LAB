package tw.gov.bli.controller;

import java.util.List;

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

import tw.gov.bli.domain.Dept;
import tw.gov.bli.domain.User;
import tw.gov.bli.dto.DeptCase;
import tw.gov.bli.dto.UserCase;
import tw.gov.bli.log.logTest;
import tw.gov.bli.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptController {
	private static Logger logger = LoggerFactory.getLogger(logTest.class);
	@Autowired
	private DeptService deptService;
	
	//查全部
			@RequestMapping("/dept_findAll.action")
			public String findAll(Model model) {
				//用service的方法
				List<DeptCase> list = deptService.findAll();
				model.addAttribute("list",list);
				model.addAttribute("Condition",new DeptCase());
				//轉JSP的名字
				return "deptlistAll";
			}
	//進入新增page
			@RequestMapping("/dept_insert_enter.action")
			public String insert_enter(Model model) {
				model.addAttribute("deptCase",new DeptCase());
				model.addAttribute("usersSize",0);
				return "deptinsert";
			}

			
	//新增部門與員工
			@PostMapping("/deptinsert.action")
			public String insert(@Valid DeptCase deptCase,BindingResult result,
 String action,Model model,@RequestParam int usersSize) {
				
				if(result.hasErrors()) {
					model.addAttribute("usersSize",usersSize);
					model.addAttribute("message" , "輸入錯誤錯誤訊息在下面");
					   return "deptinsert";
				}
				if("建立單位與所屬客戶".equals(action)) {
					DeptService svc = new DeptService();
					svc.insert(deptCase);//把上面dept放入
					model.addAttribute("message" , "全部新增成功");
					
					return deptadd(model);
				}
				if("存一個客戶".equals(action)) {
					
					model.addAttribute("usersSize",usersSize+1);
					
					return "deptinsert";
				}
					return "deptlistAll";
				}
			
			
			public String deptadd(Model model) {
				Dept dept = new Dept();
				User user = new User();
				dept.getUsers().add(user);//叫出dept中可以裝users的容器
				model.addAttribute("dept" , dept);
				model.addAttribute("usersSize",0);//變回0 給下次進去的人用
				return "deptinsert";
				
			}
		
}
