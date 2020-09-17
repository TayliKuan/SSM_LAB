package com.activity.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.activity.model.*;
import com.expertise.model.*;


@MultipartConfig
public class ActivityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ActivityServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/* 訪客查詢一筆顯示 */
		if ("getOne_For_Guest".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接收請求參數 */
				String actno = req.getParameter("actno");
				System.out.println(actno);
				if (actno == null || (actno.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectallforguest.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/* 開始查詢資料 */
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(actno);
				if (activityVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectallforguest.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/* 查詢完準備轉交 */
				req.setAttribute("activityVO", activityVO);
				String url = "/front-end/activity/activity_selectoneforguest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectallforguest.jsp");
				failureView.forward(req, res);
			}
		}

		/* 主揪查詢一筆準備修改 */
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/* 接收請求參數 */
				String actno = req.getParameter("actno");
				System.out.println(actno);
				/* 開始查詢資料 */
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(actno);
				/* 查詢完準備轉交 */
				req.setAttribute("activityVO", activityVO);
				String url = "/front-end/activity/activity_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectallforhost.jsp");
				failureView.forward(req, res);
			}
		}

		/* 主揪修改活動 */
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接收請求參數 */
				String actno = req.getParameter("actno").trim();
				System.out.println(actno);
				String actnoReg = "^[A]{1}\\d{3}$";
				if (actno == null || actno.trim().length() == 0) {
					errorMsgs.add("請輸入活動編號");
				} else if (!actno.trim().matches(actnoReg)) {
					errorMsgs.add("活動編號為大寫A開頭加上數字，且長度在4位數之間");
				}
				String actname = req.getParameter("actname");
				System.out.println(actname);
				if (actname == null || actname.trim().length() == 0) {
					errorMsgs.add("請輸入活動名稱");
				}
				String actloc = req.getParameter("actloc");
				System.out.println(actloc);
				if (actloc == null || actloc.trim().length() == 0) {
					errorMsgs.add("請輸入活動地點");
				}
				java.sql.Date actdate = null;
				try {
					actdate = java.sql.Date.valueOf(req.getParameter("actdate"));
					System.out.println(actdate);
				} catch (IllegalArgumentException ie) {
					actdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動日期");
				}
				String actss = req.getParameter("actss");
				System.out.println(actss);
				if (actss == null || actss.trim().length() == 0) {
					errorMsgs.add("請輸入活動時段");
				}
				java.sql.Date actstart = null;
				try {
					actstart = java.sql.Date.valueOf(req.getParameter("actstart"));
					System.out.println(actstart);
				} catch (IllegalArgumentException ie) {
					actstart = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始報名日期");
				}
				java.sql.Date actend = null;
				try {
					actend = java.sql.Date.valueOf(req.getParameter("actend"));
					System.out.println(actend);
				} catch (IllegalArgumentException ie) {
					actend = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入截止報名日期");
				}
				String acttype = req.getParameter("acttype");
				System.out.println(acttype);

				if (acttype == null || acttype.trim().length() == 0) {
					errorMsgs.add("請輸入活動類型");
				}
				Integer actprice = null;
				try {
					actprice = new Integer(req.getParameter("actprice").trim());
					System.out.println(actprice);
				} catch (NumberFormatException e) {
					actprice = 0;
					errorMsgs.add("活動點數請輸入數字");
				}
				Integer actmin = null;
				try {
					actmin = new Integer(req.getParameter("actmin"));
					System.out.println(actmin);
				} catch (NumberFormatException e) {
					errorMsgs.add("活動最小成團人數請輸入數字");
				}
				Integer actmax = null;
				try {
					actmax = new Integer(req.getParameter("actmax"));
					System.out.println(actmax);
				} catch (NumberFormatException e) {
					errorMsgs.add("活動最大上限人數請輸入數字");
				}
				if (actmin > actmax) {
					errorMsgs.add("最小成團不得超過最大上限人數");
				}

				String actdesc = req.getParameter("actdesc");
				System.out.println(actdesc);

				String actsta = req.getParameter("actsta");
				System.out.println(actsta);

				byte[] actpic = null;
				Part part = req.getPart("actpic");
				InputStream in = part.getInputStream();
				if (in.available() > 0) {
					actpic = new byte[in.available()];
					in.read(actpic);
					in.close();
				} else {
					ActivityService activitySvc = new ActivityService();
					ActivityVO activityVO = activitySvc.getOneActivity(actno);
					actpic = activityVO.getActpic();
				}
				String stuno = req.getParameter("stuno");
				System.out.println(stuno);
				String stunoReg = "^[S]{1}\\d{3}$";
				if (stuno == null || stuno.trim().length() == 0) {
					errorMsgs.add("請輸入學員編號");
				} else if (!stuno.trim().matches(stunoReg)) {
					errorMsgs.add("學員編號為大寫S開頭加上數字，且長度在4位數之間");
				}
				String coano = req.getParameter("coano");
				System.out.println(coano);
				String coanoReg = "^[C]{1}\\d{3}$";
				if (coano == null || coano.trim().length() == 0) {
					errorMsgs.add("請輸入教練編號");
				} else if (!coano.trim().matches(coanoReg)) {
					errorMsgs.add("教練編號為大寫C開頭加上數字，且長度在4位數之間");
				}
				ActivityVO activityVO = new ActivityVO();
				activityVO.setActno(actno);
				activityVO.setActname(actname);
				activityVO.setActloc(actloc);
				activityVO.setActdate(actdate);
				activityVO.setActss(actss);
				activityVO.setActstart(actstart);
				activityVO.setActend(actend);
				activityVO.setActtype(acttype);
				activityVO.setActprice(actprice);
				activityVO.setActmin(actmin);
				activityVO.setActmax(actmax);
				activityVO.setActdesc(actdesc);
				activityVO.setActsta(actsta);
				activityVO.setActpic(actpic);
				activityVO.setStuno(stuno);
				activityVO.setCoano(coano);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/* 開始修改 */
				ActivityService activitySvc = new ActivityService();
				activityVO = activitySvc.updateActivity(actno, actname, actloc, actdate, actss, actstart, actend,
						acttype, actprice, actmin, actmax, actdesc, actsta, actpic, stuno, coano);
				/* 修改完準備轉交 */
				req.setAttribute("activityVO", activityVO);
				String url = "/front-end/activity/activity_selectallforhost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("update","修改成功");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_update.jsp");
				failureView.forward(req, res);
			}
		}

		/* 主揪建立活動 */
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接收請求參數 */
				String actname = req.getParameter("actname");
				System.out.println(actname);
				if (actname == null || actname.trim().length() == 0) {
					errorMsgs.add("請輸入活動名稱");
				}
				String actloc = req.getParameter("actloc");
				System.out.println(actloc);
				if (actloc == null || actloc.trim().length() == 0) {
					errorMsgs.add("請輸入活動地點");
				}
				java.sql.Date actdate = null;
				try {
					actdate = java.sql.Date.valueOf(req.getParameter("actdate"));
					System.out.println(actdate);
				} catch (IllegalArgumentException ie) {
					actdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動日期");
				}
				String actss = req.getParameter("actss");
				System.out.println(actss);
				if (actss == null || actss.trim().length() == 0) {
					errorMsgs.add("請輸入活動時段");
				}
				java.sql.Date actstart = null;
				try {
					actstart = java.sql.Date.valueOf(req.getParameter("actstart"));
					System.out.println(actstart);
				} catch (IllegalArgumentException ie) {
					actstart = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始報名日期");
				}
				java.sql.Date actend = null;
				try {
					actend = java.sql.Date.valueOf(req.getParameter("actend"));
					System.out.println(actend);
				} catch (IllegalArgumentException ie) {
					actend = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入截止報名時段");
				}
				String acttype = req.getParameter("acttype");
				System.out.println(acttype);
				if (acttype == null || acttype.trim().length() == 0) {
					errorMsgs.add("請輸入活動類型");
				}
				Integer actprice = null;
				try {
					actprice = new Integer(req.getParameter("actprice").trim());
					System.out.println(actprice);
				} catch (NumberFormatException e) {
					actprice = 0;
					errorMsgs.add("活動點數請輸入數字");
				}
				Integer actmin = null;
				try {
					actmin = new Integer(req.getParameter("actmin").trim());
					System.out.println(actmin);
				} catch (NumberFormatException e) {
					errorMsgs.add("活動最小成團人數請輸入數字");
				}
				Integer actmax = null;
				try {
					actmax = new Integer(req.getParameter("actmax").trim());
					System.out.println(actmax);
				} catch (NumberFormatException e) {
					errorMsgs.add("活動最大上限人數請輸入數字");
				}
				if (actmin > actmax) {
					errorMsgs.add("最小成團不得超過最大上限人數");
				}
				String actdesc = req.getParameter("actdesc");
				System.out.println(actdesc);
				
				Part part = req.getPart("actpic");
				InputStream in = part.getInputStream();
				System.out.println(in.available());
				byte[] actpic = new byte[in.available()];
				in.read(actpic);
				in.close();

				String stuno = req.getParameter("stuno");
				System.out.println(stuno);
				String stunoReg = "^[S]{1}\\d{3}$";
				if (stuno == null || stuno.trim().length() == 0) {
					errorMsgs.add("請輸入學員編號");
				} else if (!stuno.trim().matches(stunoReg)) {
					errorMsgs.add("學員編號為大寫S開頭加上數字，且長度在4位數之間");
				}
				String coano = req.getParameter("coano");
				System.out.println(coano);
				String coanoReg = "^[C]{1}\\d{3}$";
				if (coano == null || coano.trim().length() == 0) {
					errorMsgs.add("請輸入教練編號");
				} else if (!coano.trim().matches(coanoReg)) {
					errorMsgs.add("教練編號為大寫C開頭加上數字，且長度在4位數之間");
				}

				ActivityVO activityVO = new ActivityVO();
				activityVO.setActname(actname);
				activityVO.setActloc(actloc);
				activityVO.setActdate(actdate);
				activityVO.setActss(actss);
				activityVO.setActstart(actstart);
				activityVO.setActend(actend);
				activityVO.setActtype(acttype);
				activityVO.setActprice(actprice);
				activityVO.setActmin(actmin);
				activityVO.setActmax(actmax);
				activityVO.setActdesc(actdesc);
				activityVO.setActpic(actpic);
				activityVO.setStuno(stuno);
				activityVO.setCoano(coano);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_create.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/* 開始新增 */
				ActivityService activitySvc = new ActivityService();
				activityVO = activitySvc.addActivity(actname, actloc, actdate, actss, actstart, actend, acttype,
						actprice, actmin, actmax, 0, actdesc, "待預約", actpic, stuno, coano);
				req.setAttribute("activityVO", activityVO);
				/* 新增完準備轉交 */
				String url = "/front-end/activity/activity_selectallforhost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("insert","新增成功");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_create.jsp");
				failureView.forward(req, res);
			}
		}
		/* 刪除 */
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接收請求參數 */
				String actno = req.getParameter("actno");
				System.out.println(actno);
				/* 開始刪除資料 */
				ActivityService activitySvc = new ActivityService();
				activitySvc.deleteActivity(actno);
				/* 刪除完準備轉交 */
				String url = "/front-end/activity/activity_selectallforhost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectallforhost.jsp");
				failureView.forward(req, res);
			}
		}
		
		/* 教練確認預約 */
		if ("checkstatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String coano = req.getParameter("coano");
				System.out.println(coano);
				
				String actno = req.getParameter("actno");
				System.out.println(actno);

				ActivityService activitySvc = new ActivityService();
				activitySvc.update_sta_bycoach(coano,actno);
				String url = "/front-end/activity/activity_selectallforcoach.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("checkstatus","預約確認成功");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("更新狀態失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectallforcoach.jsp");
				failureView.forward(req, res);
			}
		}
		/*主揪上架活動同時報名*/
		if ("listing".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				String stuno = req.getParameter("stuno");
				System.out.println(stuno);
				String actno = req.getParameter("actno");
				System.out.println(actno);

				ActivityService activitySvc = new ActivityService();
				activitySvc.update_sta_byhost(stuno,actno);
//				req.setAttribute("stuno",stuno);
				
				String url = "/front-end/activity/activity_selectallforhost.jsp";
				
//				req.getSession().setAttribute("stuno",stuno);
//				res.sendRedirect(url);
//				return;
//				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("listing","上架成功");
				successView.forward(req, res);
				
				
				
			} catch (Exception e) {
				errorMsgs.add("更新狀態失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectallforhost.jsp");
				failureView.forward(req, res);
			}
		}
		/*主揪下架活動(系統也能自動下架活動)*/
		if ("offactivity".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String actno = req.getParameter("actno");
				System.out.println(actno);

				ActivityService activitySvc = new ActivityService();
				activitySvc.update_sta_auto(actno);
				String url = "/front-end/activity/activity_selectallforhost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("更新狀態失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectallforhost.jsp");
				failureView.forward(req, res);
			}		
			}
			
		if("getOneType".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String acttype=req.getParameter("acttype");
				System.out.println(acttype);
			
				ActivityService activitySvc = new ActivityService();
				List<ActivityVO> activityVO =  activitySvc.findByActType(acttype);
				String url = "/front-end/activity/activity_class.jsp";
				req.setAttribute("activityVO", activityVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("狀態失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_class.jsp");
				failureView.forward(req, res);
			}		
			}
		
		if("update_actprice_for_sale".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String actno=req.getParameter("actno");
				System.out.println(actno);
				
				Integer	actprice = new Integer(req.getParameter("actprice"));
				System.out.println(actprice);
				
				ActivityService activitySvc = new ActivityService();
				activitySvc.update_actprice(actprice, actno);
				String url = "/front-end/activity/activity_selectallforguest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("狀態失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectallforguest.jsp");
				failureView.forward(req, res);
			}		
			}
			
			

	}
}
