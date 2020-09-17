package com.employee.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.employee.model.*;

@MultipartConfig
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		// 找一個員工
		if ("getOne_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 永續層存取
				String empno = req.getParameter("empno");
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneEmp(empno);

				// 轉送到view,要記得set attribute
				req.setAttribute("empVO", empVO);
				String url = "/back-end/employee/showOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/employee/showAllEmployee.jsp");
				failView.forward(req, res);
			}
		}
		// 新增員工
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String ename = req.getParameter("ename");
				String enameCheck = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$"; // 含中文英文數字的正規表示法
				if (ename == null || ename.trim().length() == 0) {
					errorMsgs.add("姓名不可留白");
				} else if (!ename.trim().matches(enameCheck)) {
					errorMsgs.add("姓名格式錯誤,請輸入長度10以內的中英文");
				}

				String eacc = req.getParameter("eacc");
				String eaccCheck = "^[(a-zA-Z0-9)]{1,20}$"; // only英文數字的正規表示法
				if (eacc == null || eacc.trim().length() == 0) {
					errorMsgs.add("帳號不可留白");
				} else if (!eacc.trim().matches(eaccCheck)) {
					errorMsgs.add("帳號格式錯誤,請輸入長度10以內的英數字");
				}

				// 日期處理
				java.sql.Date edate = null;
				try {
					edate = java.sql.Date.valueOf(req.getParameter("edate"));
				} catch (IllegalArgumentException e) {
					edate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入員工雇用日期");
				}
				// 圖片處理
				Part part = req.getPart("epic");
				InputStream is = part.getInputStream();
				byte[] epic = new byte[is.available()];
				is.read(epic);

				String esta = req.getParameter("esta");

				String email = req.getParameter("email");
				String emailCheck = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("信箱不可留白");
				} else if (!email.trim().matches(emailCheck)) {
					errorMsgs.add("信箱格式錯誤,請重新輸入");
				}

				EmployeeVO empVO = new EmployeeVO();
				empVO.setEname(ename);
				empVO.setEacc(eacc);
				String epsw = getRandomString();
				empVO.setEpsw(epsw);
				empVO.setEmail(email);
				empVO.setEdate(edate);
				empVO.setEpic(epic);
				empVO.setEsta(esta);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO);
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/employee/insertOneEmp.jsp");
					failView.forward(req, res);
					return;
				}

				EmployeeService empSvc = new EmployeeService();
				empVO = empSvc.addEmp(ename, eacc, epsw , email , edate, epic, esta);
				
				//準備信件內容
				String subject = "FitMate新進員工帳號密碼通知信件";
				String messageText = "FitMate員工 "+ename+" 您好,您的後台系統登入帳號為 "+eacc+" 密碼為 "+epsw+" ,請務必保管好此信件,謝謝";
				//送信
				MailService mSvc = new MailService();
				mSvc.sendMail(email, subject, messageText);

				req.setAttribute("empVO", empVO);
				String url = "/back-end/employee/showAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/employee/showAllEmployee.jsp");
				failView.forward(req, res);
			}
		}
		// for修改員工資料(按鈕,抓到empno後跳轉到另一隻jsp做修改)
		// 正式修改功能會在另一層控制
		if ("alterOneEmp".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String empno = req.getParameter("empno");
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneEmp(empno);
				System.out.println(empVO.getEmpno());
				req.setAttribute("empVO", empVO);
				String url = "/back-end/employee/alterOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/employee/showAllEmployee.jsp");
				failView.forward(req, res);
			}
		}
		// 修改頁面輸入資料確認&更新後展示在showAllEmp
		if ("alter".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String empno = req.getParameter("empno");
				System.out.println(empno);
				String ename = req.getParameter("ename");
				String enameCheck = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$"; // 含中文英文數字的正規表示法
				if (ename == null || ename.trim().length() == 0) {
					errorMsgs.add("姓名不可留白");
				} else if (!ename.trim().matches(enameCheck)) {
					errorMsgs.add("姓名格式錯誤,請輸入長度10以內的中英文");
				}

				String eacc = req.getParameter("eacc");
//				String eaccCheck = "^[(a-zA-Z0-9)]{1,20}$"; // only英文數字的正規表示法
//				if (eacc == null || eacc.trim().length() == 0) {
//					errorMsgs.add("帳號不可留白");
//				} else if (!eacc.trim().matches(eaccCheck)) {
//					errorMsgs.add("帳號格式錯誤,請輸入長度10以內的英數字");
//				}
//
				String epsw = req.getParameter("epsw");
//				String epswCheck = "^[(a-zA-Z0-9_)]{1,20}$";
//				if (epsw == null || epsw.trim().length() == 0) {
//					errorMsgs.add("密碼不可留白");
//				} else if (!epsw.trim().matches(epswCheck)) {
//					errorMsgs.add("密碼格式錯誤,請輸入長度10以內的英數字");
//				}
				java.sql.Date edate = null;
				try {
					edate = java.sql.Date.valueOf(req.getParameter("edate"));
				} catch (IllegalArgumentException e) {
					edate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入員工雇用日期");
				}
				// 有圖給新圖,沒圖撈舊圖
				byte[] epic;
				Part part = req.getPart("epic");
				InputStream is = part.getInputStream();

				if (is.available() > 0) {
					epic = new byte[is.available()];
					is.read(epic);
					is.close();

				} else {
					EmployeeService Svc = new EmployeeService();
					EmployeeVO VO = Svc.getOneEmp(empno);
					epic = VO.getEpic();
				}

				String esta = req.getParameter("esta");

				String email = req.getParameter("email");
				String emailCheck = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("信箱不可留白");
				} else if (!email.trim().matches(emailCheck)) {
					errorMsgs.add("信箱格式錯誤,請重新輸入");
				}

				EmployeeVO empVO = new EmployeeVO();
				empVO.setEmpno(empno);
				empVO.setEname(ename);
				empVO.setEacc(eacc);
				empVO.setEpsw(epsw);
				empVO.setEmail(email);
				empVO.setEpic(epic);
				empVO.setEdate(edate);
				empVO.setEsta(esta);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO);
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/employee/alterOneEmp.jsp");
					failView.forward(req, res);
					return;
				}

				EmployeeService empSvc = new EmployeeService();
				empVO = empSvc.alterEmp(ename, eacc, epsw, email, edate, epic, esta, empno);

				req.setAttribute("empVO", empVO);
				String url = "/back-end/employee/showOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/employee/showAllEmployee.jsp");
				failView.forward(req, res);
			}
		}
		// 刪除按鈕
		if ("deleteOneEmp".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String empno = req.getParameter("empno");

				EmployeeService empSvc = new EmployeeService();
				empSvc.byeEmp(empno);

				String url = "/back-end/employee/showAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/employee/showAllEmployee.jsp");
				failView.forward(req, res);
			}

		}
		// 忘記密碼查詢
		if ("forgetPsw".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String eacc = req.getParameter("eacc");
				String eaccCheck = "^[(a-zA-Z0-9)]{1,20}$"; // only英文數字的正規表示法
				if (eacc == null || eacc.trim().length() == 0) {
					errorMsgs.add("帳號不可留白");
				} else if (!eacc.trim().matches(eaccCheck)) {
					errorMsgs.add("帳號格式錯誤,請輸入長度10以內的英數字");
				}

				String email = req.getParameter("email");
				String emailCheck = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("信箱不可留白");
				} else if (!email.trim().matches(emailCheck)) {
					errorMsgs.add("信箱格式錯誤,請重新輸入");
				}

				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.forgetPsw(eacc, email);
				forgetPsw(empVO);

				req.setAttribute("empVO", empVO);
				String url = "/back-end/backend_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/forgetPsw.jsp");
				failView.forward(req, res);
			}
		}
		// 登入驗證
		if ("logincheck".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				String eacc = req.getParameter("eacc");
				String eaccCheck = "^[(a-zA-Z0-9)]{1,20}$";
				if (eacc == null || eacc.trim().length() == 0) {
					errorMsgs.add("帳號不可留白");
				} else if (!eacc.trim().matches(eaccCheck)) {
					errorMsgs.add("帳號格式錯誤,請輸入長度10以內的英數字");
				}
				String epsw = req.getParameter("epsw");
				if (epsw == null || epsw.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.loginCheck(eacc);
				String epswCheck = empVO.getEpsw();
				
				if(!epsw.matches(epswCheck)) {
					errorMsgs.add("密碼錯誤,拒絕登入");
					res.sendRedirect(req.getContextPath() + "/back-end/loginFail.jsp");
				} else {
					HttpSession session = req.getSession();
					session.setAttribute("empVO", empVO);	//含有名字帳號密碼狀態的VO
					try {
						String location = (String) session.getAttribute("location");
						if(location!=null) {
							session.removeAttribute("location");
							res.sendRedirect(location);
							return ;
						}
					} catch (Exception ignored) {}
					res.sendRedirect(req.getContextPath()+ "/back-end/backend_index.jsp");
				}
		}
		//登出(移除session中的資料)
		if("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("empVO");
			session.invalidate();
			String url = "/back-end/backend_index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
	
	//生成隨機密碼
	public String getRandomString() {
		String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for(int i = 0 ; i <10 ; i++) {
			int number=random.nextInt(62);
			sf.append(str.charAt(number));
		}
		return sf.toString();
	}
	
	public void forgetPsw (EmployeeVO empVO) {
		String ename = empVO.getEname();
		String to = empVO.getEmail();			
		String epsw = empVO.getEpsw();
		String subject = "忘記密碼通知信件";
		String messageText = "FitMate員工 "+ename+" 您好,您的後台系統登入密碼為 "+epsw+" ,請務必保管好此信件,謝謝";
		
		MailService mSvc = new MailService();
		mSvc.sendMail(to, subject, messageText);
	}
}


