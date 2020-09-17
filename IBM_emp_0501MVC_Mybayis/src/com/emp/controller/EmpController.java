package com.emp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;

import com.emp.model.EmpVO;
import com.emp.model.EmpService;
import com.dept.model.DeptVO;
import com.dept.model.DeptService;

@Controller
@RequestMapping("/emp")
public class EmpController {

	/*
	 * This method will serve as addEmp.jsp handler.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "deptadd")
	public String deptadd(ModelMap model) {
		DeptVO deptVO = new DeptVO();
		EmpVO empVO = new EmpVO();
		deptVO.getEmps().add(empVO);
		model.addAttribute("deptVO" , deptVO);
		model.addAttribute("empsSize" , 0);
		return "emp/addDept";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "deptInsert_action")
	public String deptInsert_action(@Valid @ModelAttribute DeptVO deptVO ,BindingResult result,
ModelMap model , @RequestParam int empsSize ,@RequestParam String action) {
		
		if(result.hasErrors()) {
			model.addAttribute("empsSize" ,empsSize);
			model.addAttribute("message" , "輸入錯誤錯誤訊息在下面");
			return "emp/addDept";
		}
		
		if("全部新增".equals(action)) {
			DeptService svc = new DeptService();
			svc.inserts(deptVO);
			model.addAttribute("message" , "全部新增成功");
			return deptadd(model);
		}
		if("暫存".equals(action)) {
			
			model.addAttribute("empsSize" ,empsSize+1);
			model.addAttribute("message" , "暫存成功");
			return "emp/addDept";
		}	
		return "emp/addDept";
	}


}