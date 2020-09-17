package com.lesson.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson.model.LessonService;
import com.lesson.model.LessonVO;

public class PicServletJDBC extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String lessno = req.getParameter("lessno");
			LessonService lessonSvc = new LessonService();
			LessonVO lessonVO = lessonSvc.getOneByPK(lessno);
			//把DB查出來的照片 放進byte[] 
			byte[] buf = lessonVO.getLesspic();
			out.write(buf);
		} catch (Exception e) {
			//沒有圖片的話 給預設圖片 前台顯示無圖片
			InputStream in = getServletContext().getResourceAsStream("images/NoData/null2.jpg");
			byte [] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();

		}
	}
}
