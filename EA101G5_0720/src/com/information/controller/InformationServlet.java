package com.information.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.information.model.*;

public class InformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 顯示一則消息
		if ("getOne_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String inno = req.getParameter("inno");
				InformationService infSvc = new InformationService();
				InformationVO infVO = infSvc.getOneInf(inno);

				req.setAttribute("infVO", infVO);
				String url = "/back-end/information/showOneInformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/information/information_select_page.jsp");
				failView.forward(req, res);
			}
		}
		// 新增一則消息
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 錯誤處理
			try {
				java.sql.Date indate = null;
				try {
					indate = java.sql.Date.valueOf(req.getParameter("indate").trim());
				} catch (IllegalArgumentException e) {
					indate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入消息發布日期");
				}

				String intitle = req.getParameter("intitle");
				if (intitle == null || intitle.trim().length() == 0) {
					errorMsgs.add("標題不可空白");
				} 

				String indesc = req.getParameter("indesc");				
				if (indesc == null || indesc.trim().length() == 0) {
					errorMsgs.add("內容不可空白");
				}
				
				String intype = req.getParameter("intype");
				if (intype == null || intype.trim().length() == 0) {
					errorMsgs.add("請選擇一個消息類別");
				}
				InformationVO infVO = new InformationVO();
				
				infVO.setIndate(indate);
				infVO.setIntitle(intitle);
				infVO.setIndesc(indesc);
				infVO.setIntype(intype);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("infVO", infVO);
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/information/insertOneInformation.jsp");
					failView.forward(req, res);
					return;
				}
				// 用service呼叫dao包裝VO送給jsp
				InformationService infSvc = new InformationService();
				infVO = infSvc.addInf(indate, intype ,intitle, indesc);

				String url = "/back-end/information/showAllInformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/information/information_select_page.jsp");
				failView.forward(req, res);
			}
		}
		// for跳轉到修改頁面
		if ("alterOneInf".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String inno = req.getParameter("inno");
				InformationService infSvc = new InformationService();
				InformationVO infVO = infSvc.getOneInf(inno);

				req.setAttribute("infVO", infVO);
				String url = "/back-end/information/alterOneInformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/information/information_select_page.jsp");
				failView.forward(req, res);
			}
		}
		// for正式修改
		if ("alter".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String inno = req.getParameter("inno");
			
				java.sql.Date indate = null;
				try {
					indate = java.sql.Date.valueOf(req.getParameter("indate"));
				} catch (IllegalArgumentException e) {
					indate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入消息發布日期");
				}
				
				String intitle = req.getParameter("intitle");
				if (intitle == null || intitle.trim().length() == 0) {
					errorMsgs.add("標題不可空白");
				} 
				
				String indesc = req.getParameter("indesc");				
				if (indesc == null || indesc.trim().length() == 0) {
					errorMsgs.add("內容不可空白");
				}
				
				String intype = req.getParameter("intype");
				if (intype == null || intype.trim().length() == 0) {
					errorMsgs.add("請選擇一個消息類別");
				}
				
				InformationVO infVO = new InformationVO();
				infVO.setInno(inno);
				infVO.setIntype(intype);
				infVO.setIndate(indate);
				infVO.setIntitle(intitle);
				infVO.setIndesc(indesc);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("infVO", infVO);
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/information/alterOneInformation.jsp");
					failView.forward(req, res);
					return;
				}

				InformationService infSvc = new InformationService();
				infVO = infSvc.alterInf(indate, intype , intitle, indesc , inno);
				
				req.setAttribute("infVO", infVO);
				String url = "/back-end/information/showOneInformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/information/information_select_page.jsp");
				failView.forward(req, res);
			}
		}

		if ("deleteOneInf".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String inno = req.getParameter("inno");

				InformationService infSvc = new InformationService();
				infSvc.byeInf(inno);

				String url = "/back-end/information/showAllInformation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/information/information_select_page.jsp");
				failView.forward(req, res);
			}
		}
	}
}
