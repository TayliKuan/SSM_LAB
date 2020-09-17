package com.deposit.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deposit.model.*;
import com.student.model.*;

public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		//查詢個人所有儲值紀錄
		if("selectAll".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String stuno = req.getParameter("stuno");
				DepositService depSvc = new DepositService();
				List<DepositVO> deplist = depSvc.showAllDep(stuno);
				
				req.setAttribute("deplist", deplist);
				String url = "/front-end/deposit/showAllDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/deposit/deposit_index.jsp");
				failView.forward(req, res);
			}
		}
		
		//點數儲值
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
			String stuno = req.getParameter("stuno");	//input hidden
			
			Integer depprice = null ;
			try {
				depprice = new Integer(req.getParameter("depprice").trim());
			} catch (NumberFormatException e) {
				depprice = 0 ;
			}
			
			DepositVO depVO = new DepositVO();
			depVO.setStuno(stuno);
			depVO.setDepprice(depprice);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("depVO", depVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/deposit/addOneDeposit.jsp");
				failureView.forward(req, res);
				return;
			}
			//新增儲值紀錄
			DepositService depSvc = new DepositService();
			depVO = depSvc.addDeposit(stuno, depprice);
			//抓學員現有點數
			StuService stuSvc = new StuService();
			StuVO stuVO = stuSvc.getOneStu(stuno);
			Integer oldpoint = stuVO.getStupoint();
			//更新點數(+)
			Integer newpoint = oldpoint + depprice ;
			depSvc.alterStuPoint(stuno, newpoint);
			
			req.setAttribute("depVO", depVO);
			String url = "/front-end/deposit/deposit_index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/deposit/showAllDeposit.jsp");
				failView.forward(req, res);
			}
		}
		
		//頁面跳轉setAttribute
		if("goInsert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			HttpSession session = req.getSession();
			
			try{
				String stuno = req.getParameter("stuno");
				req.setAttribute("stuno", stuno);
				
				String url = "/front-end/deposit/addOneDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/deposit/deposit_index.jsp");
				failView.forward(req, res);
			}
		}
		
		
	}
	
		
}
