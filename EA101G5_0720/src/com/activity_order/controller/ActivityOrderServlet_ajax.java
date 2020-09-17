package com.activity_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity_order.model.*;
import javax.servlet.*;

public class ActivityOrderServlet_ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActivityOrderServlet_ajax() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		if("action".equals(action)) {
			System.out.println("in");
		try {
			String aord_no = req.getParameter("aord_no");
			System.out.println(aord_no);
			String aord_sc = req.getParameter("aord_sc");
			System.out.println(aord_sc);

			if (aord_no == null || aord_no.trim().length() == 0) {
				errorMsgs.add("資料錯誤");
			}
			if (aord_sc == null) {
				errorMsgs.add("資料錯誤");
			}
			String no = aord_no.trim();
			int sc = Integer.parseInt(aord_sc);
			System.out.println(aord_sc);

			System.out.println(sc);

			/*************************** 2.開始修改資料 ***************************************/
//			Activity_orderVO activity_orderVO = new Activity_orderVO();
			Activity_orderService activity_orderSvc = new Activity_orderService();
			activity_orderSvc.update_activity_order_aord_sc(no, sc);

			PrintWriter out = res.getWriter();

			out.println(no);

		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("");
			failureView.forward(req, res);
		}
	}
	}

}
