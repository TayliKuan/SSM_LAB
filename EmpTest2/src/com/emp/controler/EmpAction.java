package com.emp.controler;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

 // 查詢全部
 @GetMapping("getall.action")
 public String getall(ModelMap model) {
  model.addAttribute("empDoMain", new EmpDoMain());
  model.addAttribute("emps", svc.getAll());
  return "ShowAll";
 }

 // 點擊新增
 @GetMapping("add.action")
 public String add(ModelMap model) {
  EmpDoMain emp = new EmpDoMain();
  model.addAttribute("empDoMain", emp);
  model.addAttribute("dept", dsvc.getAll());
  return "insert";
 }

 // 開始新增
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

 // 單一刪除
 @GetMapping("delete.action")
 public String delete(@RequestParam("empno") Integer empno, ModelMap model) {
  svc.delete(empno);
  model.addAttribute("deletesuccess", "刪除成功!");
  return getall(model);
 }

 // 點擊修改
 @GetMapping("getoneforupdate.action")
 public String getoneforupdate(@RequestParam("empno") Integer empno, ModelMap model) {
  model.addAttribute("empDoMain", svc.getOne(empno));
  return "update";
 }

 // 開始修改
 @PostMapping("update.action")
 public String update(@Validated EmpDoMain emp, BindingResult result, ModelMap model) {
  if (result.hasErrors()) {
   return "update";
  }
  svc.update(emp);
  return getall(model);
 }

 // 批次刪除
 @PostMapping("deletebatch.action")
 public String deletebatch(@RequestParam("empno") Integer[] empnos, ModelMap model) {
  if (empnos.length <= 1) {
   return getall(model);
  }
  svc.deletebatch(empnos);
  return getall(model);
 }

 // 搜尋
 @PostMapping("findByCondition.action")
 public String findByCondition(EmpDoMain emp, ModelMap model) {
  if ((emp.getEname().trim().length() <= 0 && emp.getEname() == "") && (emp.getHiredate() == null)
    && emp.getDeptno() == null) {
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

 // 新增多筆 進入頁面

 @GetMapping("insertbatch.action")
 public String insertbatch(ModelMap model) {
  model.addAttribute("deptDoMain", new DeptDoMain());//給他一個空的
  return "insertbatch";
 }

 @PostMapping("insertemps.action")
 public String insertemps(@RequestParam("action") String value, @Validated DeptDoMain dept, BindingResult result,
   ModelMap model, HttpServletRequest req) {
  // 錯誤驗證
  if (result.hasErrors()) {
   return "insertbatch";
  }
  if (value.equals("新增員工")) {
   // 沒錯誤開始執行
   if (req.getSession().getAttribute("emps") == null) {
    List<EmpDoMain> list = new ArrayList<>();
    list.add(dept.getEmp());
    req.getSession().setAttribute("emps", list);
    req.getSession().setAttribute("dept", dept);
   } else {
    ArrayList<EmpDoMain> list = (ArrayList) req.getSession().getAttribute("emps");
    list.add(dept.getEmp());
    req.getSession().setAttribute("dept", dept);
   }
   return "insertbatch";
  }
  return "insertbatch";
 }

 // 刪除
 @PostMapping("deleteEmp.action")
 public String deleteEmp(@RequestParam("index") int value, ModelMap model,
   HttpServletRequest req) {
  List<EmpDoMain> list = (List) req.getSession().getAttribute("emps");
  list.remove(value);
  model.addAttribute("deptDoMain", (DeptDoMain)req.getSession().getAttribute("dept"));
  return "insertbatch";
 }

 // 開始修改
 @PostMapping("updateEmp.action")
 public String updateEmp(@RequestParam("index") int value, ModelMap model, HttpServletRequest req) {
  List<EmpDoMain> list = (List) req.getSession().getAttribute("emps");
  EmpDoMain emp = list.get(value);
  DeptDoMain dept=(DeptDoMain)req.getSession().getAttribute("dept");
  dept.setEmp(emp);
  model.addAttribute("deptDoMain", dept);
  return "insertbatch";
 }
 
 

}