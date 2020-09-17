package com.coach.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.activity_order.model.Activity_orderService;
import com.activity_order.model.Activity_orderVO;
import com.coach.model.CoaService;
import com.coach.model.CoaVO;
import com.expertise.model.ExpService;
import com.expertise.model.ExpVO;
import com.expertise_own.model.ExpOwnService;
import com.expertise_own.model.ExpOwnVO;
import com.lesson.model.LessonService;
import com.lesson.model.LessonVO;
import com.lesson_order.model.Lesson_orderService;
import com.lesson_order.model.Lesson_orderVO;
import com.utils.MailUtil;
import com.utils.StringUtil;

import sun.misc.BASE64Encoder;

@MultipartConfig
public class CoaServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		try {
			CoaService coaSvc = new CoaService();
			ExpService expService = new ExpService();
			ExpOwnService expOwnService = new ExpOwnService();
			List<CoaVO> list = coaSvc.getAll();

			JSONArray jsonArray = new JSONArray();
			for (CoaVO coaVO : list) {
				JSONObject jSONObject = new JSONObject();
				jSONObject.put("coano", coaVO.getCoano());
				jSONObject.put("coaname", coaVO.getCoaname());
				jSONObject.put("coapsw", coaVO.getCoapsw());
				jSONObject.put("coamail", coaVO.getCoamail());
				jSONObject.put("coatel", coaVO.getCoatel());
				jSONObject.put("coaacc", coaVO.getCoaacc());
				jSONObject.put("coapoint", coaVO.getCoapoint());
				jSONObject.put("coasta", coaVO.getCoasta());
				jSONObject.put("coapic", coaVO.getCoapic());
				jSONObject.put("coasex", coaVO.getCoasex());
				jSONObject.put("coaintro", coaVO.getCoaintro());
				List<String> expdescs = new ArrayList<String>();
				List<ExpOwnVO> expOwnVOs = expOwnService.getExpOwnsByCoano(coaVO.getCoano());
				for (ExpOwnVO expOwnVO : expOwnVOs) {
					ExpVO expVO = expService.getOneExp(expOwnVO.getExpno());
					expdescs.add(expVO.getExpdesc());
				}
				jSONObject.put("expdescs", expdescs);

				// calculate coach total score
				HashMap<String, Integer> lessonScoreHashMap = getLessonScoreByCoano(coaVO.getCoano());
				Integer totalLessonCount = lessonScoreHashMap.get("totalLessonCount");
				Integer totalLessonScore = lessonScoreHashMap.get("totalLessonScore");

				// calculate coach total score
				HashMap<String, Integer> activityScoreHashMap = getActivityScoreByCoano(coaVO.getCoano());
				Integer totalActivityCount = activityScoreHashMap.get("totalActivityCount");
				Integer totalActivityScore = activityScoreHashMap.get("totalActivityScore");

				Integer totalCount = totalLessonCount + totalActivityCount;
				Integer totalScore = totalLessonScore + totalActivityScore;
				jSONObject.put("coasctotal", totalCount == 0 ? 0 : totalScore / totalCount);
				jSONObject.put("coascqty", totalCount);

				jsonArray.put(jSONObject);
			}
			out.print(jsonArray.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	private HashMap<String, Integer> getLessonScoreByCoano(String coano) {
		// calculate coach total score
		Integer totalLessonCount = 0;
		Integer totalLessonScore = 0;
		LessonService lessonService = new LessonService();
		Lesson_orderService lesson_orderService = new Lesson_orderService();
		// get all lessons under this coach
		List<LessonVO> lessonVOs = lessonService.getCoachLesson(coano);
		for (LessonVO lessonVO : lessonVOs) {
			// get all orders under this lesson
			List<Lesson_orderVO> lessonOrderVOs = lesson_orderService.getfindByLessno(lessonVO.getLessno());
			// summarize score
			for (Lesson_orderVO lesson_orderVO : lessonOrderVOs) {
				// only grater than 0 means already rate
				if (lesson_orderVO.getLord_sc() == 0) {
					continue;
				}
				totalLessonCount += 1;
				totalLessonScore += lesson_orderVO.getLord_sc();
			}
		}
		HashMap<String, Integer> resp = new HashMap<String, Integer>();
		resp.put("totalLessonCount", totalLessonCount);
		resp.put("totalLessonScore", totalLessonScore);
		return resp;
	}

	private HashMap<String, Integer> getActivityScoreByCoano(String coano) {
		// calculate coach total score
		Integer totalActivityCount = 0;
		Integer totalActivityScore = 0;
		ActivityService activityService = new ActivityService();
		Activity_orderService activity_orderService = new Activity_orderService();
		// get all activities under this coach
		List<ActivityVO> activityVOs = activityService.getAllReservation(coano);
		for (ActivityVO activityVO : activityVOs) {
			// get all orders under this activity
			List<Activity_orderVO> activityOrderVOs = activity_orderService.findStudentByactno(activityVO.getActno());
			// summarize score
			for (Activity_orderVO activity_orderVO : activityOrderVOs) {
				// only grater than 0 means already rate
				if (activity_orderVO.getAord_sc() == 0) {
					continue;
				}
				totalActivityCount += 1;
				totalActivityScore += activity_orderVO.getAord_sc().intValue();
			}
		}
		HashMap<String, Integer> resp = new HashMap<String, Integer>();
		resp.put("totalActivityCount", totalActivityCount);
		resp.put("totalActivityScore", totalActivityScore);
		return resp;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 新增一筆教練資料
		if ("insert".equals(action)) { // 來自addCoach.jsp的請求
			Map<String, String> errorMsgs = new HashMap<String, String>();
			CoaService coaSvc = new CoaService();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String coaname = req.getParameter("coaname").trim();
				System.out.println("coaname: " + coaname);
				String coanameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (coaname == null || coaname.trim().length() == 0) {
					errorMsgs.put("coaname", "教練姓名: 請勿空白！");
				} else if (!coaname.trim().matches(coanameReg)) {
					errorMsgs.put("coaname", "教練姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間！");
				}

				String coapsw = StringUtil.genRamdomStr(6);
				System.out.println("coapsw: " + coapsw);

				String coamail = req.getParameter("coamail").trim();
				System.out.println("coamail: " + coamail);
				String coamailRege = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
				if (coamail == null || coamail.trim().length() == 0) {
					errorMsgs.put("coamail", "信箱: 請勿空白！");
				} else if (!coamail.matches(coamailRege)) {
					errorMsgs.put("coamail", "信箱: 輸入格式不正確，前後必需包含@和字母或(和)數字！");
				} else if (coaSvc.getOneCoaByMail(coamail) != null) {
					errorMsgs.put("coamail", "信箱: 信箱重複註冊！");
				}

				String coatel = req.getParameter("coatel").trim();
				System.out.println("coatel: " + coatel);
				String coatelRege = "^09[0-9]{8}$";
				if (coatel == null || coatel.trim().length() == 0) {
					errorMsgs.put("coatel", "手機號碼: 請勿空白！");
				} else if (!coatel.matches(coatelRege)) {
					errorMsgs.put("coatel", "手機號碼: 必需是09開頭，共10個數字！");
				}

				String coaacc = req.getParameter("coaacc").trim();
				System.out.println("coaacc: " + coaacc);
				String coaaccRege = "^[0-9]{12,14}$";
				if (coaacc == null || coaacc.trim().length() == 0) {
					errorMsgs.put("coaacc", "匯款帳戶: 請勿空白！");
				} else if (!coaacc.matches(coaaccRege)) {
					errorMsgs.put("coaacc", "匯款帳戶: 必需是12~14個數字！");
				}

				InputStream picIn = null;
				byte[] coapic = new byte[0];
				try {
					Part picPart = req.getPart("coapic");
					picIn = picPart.getInputStream();
					coapic = new byte[picIn.available()];
					picIn.read(coapic);
				} catch (NullPointerException e) {
					errorMsgs.put("coapic", "請上傳一張照片！");
				} finally {
					picIn.close();
				}

				String coasex = req.getParameter("coasex");
				System.out.println("coasex: " + coasex);

				String coaintro = req.getParameter("coaintro").trim();
				System.out.println("coaintro: " + coaintro);
				if (coaintro == null || coaintro.trim().length() == 0) {
					errorMsgs.put("coaintro", "自我介紹: 請輸入內容！");
				}

				// TODO Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {// 如果有任何錯誤訊息
					CoaVO coaVO = new CoaVO();

					coaVO.setCoaname(coaname);
					coaVO.setCoapsw(coapsw);
					coaVO.setCoamail(coamail);
					coaVO.setCoatel(coatel);
					coaVO.setCoaacc(coaacc);
					coaVO.setCoapic(coapic);
					coaVO.setCoasex(coasex);
					coaVO.setCoaintro(coaintro);

					// 給前端接收的值
					req.setAttribute("coaVO", coaVO);
					req.setAttribute("errorMsgs", errorMsgs);
					// 用來給alert辨認新增是否成功，失敗為1以及失敗訊息
					req.setAttribute("update_error_code", "1");
					req.setAttribute("update_error_msg", "輸入資料格式有誤！");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/addCoach.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				String coano = coaSvc.addCoa(coaname, coapsw, coamail, coatel, coaacc, coapic, coasex, coaintro);

				ExpOwnService expOwnService = new ExpOwnService();
				Integer expNumber = 1;
				while (req.getParameter("expno" + expNumber.toString()) != null) {
					// insert expown
					String expno = req.getParameter("expno" + expNumber.toString());
					Part expownPart = req.getPart("expown" + expNumber.toString());
					InputStream expownIn = expownPart.getInputStream();
					byte[] expown = new byte[expownIn.available()];
					expownIn.read(expown);
					expownIn.close();
					expOwnService.addExpOwn(coano, expno, expown);
					expNumber++;
				}

				MailUtil mailUtil = new MailUtil();
				mailUtil.sendMail(coamail, "Fitmate教練身份註冊成功!", "您的FitMate密碼為： " + coapsw + "\t" + "提醒您,教練資格驗證需1-3天,驗證完成後才能使用相關功能,請耐心等待");

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 用來給alert辨認新增是否成功，成功為0以及成功訊息
				String url = "/front-end/index.jsp";
				req.getSession().setAttribute("update_error_code", "0");
				req.getSession().setAttribute("update_error_msg", "註冊成功！");
				res.sendRedirect(req.getContextPath() + url);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("update_error_code", "1");
				req.setAttribute("update_error_msg", "輸入資料格式有誤！");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/addCoach.jsp");
				failureView.forward(req, res);
			}
		}
		// 修改頁面展示一筆教練資料
		else if ("getOne".equals(action) || "getOneForBackend".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String coano = (String) req.getParameter("coano");

				/*************************** 2.開始查詢資料 ****************************************/
				// get coach data
				BASE64Encoder encoder = new BASE64Encoder();
				CoaService coaSvc = new CoaService();
				CoaVO coaVO = coaSvc.getOneCoa(coano);
				coaVO.setCoapicStr(encoder.encode(coaVO.getCoapic()));
				// get expertise data
				ExpOwnService expOwnService = new ExpOwnService();
				List<ExpOwnVO> expOwnVOs = expOwnService.getExpOwnsByCoano(coano);
				ExpService expService = new ExpService();
				for (ExpOwnVO expOwnVO : expOwnVOs) {
					ExpVO expVO = expService.getOneExp(expOwnVO.getExpno());
					expOwnVO.setExpdesc(expVO.getExpdesc());

					// encode bytes to base64 for display purpose
					expOwnVO.setExpownStr(encoder.encode(expOwnVO.getExpown()));
				}

				// calculate coach total score
				HashMap<String, Integer> lessonScoreHashMap = getLessonScoreByCoano(coano);
				Integer totalLessonCount = lessonScoreHashMap.get("totalLessonCount");
				Integer totalLessonScore = lessonScoreHashMap.get("totalLessonScore");

				// calculate coach total score
				HashMap<String, Integer> activityScoreHashMap = getActivityScoreByCoano(coano);
				Integer totalActivityCount = activityScoreHashMap.get("totalActivityCount");
				Integer totalActivityScore = activityScoreHashMap.get("totalActivityScore");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("coaVO", coaVO); // 資料庫取出的coaVO物件,存入req
				req.setAttribute("expOwnVOs", expOwnVOs);
				req.setAttribute("totalLessonCount", totalLessonCount);
				req.setAttribute("totalLessonAvg", totalLessonCount == 0 ? 0 : totalLessonScore / totalLessonCount);
				req.setAttribute("totalActivityCount", totalActivityCount);
				req.setAttribute("totalActivityAvg",
						totalActivityCount == 0 ? 0 : totalActivityScore / totalActivityCount);

// TODO 課表要用這兩段教練才看得到自己的課表 (對到timetable)
				HttpSession session = req.getSession();
				session.setAttribute("coano", coaVO.getCoano());

				String url = "getOne".equals(action) ? "/front-end/coach/listOneCoach_ForStudent.jsp"
						: "/back-end/coach/listOneCoach.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("updateSuccessMsg", "修改成功！");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				String url = "getOne".equals(action) ? "/front-end/coach/listAllCoach_ForStudent.jsp"
						: "/back-end/coach/listAllCoach.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		// for ajax
		else if ("BackendUpdate".equals(action)) {
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			String coano = (String) req.getParameter("coano");
			String coasta = (String) req.getParameter("coasta");
			try {
				CoaService coaSvc = new CoaService();
				CoaVO coaVO = coaSvc.getOneCoa(coano);
				coaSvc.updateCoa(coano, coaVO.getCoaname(), coaVO.getCoapsw(), coaVO.getCoamail(), coaVO.getCoatel(),
						coaVO.getCoaacc(), coaVO.getCoapoint(), coasta, coaVO.getCoapic(), coaVO.getCoasex(),
						coaVO.getCoaintro(), coaVO.getCoasctotal(), coaVO.getCoascqty());
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error_code", "0");
				out.print(jsonObject.toString());
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
		}
		// for submit form
		else if ("update".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String coaname = req.getParameter("coaname").trim();
				System.out.println("coaname: " + coaname);
				String coanameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (coaname == null || coaname.trim().length() == 0) {
					errorMsgs.put("coaname", "教練姓名: 請勿空白！");
				} else if (!coaname.trim().matches(coanameReg)) {
					errorMsgs.put("coaname", "教練姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間！");
				}

				String coamail = req.getParameter("coamail").trim();
				System.out.println("coamail: " + coamail);
				String coamailRege = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
				if (coamail == null || coamail.trim().length() == 0) {
					errorMsgs.put("coamail", "信箱: 請勿空白！");
				} else if (!coamail.matches(coamailRege)) {
					errorMsgs.put("coamail", "信箱: 輸入格式不正確，前後必需包含@和字母或(和)數字！");
				}

				String coatel = req.getParameter("coatel").trim();
				System.out.println("coatel: " + coatel);
				String coatelRege = "^09[0-9]{8}$";
				if (coatel == null || coatel.trim().length() == 0) {
					errorMsgs.put("coatel", "手機號碼: 請勿空白！");
				} else if (!coatel.matches(coatelRege)) {
					errorMsgs.put("coatel", "手機號碼: 必需是09開頭，共10個數字！");
				}

				String coaacc = req.getParameter("coaacc").trim();
				System.out.println("coaacc: " + coaacc);
				String coaaccRege = "^[0-9]{12,14}$";
				if (coaacc == null || coaacc.trim().length() == 0) {
					errorMsgs.put("coaacc", "匯款帳戶: 請勿空白！");
				} else if (!coaacc.matches(coaaccRege)) {
					errorMsgs.put("coaacc", "匯款帳戶: 必需是12~14個數字！");
				}

				InputStream picIn = null;
				byte[] coapic = new byte[0];
				try {
					Part picPart = req.getPart("coapic");
					picIn = picPart.getInputStream();
					coapic = new byte[picIn.available()];
					picIn.read(coapic);
				} catch (NullPointerException e) {
					errorMsgs.put("coapic", "請上傳一張照片！");
				} finally {
					picIn.close();
				}

				String coasex = req.getParameter("coasex");
				System.out.println("coasex: " + coasex);

				String coaintro = req.getParameter("coaintro").trim();
				System.out.println("coaintro:" + coaintro);
				if (coaintro == null || coaintro.trim().length() == 0) {
					errorMsgs.put("coaintro", "自我介紹: 請勿空白！");
				}

				CoaService coaSvc = new CoaService();
				String coano = req.getParameter("coano").trim();
				CoaVO coaVO = coaSvc.getOneCoa(coano);

				String coapswOld = req.getParameter("coapswOld").trim();
				String coapswNew = req.getParameter("coapswNew").trim();
				String coapswConfirm = req.getParameter("coapswConfirm").trim();
				System.out.println("coapswOld: " + coapswOld);
				System.out.println("coapswNew: " + coapswNew);
				System.out.println("coapswConfirm: " + coapswConfirm);
				String coapswRege = "^[A-Za-z0-9]{6,10}$";
				if (!"".equals(coapswOld) || !"".equals(coapswNew) || !"".equals(coapswConfirm)) {
					if (!coapswOld.equals(coaVO.getCoapsw())) {
						errorMsgs.put("coapswOld", "舊密碼不正確!");
					}
					if (!coapswNew.matches(coapswRege)) {
						errorMsgs.put("coapswNew", "密碼只能是大寫或小寫英文字母和數字 , 且長度必需在6到10之間！");
					}
					if (!coapswNew.equals(coapswConfirm)) {
						errorMsgs.put("coapswConfirm", "新密碼與再次確認新密碼輸入不一致！");
					}
				}

				// TODO Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {// 如果有任何錯誤訊息
					coaVO = new CoaVO();

					coaVO.setCoaname(coaname);
					coaVO.setCoamail(coamail);
					coaVO.setCoatel(coatel);
					coaVO.setCoaacc(coaacc);
					coaVO.setCoapic(coapic);
					coaVO.setCoasex(coasex);
					coaVO.setCoaintro(coaintro);

					req.setAttribute("coaVO", coaVO); // 含有輸入格式錯誤的coaVO物件,也存入req
					req.setAttribute("errorMsgs", errorMsgs); // 傳入錯誤訊息並跳轉頁面
					req.setAttribute("update_error_code", "1");
					req.setAttribute("update_error_msg", "輸入資料格式有誤！");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/updateCoach.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始更新資料 ***************************************/
				ExpOwnService expOwnService = new ExpOwnService();

				String deleteExpnos = req.getParameter("deleteExpnos");
				System.out.println("deleteExpnos: " + deleteExpnos);
				if (deleteExpnos != null) {
					for (String expno : deleteExpnos.split(",")) {
						expOwnService.deleteExp(coano, expno);
					}
				}

				String coasta = coaVO.getCoasta();
				Integer expNumber = 1;
				while (req.getParameter("expno" + expNumber.toString()) != null) {
					// insert expown
					String expno = req.getParameter("expno" + expNumber.toString());
					Part expownPart = req.getPart("expown" + expNumber.toString());
					InputStream expownIn = expownPart.getInputStream();
					byte[] expown = new byte[expownIn.available()];
					expownIn.read(expown);
					if (expown.length > 0) {
						// insert expOwn only if picture uploaded
						expOwnService.addExpOwn(coano, expno, expown);
						coasta = "未授權"; // update coasta
					}
					expownIn.close();
					expNumber++;
				}

				coaSvc.updateCoa(coano, coaname, "".equals(coapswNew) ? coaVO.getCoapsw() : coapswNew, coamail, coatel,
						coaacc, coaVO.getCoapoint(), coasta, coapic.length == 0 ? coaVO.getCoapic() : coapic, coasex,
						coaintro, coaVO.getCoasctotal(), coaVO.getCoascqty());

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/coach/updateCoach.jsp";
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
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/coach/updateCoach.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
