package tw.gov.bli.user.controller;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tw.gov.bli.dept.service.DeptService;
import tw.gov.bli.log.logTest;
import tw.gov.bli.user.domain.User;
import tw.gov.bli.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/user")
public class UserController{//就是servlet

	private static Logger logger = LoggerFactory.getLogger(logTest.class);

	
	//控制器呼叫 Service
	//注入 spring(service與DAO) 即可以用
	@Autowired
	private UserService userService;

	
//------------------------------------------------------------------------------------------------------------------//
	//查全部
		@RequestMapping("/user_findAll.action")
		public String findAll(Model model) {
			//用service的方法
			List<User> list = userService.findAll();
			model.addAttribute("list",list);
			model.addAttribute("Condition",new User());
			//轉JSP的名字
			return "listAll";
		}
//------------------------------------------------------------------------------------------------------------------//	
	//進入新增page
		@RequestMapping("/user_insert_enter.action")
		public String insert_enter(Model model) {
			model.addAttribute("userCase",new User());
			return "insert";
		}
//------------------------------------------------------------------------------------------------------------------//		
	//新增
		@PostMapping("/user_insert.action")
		public ModelAndView insert(@Valid User user,BindingResult result) {
			if(result.hasErrors()) {
				ModelAndView  model = new ModelAndView("insert");    
				   return model;
			}
			userService.insert(user);
			ModelAndView  model = new ModelAndView("redirect:/user/user_findAll.action");    
				return model;
			}
//------------------------------------------------------------------------------------------------------------------//	
	//進入修改page
		@RequestMapping("/user_findByPrimaryKey.action")
		public String findByPrimaryKey(String uno,Model model) {
			User aUser = userService.findByPrimaryKey(uno);
			model.addAttribute("user",aUser);//從form:form過來的 modelAttribute 空的
			return "update";
		}
//------------------------------------------------------------------------------------------------------------------//	
	//修改
		@PostMapping("/user_update.action")
		public ModelAndView update(@Valid User user,BindingResult result,String uno) {
			if(result.hasErrors()) {
				ModelAndView  model = new ModelAndView("update");  //forward
//				((Model) model).addAttribute("userCase",userCase);//error msg
				model.addObject("user", user);//error msg
				return model;
			}
			userService.update(user);
			ModelAndView  model = new ModelAndView("redirect:/user/user_findAll.action");   //重導就不會刷新 再新增 
			return model;
		}
//------------------------------------------------------------------------------------------------------------------//		
	//刪除一個
		@RequestMapping("/user_delete.action")
		public String delete(@Valid User user,BindingResult result,String uno,Model model) {
			userService.delete(uno);
			return findAll( model);
		}	
//------------------------------------------------------------------------------------------------------------------//		
	//批次刪除
		@RequestMapping("/user_deleteMany.action")
		public String deleteMany(@Valid User user,BindingResult result,HttpServletRequest request,Model model) {
			String[] unoArray = request.getParameterValues("deleteMany");
			if(unoArray.length==1) {
		        logger.debug("請選擇要刪除的用戶");

//				System.out.println("請選擇要刪除的用戶");
			}else {
				for(int i = 0;i<unoArray.length;i++) {
					userService.delete(unoArray[i]);
				}
			}
			return findAll( model);
		}	
//------------------------------------------------------------------------------------------------------------------//		
	//複合查詢
		@RequestMapping("/user_findUserByCondition.action")
		public String findUserByCondition(User user ,Model model,String birthday) throws ParseException {
			
			user.setUsername(user.getUsername());
			user.setSex(user.getSex());
			user.setUserid(user.getUserid());
			try {
				user.setBirthday(java.sql.Date.valueOf(birthday));
			} catch (Exception e) {
				user.setBirthday(null);
			}
			model.addAttribute("Condition",user);
			//用service的方法
			List<User> list = userService.findUserByCondition(user);
			model.addAttribute("list",list);
			System.out.println("list.size()="+list.size());
			return "listAll";
		}	
//------------------------------------------------------------------------------------------------------------------//		
	//產PDF
		@RequestMapping("/user_pdf.action")
		public String pdf(Model model) throws Exception {
			List<User> list = userService.findAll();

	    	logger.debug("list.size()=", list.size());
	    	
	        // 建立一個文件（預設大小A4，邊距36, 36, 36, 36）
	        Document document = new Document();  
	        // 設定文件大小  
	        document.setPageSize(PageSize.A4);  
	        // 設定邊距，單位都是畫素，換算大約1釐米=28.33畫素  
	        document.setMargins(50, 50, 50, 50);
	        // 設定pdf生成的路徑
	        FileOutputStream fileOutputStream= new FileOutputStream("D:/printAll.pdf");
	        // 建立writer，通過writer將文件寫入磁碟
	        PdfWriter writer = PdfWriter.getInstance(document,fileOutputStream);
	        // demo
	        String title = "客戶資料管理";
	        String content = "列印 全部";
	    
	        // 定義字型  
	        FontFactoryImp ffi = new FontFactoryImp();  
	        // 註冊全部預設字型目錄，windows會自動找fonts資料夾的，返回值為註冊到了多少字型  
	        ffi.registerDirectories();  
	        // 獲取字型，其實不用這麼麻煩，後面有簡單方法  
	        Font font = ffi.getFont("標楷體",BaseFont.IDENTITY_H,BaseFont.EMBEDDED, 12, Font.UNDEFINED, null);  
	    
	        // 開啟文件，只有開啟後才能往裡面加東西  
	        document.open();  
	//    
//	        // 設定作者  
//	        document.addAuthor("這是標楷體");
//	        // 設定建立者  
//	        document.addCreator("111");
//	        // 設定主題  
//	        document.addSubject("222");
//	        // 設定標題  
//	        document.addTitle("這是標楷體");
	    
	        // 增加一個段落  
	        document.add(new Paragraph(title, font));  
	        document.add(new Paragraph(content, font));  
	        document.add(new Paragraph("\n\r", font));  
	    
	        // 建立表格，5列的表格  
	        PdfPTable table = new PdfPTable(4);  
	        PdfPTable table1 = new PdfPTable(new float[] {1, 2, 1 }); // 方法 1
	        PdfPTable table2 = new PdfPTable(4);  
	        table.setTotalWidth(PageSize.A4.getWidth()- 100);  
	        table.setLockedWidth(true);  
	        // 建立頭  
	        PdfPHeaderCell header = new PdfPHeaderCell();  
	        header.addElement(new Paragraph("測試 全部", font));  
	        header.setColspan(4);  
	        table.addCell(header);  
	        
	        // 新增內容  
	        table1.addCell(new Paragraph("1111",font));
	        table1.addCell(new Paragraph("這是標楷體",font));
	        table1.addCell(new Paragraph("6666",font));
	        table1.addCell(new Paragraph("7777",font));
	        
	        table2.addCell(new Paragraph("2",font));
	        table2.addCell(new Paragraph("222",font));
	        table2.addCell(new Paragraph("2222",font));
	        table2.addCell(new Paragraph("22222",font));
	        
	        for (int i = 0; i < list.size(); i++) {
	        	 User user = list.get(i);
	        	 table.addCell(new Paragraph(user.getUsername(),font));
	        	 table.addCell(new Paragraph(user.getSex(),font));
	        	 table.addCell(new Paragraph(user.getUserid(),font));
	        	 table.addCell(new Paragraph(user.getAddress(),font));
	        	 }
	        document.add(table);  
	        document.add(table1);  
	        document.add(table2);  
	       
	        // 關閉文件，才能輸出  
	        document.close();  
	        writer.close();
	        model.addAttribute("list",list);
			model.addAttribute("Condition",new User());
			model.addAttribute("OK","OK");
			//轉JSP的名字
			return "listAll";
			 
	   
		}

}
