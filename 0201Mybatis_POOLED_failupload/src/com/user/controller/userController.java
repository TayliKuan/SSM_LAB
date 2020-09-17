package com.user.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class userController {
	
	@RequestMapping(value="/testparams",params= {"name"})
	public String testparams() {
		System.out.println("測試params註解");
		//是回傳JSP名字
		return "success";
	}
	
	@RequestMapping("/fileupload")
	public String fileupload(HttpServletRequest request) throws Exception {
		System.out.println("文件上傳");
		
		//使用fileupload jar 完成文件上傳
		//上傳的位置
		String path = request.getSession().getServletContext().getRealPath("/uploads/");
		//判斷路徑是否存在
		File file = new File(path);
		if(!file.exists()) {
			//創建文件夾
			file.mkdirs();
		}
		
		//解析 request 獲取上傳文件
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//解析 request
		List<FileItem> items = upload.parseRequest(request);

		//foreach
		for(FileItem item:items) {
			if(item.isFormField()) {
				//還在上傳
			}else {
				//得到文件上傳名
				String filename = item.getName();
				//完成文件上傳
				item.write(new File(path,filename));
				//刪除文件
				item.delete();
			}
		}
		return "success";
		
	}
	
	
	
	@RequestMapping("/fileupload2")
	public String fileupload2(HttpServletRequest request,MultipartFile upload) throws Exception {
		System.out.println("spring文件上傳");
		
		//使用fileupload jar 完成文件上傳
		//上傳的位置
		String path = request.getSession().getServletContext().getRealPath("/uploads/");
		//判斷路徑是否存在
		File file = new File(path);
		if(!file.exists()) {
			//創建文件夾
			file.mkdirs();
		}
		
	
		//得到文件上傳名
		String filename = upload.getOriginalFilename();
		//把文件名設置唯一 uuid
		String uuid = UUID.randomUUID().toString().replace("-", "");
		filename = uuid+"_"+filename;
		
		//文件上傳
		upload.transferTo(new File(path,filename));
		return "success";
		
	}
}
