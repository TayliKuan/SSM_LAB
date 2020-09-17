package com.activity_order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity_order.model.*;
import com.student.model.*;

public class ActivityOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ActivityOrderServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/* 查詢一筆顯示 */
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String aord_no = req.getParameter("aord_no");
				if (aord_no == null || (aord_no.trim()).length() == 0) {
					errorMsgs.add("請輸入活動訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity_order/selectActivityOrder_page.jsp");
					failureView.forward(req, res);
					return;
				}
				Activity_orderService activity_orderSvc = new Activity_orderService();
				Activity_orderVO activity_orderVO = activity_orderSvc.getOneActivityOrder(aord_no);
				if (activity_orderVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity_order/selectActivityOrder_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				req.setAttribute("activity_orderVO", activity_orderVO);
				String url = "/front-end/activity_order/listOneActivityOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity_order/selectActivityOrder_page.jsp");
				failureView.forward(req, res);
			}
		}
		/* 查詢一筆準備修改 */
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接收請求參數 */
				String aord_no = req.getParameter("aord_no");
				/* 開始查詢資料 */
				Activity_orderService activity_orderSvc = new Activity_orderService();
				Activity_orderVO activity_orderVO = activity_orderSvc.getOneActivityOrder(aord_no);
				/* 查詢完準備轉交 */
				req.setAttribute("activity_orderVO", activity_orderVO);
				String url = "/front-end/activity_order/activity_score_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity_order/activity_order_manage.jsp");
				failureView.forward(req, res);
			}
		}
		/* 修改 */
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接收請求參數 */
				String aord_no = req.getParameter("aord_no").trim();
				if (aord_no == null || aord_no.trim().length() == 0) {
					errorMsgs.add("請輸入活動訂單編號");
				}
				String actno = req.getParameter("actno").trim();
				if (actno == null || actno.trim().length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				String stuno = req.getParameter("stuno").trim();
				if (stuno == null || stuno.trim().length() == 0) {
					errorMsgs.add("請輸入學員編號");
				}
				Integer aord_sc = null;
				try {
					aord_sc = new Integer(req.getParameter("aord_sc").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("教練評價數請給含小數點並且小於5的評價");
				}
				if (aord_sc > 10.0) {
					errorMsgs.add("教練評價數請給含小數點並且小於5的評價");
				}
				
				Activity_orderVO activity_orderVO = new Activity_orderVO();
				activity_orderVO.setAord_no(aord_no);
				activity_orderVO.setActno(actno);
				activity_orderVO.setStuno(stuno);
				activity_orderVO.setAord_sc(aord_sc);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activity_orderVO", activity_orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity_order/activity_score_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/* 開始修改 */
				Activity_orderService activity_orderSvc = new Activity_orderService();
				activity_orderVO = activity_orderSvc.updateActivityOrder(aord_no, actno, stuno, aord_sc);
				/* 修改完準備轉交 */
				req.setAttribute("activity_orderVO", activity_orderVO);
				String url = "/front-end/activity_order/activity_order_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity_order/activity_score_update.jsp");
				failureView.forward(req, res);
			}
		}
		/* 新增 */
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接受請求參數 */
				String actno = req.getParameter("actno");
				String actnoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (actno == null || actno.trim().length() == 0) {
					errorMsgs.add("請輸入活動編號");
				} else if (!actno.trim().matches(actnoReg)) {
					errorMsgs.add("活動編號為大寫A開頭加上數字，且長度在4位數之間");
				}
				String stuno = req.getParameter("stuno");
				String stunoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (stuno == null || stuno.trim().length() == 0) {
					errorMsgs.add("請輸入學員編號");
				} else if (!stuno.trim().matches(stunoReg)) {
					errorMsgs.add("學員編號為大寫S開頭加上數字，且長度在4位數之間");
				}
				String point = req.getParameter("stupoint");
				int stupoint = (int)Double.parseDouble(point);
				System.out.println(stupoint);

				
				java.sql.Timestamp aord_time = new java.sql.Timestamp(System.currentTimeMillis());

				
				Activity_orderVO activity_orderVO = new Activity_orderVO();
				StuVO stuVO = new StuVO();
				activity_orderVO.setActno(actno);
				activity_orderVO.setStuno(stuno);
				activity_orderVO.setAord_time(aord_time);
				System.out.println("stupoint="+stupoint);
				stuVO.setStupoint(stupoint);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activity_orderVO", activity_orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/activity/activity_selectoneforguest.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/* 開始新增 */
				Activity_orderService activity_orderSvc = new Activity_orderService();
				activity_orderVO = activity_orderSvc.addActivityOrder(actno, stuno, aord_time, stupoint);
				req.setAttribute("activity_orderVO", activity_orderVO);
				/* 新增完準備轉交 */
//				String url = req.getContextPath()+"/front-end/activity_order/activity_order_manage.jsp";
				String url = "/front-end/activity_order/activity_order_manage.jsp";
//				req.getSession().setAttribute("actno",actno);
//				req.getSession().setAttribute("stuno",stuno);
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("insert", "報名成功");
				successView.forward(req, res);
//				res.sendRedirect(url);
//				return;

			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/activity/activity_selectoneforguest.jsp");
				failureView.forward(req, res);
			}
		}
	}
}