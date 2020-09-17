package com.emp.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dept.service.DeptService;
import com.emp.model.EmpDoMain;
import com.emp.service.EmpService;


@RequestMapping("/emp")
@Controller
public class EmpAction {

	@Autowired
	private EmpService svc;
	@Autowired
	private DeptService dsvc;

	//查詢全部
	@GetMapping("getall.action")
	public String getall(ModelMap model) {
		model.addAttribute("empDoMain", new EmpDoMain());
		model.addAttribute("emps", svc.getAll());
		model.addAttribute("dept", dsvc.getAll());
		return "ShowAll";
	}
	//點擊新增
	@GetMapping("add.action")
	public String add(ModelMap model) {
		EmpDoMain emp = new EmpDoMain();
		model.addAttribute("empDoMain", emp);
		model.addAttribute("dept", dsvc.getAll());
		return "insert";
	}
	//開始新增
	@PostMapping("insert.action")
	public String insert(@Validated EmpDoMain emp, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("dept", dsvc.getAll());
			return "insert";
		}
		svc.insert(emp);
		return getall(model);
	}

	//單一刪除
	@GetMapping("delete.action")
	public String delete(@RequestParam("empno") Integer empno, ModelMap model) {
		svc.delete(empno);
		model.addAttribute("deletesuccess", "刪除成功!");
		return getall(model);
	}

	//點擊修改
	@GetMapping("getoneforupdate.action")
	public String getoneforupdate(@RequestParam("empno") Integer empno, ModelMap model) {
		model.addAttribute("empDoMain", svc.getOne(empno));
		return "update";
	}

	//開始修改
	@PostMapping("update.action")
	public String update(@Validated EmpDoMain emp, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "update";
		}
		svc.update(emp);
		return getall(model);
	}

	//批次刪除
	@PostMapping("deletebatch.action")
	public String deletebatch(@RequestParam("empno") Integer[] empnos, ModelMap model) {
		if (empnos.length <= 1) {
			return getall(model);
		}
		svc.deletebatch(empnos);
		return getall(model);
	}

	//搜尋
	@PostMapping("findByCondition.action")
	public String findByCondition(EmpDoMain emp, ModelMap model) {
		if ((emp.getEname().trim().length()<=0&&emp.getEname()=="")&&(emp.getHiredate()==null)&&emp.getDeptno()==null) {
			model.addAttribute("errormsg", "請輸入資料");
			return getall(model);
		} else {
			List<EmpDoMain> list = svc.findbycondition(emp);
			if (list != null) {
				model.addAttribute("emps", list);
				model.addAttribute("empDoMain", new EmpDoMain());
				model.addAttribute("dept", dsvc.getAll());
				return "ShowAll";
			} else {
				model.addAttribute("errormsg", "查無資料");
				return getall(model);
			}
		}
	}
}
