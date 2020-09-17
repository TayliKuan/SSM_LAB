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

	//�d�ߥ���
	@GetMapping("getall.action")
	public String getall(ModelMap model) {
		model.addAttribute("empDoMain", new EmpDoMain());
		model.addAttribute("emps", svc.getAll());
		model.addAttribute("dept", dsvc.getAll());
		return "ShowAll";
	}
	//�I���s�W
	@GetMapping("add.action")
	public String add(ModelMap model) {
		EmpDoMain emp = new EmpDoMain();
		model.addAttribute("empDoMain", emp);
		model.addAttribute("dept", dsvc.getAll());
		return "insert";
	}
	//�}�l�s�W
	@PostMapping("insert.action")
	public String insert(@Validated EmpDoMain emp, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("dept", dsvc.getAll());
			return "insert";
		}
		svc.insert(emp);
		return getall(model);
	}

	//��@�R��
	@GetMapping("delete.action")
	public String delete(@RequestParam("empno") Integer empno, ModelMap model) {
		svc.delete(empno);
		model.addAttribute("deletesuccess", "�R�����\!");
		return getall(model);
	}

	//�I���ק�
	@GetMapping("getoneforupdate.action")
	public String getoneforupdate(@RequestParam("empno") Integer empno, ModelMap model) {
		model.addAttribute("empDoMain", svc.getOne(empno));
		return "update";
	}

	//�}�l�ק�
	@PostMapping("update.action")
	public String update(@Validated EmpDoMain emp, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "update";
		}
		svc.update(emp);
		return getall(model);
	}

	//�妸�R��
	@PostMapping("deletebatch.action")
	public String deletebatch(@RequestParam("empno") Integer[] empnos, ModelMap model) {
		if (empnos.length <= 1) {
			return getall(model);
		}
		svc.deletebatch(empnos);
		return getall(model);
	}

	//�j�M
	@PostMapping("findByCondition.action")
	public String findByCondition(EmpDoMain emp, ModelMap model) {
		if ((emp.getEname().trim().length()<=0&&emp.getEname()=="")&&(emp.getHiredate()==null)&&emp.getDeptno()==null) {
			model.addAttribute("errormsg", "�п�J���");
			return getall(model);
		} else {
			List<EmpDoMain> list = svc.findbycondition(emp);
			if (list != null) {
				model.addAttribute("emps", list);
				model.addAttribute("empDoMain", new EmpDoMain());
				model.addAttribute("dept", dsvc.getAll());
				return "ShowAll";
			} else {
				model.addAttribute("errormsg", "�d�L���");
				return getall(model);
			}
		}
	}
}
