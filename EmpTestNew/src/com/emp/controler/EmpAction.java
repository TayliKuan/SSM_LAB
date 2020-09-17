package com.emp.controler;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dept.model.DeptDoMain;
import com.dept.service.DeptService;
import com.emp.model.EmpDoMain;
import com.emp.service.EmpService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RequestMapping("/emp")
@Controller
public class EmpAction {

	@Autowired
	private EmpService svc;
	@Autowired
	private DeptService dsvc;

	// �d�ߥ���
	@GetMapping("getall.action")
	public String getall(ModelMap model) {
		model.addAttribute("empDoMain", new EmpDoMain());
		model.addAttribute("emps", svc.getAll());
		return "ShowAll";
	}

	// �I���s�W
	@GetMapping("add.action")
	public String add(ModelMap model) {
		EmpDoMain emp = new EmpDoMain();
		model.addAttribute("empDoMain", emp);
		model.addAttribute("dept", dsvc.getAll());
		return "insert";
	}

	// �}�l�s�W
	@PostMapping("insert.action")
	public String insert(@Validated EmpDoMain emp, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("dept", dsvc.getAll());
			return "insert";
		}
		svc.insert(emp);
		return "redirect:/emp/getall.action";
	}

	// ��@�R��
	@GetMapping("delete.action")
	public String delete(@RequestParam("empno") Integer empno, ModelMap model) {
		svc.delete(empno);
		model.addAttribute("deletesuccess", "�R�����\!");
		return getall(model);
	}

	// �I���ק�
	@GetMapping("getoneforupdate.action")
	public String getoneforupdate(@RequestParam("empno") Integer empno, ModelMap model) {
		model.addAttribute("empDoMain", svc.getOne(empno));
		return "update";
	}

	// �}�l�ק�
	@PostMapping("update.action")
	public String update(@Validated EmpDoMain emp, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "update";
		}
		svc.update(emp);
		return getall(model);
	}

	// �妸�R��
	@PostMapping("deletebatch.action")
	public String deletebatch(@RequestParam("empno") Integer[] empnos, ModelMap model) {
		if (empnos.length <= 1) {
			return getall(model);
		}
		svc.deletebatch(empnos);
		return getall(model);
	}

	// �j�M
	@PostMapping("findByCondition.action")
	public String findByCondition(EmpDoMain emp, ModelMap model) {
		if ((emp.getEname().trim().length() <= 0 && emp.getEname() == "") && (emp.getHiredate() == null)
				&& emp.getDeptno() == null) {
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

	@GetMapping("createpdf.action")
	public String createpdf(ModelMap model) throws Exception {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/2008017/Desktop/new.pdf"));
		document.open();
		document.add(new Paragraph("Hello World!"));
		document.close();
		writer.close();
		return getall(model);
	}

	// �s�W�h��

	@GetMapping("insertbatch.action")
	public String insertbatch(ModelMap model) {
		model.addAttribute("deptDoMain", new DeptDoMain());
		return "insertbatch";
	}

	@PostMapping("insertemps.action")
	public String insertemps(@RequestParam("action") String value, @Validated DeptDoMain dept, BindingResult result,
			ModelMap model, HttpServletRequest req) {
		// ���~����
		if (result.hasErrors()) {
			return "insertbatch";
		}
		if (value.equals("�s�W���u")) {
			// �S���~�}�l����
			
			//����Session
			HttpSession session = req.getSession();
			
			//�Ĥ@���i�h�A�p�Gsession�̨S���o�ӴN��i�h
			if (session.getAttribute("dept") == null) {
				dept.getEmps().add(dept.getEmp());
				session.setAttribute("dept",dept);
			} else {
				//�ĤG���H���ӴN�qsession���쳡������,��EMP�˨쳡��Domain�̪����Xemps
				DeptDoMain sdept = (DeptDoMain)session.getAttribute("dept");
				sdept.getEmps().add(dept.getEmp());
			}
			return "insertbatch";
		}
		return "insertbatch";
	}
	//------------------------------------------�o�̶}�l��-------------------------------
	// �R��
	@PostMapping("deleteEmp.action")
	public String deleteEmp(@RequestParam("index") int value, ModelMap model,
			HttpServletRequest req) {
		DeptDoMain  dept = (DeptDoMain)req.getSession().getAttribute("dept");
		//�n�R���ݭndeptno�A�Ѧҫe�ݭ����Ainsertbatch�U����value��loop.index�i�H����Emp�blist�̪���m�M��Ǧ^��
		//�q�Ǧ^�Ӫ����������󮳨�̭������X,�A���讳�쪺index�A���R��
		List<EmpDoMain> list = dept.getEmps();
		list.remove(value);
		model.addAttribute("deptDoMain",req.getSession().getAttribute("dept"));
		return "insertbatch";
	}

	// �}�l�ק�
	@PostMapping("updateEmp.action")
	public String updateEmp(@RequestParam("index") int value, ModelMap model, HttpServletRequest req) {
		
		//�qsession���쳡������,�]���n�ק�]�n����index��R����k�̼ˡA�ή��쪺index�q���X�̮��X��,�A�⥦�˨쮳�쪺��������
		//�M��]�wattribute�Ǧ^����SHOW�X��
		DeptDoMain sdept = (DeptDoMain) req.getSession().getAttribute("dept");
		EmpDoMain emp = sdept.getEmps().get(value);
		sdept.setEmp(emp);
		model.addAttribute("deptDoMain", sdept);
		return "insertbatch";
	}
	
	

}
