package com.student.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.student.model.StuService;
import com.student.model.StuVO;
import com.utils.MailUtil;
import com.utils.StringUtil;

@MultipartConfig
public class StuServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 新增一筆學員資料
		if ("insert".equals(action)) { // 來自addStudent.jsp的請求
			Map<String, String> errorMsgs = new HashMap<String, String>();
			StuService stuSvc = new StuService();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String stuname = req.getParameter("stuname").trim();
				System.out.println("stuname: " + stuname);
				String stunameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (stuname == null || stuname.trim().length() == 0) {
					errorMsgs.put("stuname", "姓名: 請勿空白！");
				} else if (!stuname.trim().matches(stunameReg)) {
					errorMsgs.put("stuname", "姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間！");
				}

				String stupsw = StringUtil.genRamdomStr(6);
				System.out.println("stupsw: " + stupsw);

				String stumail = req.getParameter("stumail").trim();
				System.out.println("stumail: " + stumail);
				String stumailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (stumail == null || stumail.trim().length() == 0) {
					errorMsgs.put("stumail", "信箱: 請勿空白！");
				} else if (!Pattern.matches(stumailReg, stumail)) {
					errorMsgs.put("stumail", "信箱: 輸入格式不正確，前後必需包含@和字母或(和)數字！");
				} else if (stuSvc.getOneStuByMail(stumail) != null) {
					errorMsgs.put("stumail", "信箱: 信箱重複註冊！");
				}

				String stutel = req.getParameter("stutel").trim();
				System.out.println("coatel: " + stutel);
				String stutelRege = "^09[0-9]{8}$";
				if (stutel == null || stutel.trim().length() == 0) {
					errorMsgs.put("stutel", "手機號碼: 請勿空白！");
				} else if (!stutel.matches(stutelRege)) {
					errorMsgs.put("stutel", "手機號碼: 必需是09開頭，共10個數字！");
				}

				InputStream picIn = null;
				byte[] stupic = new byte[0];
				try {
					Part picPart = req.getPart("stupic");
					picIn = picPart.getInputStream();
					stupic = new byte[picIn.available()];
					picIn.read(stupic);
				} catch (NullPointerException e) {
					errorMsgs.put("stupic", "請上傳一張照片！");
				} finally {
					picIn.close();
				}

				String stusex = req.getParameter("stusex");
				System.out.println("stusex: " + stusex);

				String zipcode = req.getParameter("zipcode").trim();
				String city = req.getParameter("city").trim();
				String town = req.getParameter("town").trim();
				String stuadd = req.getParameter("stuadd").trim();
				stuadd = zipcode + city + town + stuadd;
				System.out.println("stuadd: " + stuadd);
				if (stuadd == null || stuadd.trim().length() == 0) {
					errorMsgs.put("stuadd", "地址: 請輸入詳細地址！");
				}

				java.sql.Date stubir = null;
				try {
					stubir = java.sql.Date.valueOf(req.getParameter("stubir").trim());
				} catch (IllegalArgumentException e) {
					stubir = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("stubir", "生日: 請輸入正確日期!");

				}

				// TODO Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {// 如果有任何錯誤訊息
					StuVO stuVO = new StuVO();

					stuVO.setStuname(stuname);
					stuVO.setStupsw(stupsw);
					stuVO.setStumail(stumail);
					stuVO.setStutel(stutel);
					stuVO.setStupic(stupic);
					stuVO.setStusex(stusex);
					stuVO.setStuadd(stuadd);
					stuVO.setStubir(stubir);

					req.setAttribute("stuVO", stuVO);
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("update_error_code", "1");
					req.setAttribute("update_error_msg", "輸入資料格式有誤！");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/student/addStudent.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				StuVO stuno = stuSvc.addStu(stuname, stupsw, stumail, stutel, stupic, stusex, stuadd, stubir);

				MailUtil mailUtil = new MailUtil();
				mailUtil.sendMail(stumail, "Fitmate學員身份註冊成功!", "您的FitMate密碼為： " + stupsw);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/index.jsp";
				req.getSession().setAttribute("update_error_code", "0");
				req.getSession().setAttribute("update_error_msg", "註冊成功！");
				res.sendRedirect(req.getContextPath() + url);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("other errors", e.getMessage());
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("update_error_code", "1");
				req.setAttribute("update_error_msg", "輸入資料格式有誤！");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/student/addStudent.jsp");
				failureView.forward(req, res);
			}
		}

		// for submit form
		else if ("update".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String stuname = req.getParameter("stuname").trim();
				System.out.println("stuname: " + stuname);
				String stunameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (stuname == null || stuname.trim().length() == 0) {
					errorMsgs.put("stuname", "姓名: 請勿空白！");
				} else if (!stuname.trim().matches(stunameReg)) {
					errorMsgs.put("stuname", "姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間！");
				}

				String stupsw = StringUtil.genRamdomStr(6);
				System.out.println("stupsw: " + stupsw);

				String stumail = req.getParameter("stumail").trim();
				System.out.println("stumail: " + stumail);
				String stumailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (stumail == null || stumail.trim().length() == 0) {
					errorMsgs.put("stumail", "信箱: 請勿空白！");
				} else if (!Pattern.matches(stumailReg, stumail)) {
					errorMsgs.put("stumail", "信箱: 輸入格式不正確，前後必需包含@和字母或(和)數字！");
				}

				String stutel = req.getParameter("stutel").trim();
				System.out.println("coatel: " + stutel);
				String stutelRege = "^09[0-9]{8}$";
				if (stutel == null || stutel.trim().length() == 0) {
					errorMsgs.put("stutel", "手機號碼: 請勿空白！");
				} else if (!stutel.matches(stutelRege)) {
					errorMsgs.put("stutel", "手機號碼: 必需是09開頭，共10個數字！");
				}

				InputStream picIn = null;
				byte[] stupic = new byte[0];
				try {
					Part picPart = req.getPart("stupic");
					picIn = picPart.getInputStream();
					stupic = new byte[picIn.available()];
					picIn.read(stupic);
				} catch (NullPointerException e) {
					errorMsgs.put("stupic", "請上傳一張照片！");
				} finally {
					picIn.close();
				}

				String stusex = req.getParameter("stusex");
				System.out.println("stusex: " + stusex);

				String stuadd = req.getParameter("stuadd");
				System.out.println("stuadd: " + stuadd);
				if (stuadd == null || stuadd.trim().length() == 0) {
					errorMsgs.put("stuadd", "地址: 請輸入詳細地址！");
				}

				java.sql.Date stubir = null;
				try {
					stubir = java.sql.Date.valueOf((String) req.getParameter("stubir").trim());
				} catch (IllegalArgumentException e) {
					stubir = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("stubir", "生日: 請輸入日期!");
				}

				StuService stuSvc = new StuService();
				String stuno = req.getParameter("stuno").trim();
				StuVO stuVO = stuSvc.getOneStu(stuno);

				String stupswOld = req.getParameter("stupswOld").trim();
				String stupswNew = req.getParameter("stupswNew").trim();
				String stupswConfirm = req.getParameter("stupswConfirm").trim();
				System.out.println("stupswOld: " + stupswOld);
				System.out.println("stupswNew: " + stupswNew);
				System.out.println("stupswConfirm: " + stupswConfirm);
				String stupswRege = "^[A-Za-z0-9]{6,10}$";
				if (!"".equals(stupswOld) || !"".equals(stupswNew) || !"".equals(stupswConfirm)) {
					if (!stupswOld.equals(stuVO.getStupsw())) {
						errorMsgs.put("stupswOld", "輸入的舊密碼錯誤!");
					}
					if (!stupswNew.matches(stupswRege)) {
						errorMsgs.put("stupswNew", "密碼只能是大寫或小寫英文字母和數字 , 且長度必需在6到10之間!");
					}
					if (!stupswNew.equals(stupswConfirm)) {
						errorMsgs.put("stupswConfirm", "新密碼與再次確認新密碼輸入不一致！");
					}
				}

				// TODO Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {// 如果有任何錯誤訊息
					stuVO.setStuname(stuname);
					stuVO.setStupsw(stupsw);
					stuVO.setStumail(stumail);
					stuVO.setStutel(stutel);
					stuVO.setStupic(stupic);
					stuVO.setStusex(stusex);
					stuVO.setStuadd(stuadd);
					stuVO.setStubir(stubir);

					req.setAttribute("stuVO", stuVO);
					req.setAttribute("errorMsgs", errorMsgs);
					req.setAttribute("update_error_code", "1");
					req.setAttribute("update_error_msg", "輸入資料格式有誤！");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/student/updateStudent.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始更新資料 ***************************************/
				stuSvc.updateStu(stuno, stuname, "".equals(stupswNew) ? stuVO.getStupsw() : stupswNew, stumail, stutel,
						stuadd, stuVO.getStupoint(), stuVO.getStusta(), stusex, stubir, stupic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/student/updateStudent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("update_error_code", "0");
				req.setAttribute("update_error_msg", "修改成功！");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("other errors", e.getMessage());
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("update_error_code", "1");
				req.setAttribute("update_error_msg", "輸入資料格式有誤！");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/student/updateStudent.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
