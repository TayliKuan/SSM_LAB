package com.activity.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.*;

@WebServlet("/ActivityTimetableServlet")
public class ActivityTimetableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ActivityTimetableServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if("show_lesson_detail".equals(action)) {
			try {
				/***************************1.接收請求參數****************************************/
				String actno = new String(req.getParameter("actno"));
				String coano = new String(req.getParameter("coano"));
				/***************************2.開始查詢資料****************************************/
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(actno);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("activityVO", activityVO);

				String url ="/front-end/activity/activity_selectoneforguest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理************************************/

			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
	}




	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doGet(req, res);
	}

}
