package tw.gov.bli.dept.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.gov.bli.dept.domain.Dept;
import tw.gov.bli.dept.service.DeptService;
import tw.gov.bli.log.logTest;
import tw.gov.bli.user.domain.User;

@Controller
@RequestMapping("/dept")
public class DeptController {
	private static Logger logger = LoggerFactory.getLogger(logTest.class);
	@Autowired
	private DeptService deptService;

//------------------------------------------------------------------------------------------------------------------//
	// 查全部 部門+客戶
	@RequestMapping("/dept_findAll.action")
	public String findAll(Model model) {
		// 用service的方法
		List<Dept> list = deptService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("Condition", new Dept());
		// 轉JSP的名字
		return "deptlistAll";
	}
//------------------------------------------------------------------------------------------------------------------//

	// 進入新增page
	@RequestMapping("/dept_insert_enter.action")
	public String insert_enter(Model model, HttpServletRequest req) {
		// 怕有人用幾個暫存之後 沒有要新增 要把他清空
		if (req.getSession().getAttribute("sessiondept") != null) {
			// 清空
			req.getSession().removeAttribute("sessiondept");
			
		}
		model.addAttribute("dept", new Dept());
		return "deptinsert";
	}

//------------------------------------------------------------------------------------------------------------------//
	// 新增部門與員工
	@PostMapping("/deptinsert.action")
	public String insert(@Valid Dept dept, BindingResult result, @RequestParam String action, Model model,
			HttpServletRequest req) {
		// 驗證錯誤
		if (result.hasErrors()) {
			model.addAttribute("message", "有錯誤!!! 請再次核對");
			return "deptinsert";
		}
		// 暫存客戶
		if ("saveOneUser".equals(action)) {
			// 第一次新增一個客戶 給他NEW新的
			if (req.getSession().getAttribute("sessiondept") == null) {
				Dept sessiondept = new Dept();
				BeanUtils.copyProperties(dept, sessiondept);
				sessiondept.getUsers().add(dept.getUser());
				req.getSession().setAttribute("sessiondept", sessiondept);

			} else {
				// 之後建立的 沿用 加上拿回之前的LIST
				Dept sessiondept = (Dept) req.getSession().getAttribute("sessiondept");
				sessiondept.getUsers().add(dept.getUser());
				req.getSession().setAttribute("sessiondept", sessiondept);
			}

			model.addAttribute("message", "暫存成功");

			return "deptinsert";
		}
		// 建立單位與所屬客戶
		if ("addAll".equals(action)) {
			Dept sessiondept = (Dept) req.getSession().getAttribute("sessiondept");
			
			deptService.insert(sessiondept);

			// 用service的方法 再拿到all
			List<Dept> listAll = deptService.findAll();
			model.addAttribute("list", listAll);
			model.addAttribute("Condition", new Dept());
			model.addAttribute("message", "全部新增成功");

			return "deptlistAll";
		}
		return "deptinsert";
	}
//------------------------------------------------------------------------------------------------------------------//

	// 開始修改
	@RequestMapping("/updateUser.action")
		public String updateUser(Dept dept,@RequestParam String action,@RequestParam("index") int value,Model model,HttpServletRequest req) {
		Dept sessiondept = (Dept) req.getSession().getAttribute("sessiondept");			
				if("updateUser".equals(action)){
						//拿到全部的 users
						
						//拿到你要改的那個user index
						User user = sessiondept.getUsers().get(value);
															sessiondept.setUser(user);
						//已經放上去修改 就讓他移除 一旦修改原暫存會清除 記得不管有沒有改資料 都要再暫存客戶
						model.addAttribute("dept",sessiondept);
						model.addAttribute("value",value);
					}
					
				if("confirm".equals(action)) {	

					sessiondept.getUsers().set(value, dept.getUser());
					logger.debug("in confirm");
					return "deptinsert";

				}
				if("quit".equals(action)) {
					return "deptinsert";
				}
				return "deptupdate";
			}
	
//------------------------------------------------------------------------------------------------------------------//

	// 刪除
	@RequestMapping("/deleteUser.action")
	public String deleteUser(@RequestParam("index") int value, Model model, HttpServletRequest req) {
		// 拿到全部的 users
		Dept sessiondept = (Dept) req.getSession().getAttribute("sessiondept");	
		sessiondept.getUsers().remove(value);
	
		model.addAttribute("dept", sessiondept);
		return "deptinsert";
	}

}
