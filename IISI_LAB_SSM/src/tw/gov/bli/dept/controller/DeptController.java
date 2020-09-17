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
		// 轉JSP的名字
		return "deptlistAll";
	}
//------------------------------------------------------------------------------------------------------------------//

	// 進入新增page
	@RequestMapping("/dept_insert_enter.action")
	public String insert_enter(Model model, HttpServletRequest req) {
		// 怕有人用幾個暫存之後 沒有要新增 要把他清空
		if (req.getSession().getAttribute("sessiondept") != null) {
			// 把session清空 再進去
			req.getSession().removeAttribute("sessiondept");
		}
		// 要記得一開始就要給他新的一個dept 不然就會500找不到dept
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
				//session 沒有就給他一個新的
				Dept sessiondept = new Dept();
				//把從頁面傳過來的@Valid Dept dept 複製一分到session去
				//之後都用session操作
				BeanUtils.copyProperties(dept, sessiondept);
				//session拿Dept的Users容器  裝從頁面傳過來的@Valid Dept dept的User
				sessiondept.getUsers().add(dept.getUser());
				//把裝好User的session set回去session
				req.getSession().setAttribute("sessiondept", sessiondept);

			} else {
				//已有Session 就Get回來目前的session 再加入新的User
				Dept sessiondept = (Dept) req.getSession().getAttribute("sessiondept");
				sessiondept.getUsers().add(dept.getUser());
				req.getSession().setAttribute("sessiondept", sessiondept);
			}
			//sweetalert
			model.addAttribute("message", "暫存成功");
			//回新增頁面
			return "deptinsert";
		}
		
		// 建立單位與所屬客戶
		if ("addAll".equals(action)) {
			//已有Session 就Get回來目前的session
			Dept sessiondept = (Dept) req.getSession().getAttribute("sessiondept");
			//執行deptService insert的方法
			deptService.insert(sessiondept);

			// 用service的方法 再拿到all
			List<Dept> listAll = deptService.findAll();
			model.addAttribute("list", listAll);
			model.addAttribute("message", "全部新增成功");

			return "deptlistAll";
		}
		return "deptinsert";
	}
//------------------------------------------------------------------------------------------------------------------//

	//enter修改page
	@RequestMapping("/updateUser_enter.action")
		public String updateUser_enter(Dept dept,@RequestParam String action,
				@RequestParam("index") int value,Model model,HttpServletRequest req) {
			//拿到session
			Dept sessiondept = (Dept) req.getSession().getAttribute("sessiondept");			
				if("updateUser".equals(action)){
						//拿到你要改的那個user index 放到修改上
						User user = sessiondept.getUsers().get(value);
						//改完set回去 給下面按鈕處理 理 confirm/quit
						sessiondept.setUser(user);
						model.addAttribute("dept",sessiondept);
						//記得要傳值  給下面按鈕處理 confirm/quit
						model.addAttribute("value",value);
					}
			return "deptupdate";
		}
	
	//開始修改
	@RequestMapping("/updateUser.action")
	public String updateUser(Dept dept,@RequestParam String action,@RequestParam("index") int value,
			Model model,HttpServletRequest req) {
		//先拿到session
		Dept sessiondept = (Dept) req.getSession().getAttribute("sessiondept");			
			//確認修改 
			if("confirm".equals(action)) {	
				//傳index跟要改的這個User回去session
				sessiondept.getUsers().set(value, dept.getUser());
				logger.debug("in confirm");
				//把USER值清空 保留上方dept
				dept.setUser(null);
				return "deptinsert";
			}
			//不改
			if("quit".equals(action)) {
				return "deptinsert";
			}
			//失敗回原頁
			return "deptupdate";
		}
	
//------------------------------------------------------------------------------------------------------------------//

	// 刪除A User
	@RequestMapping("/deleteUser.action")
	public String deleteUser(@RequestParam("index") int value, Model model, HttpServletRequest req) {
		// 拿到全部的dept session
		Dept sessiondept = (Dept) req.getSession().getAttribute("sessiondept");	
		//刪除你要刪除的那個index
		sessiondept.getUsers().remove(value);
		//把剩下的set回去session
		model.addAttribute("dept", sessiondept);
		return "deptinsert";
	}
//------------------------------------------------------------------------------------------------------------------//
	@RequestMapping("/delAll.action")
	public String delAll() {
		
		return null;
		
	}

}
