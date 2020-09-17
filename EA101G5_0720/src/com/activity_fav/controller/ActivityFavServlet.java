package com.activity_fav.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.activity_fav.model.*;

public class ActivityFavServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ActivityFavServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		/* 由學員編號查詢活動追蹤 */
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String stuno = req.getParameter("stuno");
				System.out.println("stuno");
				if (stuno == null || (stuno.trim()).length() == 0) {
					errorMsgs.add("請輸入你想追蹤的活動");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity_fav/activity_favorite_manage.jsp");
					failureView.forward(req, res);
					return;

				}
				Activity_favService activity_favSvc = new Activity_favService();
				List<Activity_favVO> activity_favVO = activity_favSvc.findActivityByPrimaryKey(stuno);
				if (activity_favVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity_fav/activity_favorite_manage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				req.setAttribute("activity_favVO", activity_favVO);
				String url = "/front-end/activity_fav/activity_favorite_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/activity_fav/activity_favorite_manage.jsp");
				failureView.forward(req, res);
			}
		}
		/* 查詢追蹤活動後修改 */
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String stuno = req.getParameter("stuno");
				System.out.println("stuno");

				Activity_favService activity_favSvc = new Activity_favService();
				List<Activity_favVO> activity_favVO = activity_favSvc.findActivityByPrimaryKey(stuno);
				req.setAttribute("activity_favVO", activity_favVO);
				String url = "/front-end/activity_fav/activity_favorite_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity_fav/activity_favorite_manage.jsp");
				failureView.forward(req, res);
			}
		}
		/* 刪除追蹤*/
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接收請求參數 */
				String stuno = req.getParameter("stuno");
				System.out.println(stuno);
				if (stuno == null || stuno.trim().length() == 0) {
					errorMsgs.add("請輸入學員編號");
				}
				String actno = req.getParameter("actno");
				System.out.println(actno);
				if (actno == null || actno.trim().length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				Activity_favVO activity_favVO = new Activity_favVO();
				activity_favVO.setStuno(stuno);
				activity_favVO.setActno(actno);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activity_favVO", activity_favVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity_fav/activity_favorite_manage.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/* 開始刪除 */
				Activity_favService activity_favSvc = new Activity_favService();
				activity_favSvc.deleteActivityFav(actno, stuno);
				/* 修改完轉交 */
				req.setAttribute("activity_favVO", activity_favVO);
				String url = "/front-end/activity_fav/activity_favorite_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/activity_fav/activity_favorite_manage.jsp");
				failureView.forward(req, res);
			}
		}
		/* 新增追蹤 */
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接受請求參數 */
				String actno = req.getParameter("actno");
				System.out.println(actno);
				String actnoReg = "^[A]{1}\\d{3}$";
				if (actno == null || actno.trim().length() == 0) {
					errorMsgs.add("請輸入活動編號");
				} else if (!actno.trim().matches(actnoReg)) {
					errorMsgs.add("活動編號為大寫A開頭加上數字，且長度在4位數之間");
				}
				String stuno = req.getParameter("stuno");
				System.out.println(stuno);
				String stunoReg = "^[S]{1}\\d{3}$";
				if (stuno == null || stuno.trim().length() == 0) {
					errorMsgs.add("請輸入學員編號");
				} else if (!stuno.trim().matches(stunoReg)) {
					errorMsgs.add("學員編號為大寫S開頭加上數字，且長度在4位數之間");
				}
				Activity_favVO activity_favVO = new Activity_favVO();
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activity_favVO", activity_favVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/activity_selectoneforguest.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				Activity_favService activity_favSvc = new Activity_favService();
				activity_favVO = activity_favSvc.addActivityFav(actno, stuno);
				
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(actno);
				req.setAttribute("activityVO", activityVO);
				
				
				System.out.println(activity_favVO.getActno());
				req.setAttribute("activity_favVO", activity_favVO);
				
				String url = "/front-end/activity/activity_selectoneforguest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("insert","追蹤成功");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_selectoneforguest.jsp");
				failureView.forward(req, res);
			}
		}
	}
}