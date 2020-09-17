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
		System.out.println("����params����");
		//�O�^��JSP�W�r
		return "success";
	}
	
	@RequestMapping("/fileupload")
	public String fileupload(HttpServletRequest request) throws Exception {
		System.out.println("���W��");
		
		//�ϥ�fileupload jar �������W��
		//�W�Ǫ���m
		String path = request.getSession().getServletContext().getRealPath("/uploads/");
		//�P�_���|�O�_�s�b
		File file = new File(path);
		if(!file.exists()) {
			//�Ыؤ��
			file.mkdirs();
		}
		
		//�ѪR request ����W�Ǥ��
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//�ѪR request
		List<FileItem> items = upload.parseRequest(request);

		//foreach
		for(FileItem item:items) {
			if(item.isFormField()) {
				//�٦b�W��
			}else {
				//�o����W�ǦW
				String filename = item.getName();
				//�������W��
				item.write(new File(path,filename));
				//�R�����
				item.delete();
			}
		}
		return "success";
		
	}
	
	
	
	@RequestMapping("/fileupload2")
	public String fileupload2(HttpServletRequest request,MultipartFile upload) throws Exception {
		System.out.println("spring���W��");
		
		//�ϥ�fileupload jar �������W��
		//�W�Ǫ���m
		String path = request.getSession().getServletContext().getRealPath("/uploads/");
		//�P�_���|�O�_�s�b
		File file = new File(path);
		if(!file.exists()) {
			//�Ыؤ��
			file.mkdirs();
		}
		
	
		//�o����W�ǦW
		String filename = upload.getOriginalFilename();
		//����W�]�m�ߤ@ uuid
		String uuid = UUID.randomUUID().toString().replace("-", "");
		filename = uuid+"_"+filename;
		
		//���W��
		upload.transferTo(new File(path,filename));
		return "success";
		
	}
}
