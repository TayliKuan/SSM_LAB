package com.lessonDetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson.model.LessonService;
import com.lesson.model.LessonVO;


@WebServlet("/LessonDetailServlet")
public class LessonDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LessonDetailServlet() {
        super();
      
    }
    
    //用doGet 接收lessno跟coano參數 到lesson_detail 這共用頁面
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if("show_lesson_detail".equals(action)) {
			try {
				/***************************1.接收請求參數****************************************/
				String lessno = new String(req.getParameter("lessno"));
				String coano = new String(req.getParameter("coano"));
				/***************************2.開始查詢資料****************************************/
				LessonService lessonSvc = new LessonService();
				LessonVO lessonVO = lessonSvc.getOneByPK(lessno);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("lessonVO", lessonVO);

				String url ="/front-end/lesson/lesson_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理************************************/

			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
