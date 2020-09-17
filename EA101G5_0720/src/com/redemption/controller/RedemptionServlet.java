package com.redemption.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.activity.model.*;
import com.coach.model.*;
import com.employee.model.MailService;
import com.lesson.model.*;
import com.redemption.model.*;

public class RedemptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		//查詢個人點數兌換紀錄
		if("showAll".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				String coano = req.getParameter("coano");
				
				req.setAttribute("coano", coano);	//轉交coano到view層jsp,在view層new一個Service呼叫all給coano做查詢				
				String url = "/front-end/redemption/showAllRedemption.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/redemption/redemption_index.jsp");
				failView.forward(req, res);
			}
		}
		
		//新增點數兌換申請(預設狀態=未處理)
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String coano = req.getParameter("coano");
				Integer redprice = null ;
				try {
					redprice = new Integer(req.getParameter("redprice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("兌換點數不可為0,請重新確認");
				}
				RedemptionVO redVO = new RedemptionVO();
				redVO.setCoano(coano);
				redVO.setRedprice(redprice);		
				
				//抓教練現有點數
				CoaService coaSvc = new CoaService();
				CoaVO coaVO = coaSvc.getOneCoa(coano);
				Integer coapoint = coaVO.getCoapoint();
				//新增記錄到後台表格
				RedemptionService redSvc = new RedemptionService();
				redVO = redSvc.addRed(coano, redprice);
				//點數相減後更新到教練表格
				Integer newpoint = coapoint - redprice ;
				redSvc.alterCoaPoint(coano, newpoint);
			

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("redVO", redVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/redemption/addOneRedemption.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("redVO", redVO);
				String url = "/front-end/redemption/redemption_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/redemption/redemption_index.jsp");
				failView.forward(req, res);
			}
		}
		
		//更改兌換狀態(ajax預定)(目前form版)
		if("change".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
					
			try {
				String redno = req.getParameter("redno");
				RedemptionService redSvc = new RedemptionService();
				redSvc.alterRed(redno);
				System.out.println(redno);
				//取得redemption的教練編號&申請日期
				RedemptionVO redVO = redSvc.getCoaByRed(redno);
				String coano = redVO.getCoano();
				String reddate = new SimpleDateFormat("yyyy-mm-dd").format(redVO.getReddate());
				//取得教練VO抓name跟mail
				CoaService coaSvc = new CoaService();
				CoaVO coaVO = coaSvc.getOneCoa(coano);
				String email = coaVO.getCoamail();
				String coaname = coaVO.getCoaname();
				//準備信件內容
				String subject = "點數兌換審核通知";
				String messageText = "FitMate合作教練"+coaname+"您好,您在"+reddate+"申請的點數兌換已通過審核,金額將在數個工作日後透過您的帳戶入款,工作日將依銀行而有所不同,敬請耐心等待"
						+ "\t"+"FitMate工作團隊 敬上";
				//送信
				MailService mSvc = new MailService();
				mSvc.sendMail(email, subject, messageText);
				
				req.setAttribute("redno", redno);
				String url = "/back-end/redemption/showAllRedemptionBack.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/deposit/deposit_index.jsp");
				failView.forward(req, res);
				}
		}
		
		//跳轉兌換頁面
		if("goInsert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			HttpSession session = req.getSession();
			
			try{
				String coano = req.getParameter("coano");
				req.setAttribute("coano", coano);
				
				String url = "/front-end/redemption/addOneRedemption.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/redemption/redemption_index.jsp");
				failView.forward(req, res);
			}
		}
		
		//教練點數發放(課程)
		if("addpay".equals(action)) {
					
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
				
			try {
				String lessno = req.getParameter("lessno");
				String coano = req.getParameter("coano");
				//用課程編號取得課程VO
				LessonService lessSvc = new LessonService();
				LessonVO lessVO = lessSvc.getOneByPK(lessno);
				//用課程VO取得課程售價
				Integer salary = lessVO.getLessprice();
				CoaService coaSvc = new CoaService();
				//取得教練現有點數
				CoaVO coaVO = coaSvc.getOneCoa(coano);
				Integer oldpoint = coaVO.getCoapoint();
				//取得新點數
				Integer newpoint = oldpoint + salary ;
				//更新教練點數
				RedemptionService redSvc = new RedemptionService();
				redSvc.alterCoaPoint(coano, newpoint);
				//更新課程狀態
				redSvc.changeLessonSta(lessno);
						
				req.setAttribute("coano", coano);
				String url = "/back-end/redemption/showAllCoachPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
					
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/redemption/showAllCoachPoint.jsp");
				failView.forward(req, res);
			}
		}
		//選擇教練的課程	
		if("getOne_Display".equals(action)) {
					
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
					
			try {
				String coano = req.getParameter("coano");
				req.setAttribute("coano", coano);
				String url = "/back-end/redemption/showAllCoachPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/redemption/selectCoachForPoint.jsp");
				failView.forward(req, res);
			}
		}
			
		//選擇教練的課程	
		if("getOne_DisplayAct".equals(action)) {
					
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
					
			try {
				String coano = req.getParameter("coano");
				req.setAttribute("coano", coano);
				String url = "/back-end/redemption/showAllCoachPointAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/redemption/selectCoachForPoint.jsp");
				failView.forward(req, res);
			}
		}
		
		//教練點數發放(課程)
		if("addpayact".equals(action)) {
							
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
					
			try {
				String actno = req.getParameter("actno");
				System.out.println(actno);
				String coano = req.getParameter("coano");
				System.out.println(coano);
				//用課程編號取得課程VO
				ActivityService actSvc = new ActivityService();
				ActivityVO actVO = actSvc.getOneActivity(actno);
				//用課程VO取得課程售價
				Integer salary = actVO.getActprice();
				CoaService coaSvc = new CoaService();
				//取得教練現有點數
				CoaVO coaVO = coaSvc.getOneCoa(coano);
				Integer oldpoint = coaVO.getCoapoint();
				//取得新點數
				Integer newpoint = oldpoint + salary ;
				//更新教練點數
				RedemptionService redSvc = new RedemptionService();
				redSvc.alterCoaPoint(coano, newpoint);
				//更新課程狀態
				redSvc.changeActSta(actno);
								
				req.setAttribute("coano", coano);
				String url = "/back-end/redemption/showAllCoachPointAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
							
			} catch (Exception e) {
				errorMsgs.add("系統提示:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/redemption/selectCoachForPoint.jsp");
				failView.forward(req, res);
			}
		}
	}
	
	
}
