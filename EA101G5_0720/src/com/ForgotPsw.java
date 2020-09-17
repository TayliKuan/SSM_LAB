package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.coach.model.CoaService;
import com.coach.model.CoaVO;
import com.student.model.StuService;
import com.student.model.StuVO;
import com.utils.MailUtil;
import com.utils.StringUtil;

@WebServlet("/forgotPsw.do")
public class ForgotPsw extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("json/application; charset=UTF-8");
		PrintWriter out = res.getWriter();
		JSONObject resp = new JSONObject();
		try {
			String coamail = req.getParameter("coamail");
			String stumail = req.getParameter("stumail");
			System.out.println("coamail: " + coamail);
			System.out.println("stumail: " + stumail);
			if (coamail != null) {
				CoaService coaSvc = new CoaService();
				CoaVO coaVO = coaSvc.getOneCoaByMail(coamail);

				if (coaVO == null) {
					resp.put("error_code", "001");
					resp.put("error_msg", "信箱不存在");
				}
				// 有比對到信箱, 產生新密碼並寄信
				else {
					String coapsw = StringUtil.genRamdomStr(6);
					coaSvc.updateCoa(coaVO.getCoano(), coaVO.getCoaname(), coapsw, coaVO.getCoamail(),
							coaVO.getCoatel(), coaVO.getCoaacc(), coaVO.getCoapoint(), coaVO.getCoasta(),
							coaVO.getCoapic(), coaVO.getCoasex(), coaVO.getCoaintro(), coaVO.getCoasctotal(),
							coaVO.getCoascqty());
					MailUtil mailUtil = new MailUtil();
					mailUtil.sendMail(coaVO.getCoamail(), "您的FitMate新密碼!", "新密碼為： " + coapsw);
					resp.put("error_code", "0");
				}

			} else if (stumail != null) {
				StuService stuSvc = new StuService();
				StuVO stuVO = stuSvc.getOneStuByMail(stumail);

				if (stuVO == null) {
					resp.put("error_code", "001");
					resp.put("error_msg", "信箱不存在");
				}
				// 有比對到信箱, 產生新密碼並寄信
				else {
					String stupsw = StringUtil.genRamdomStr(6);
					stuSvc.updateStu(stuVO.getStuno(), stuVO.getStuname(), stupsw, stuVO.getStumail(),
							stuVO.getStutel(), stuVO.getStuadd(), stuVO.getStupoint(), stuVO.getStusta(),
							stuVO.getStusex(), stuVO.getStubir(), stuVO.getStupic());
					MailUtil mailUtil = new MailUtil();
					mailUtil.sendMail(stuVO.getStumail(), "您的FitMate新密碼!", "新密碼為： " + stupsw);
					resp.put("error_code", "0");
				}
			}
			out.print(resp.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}