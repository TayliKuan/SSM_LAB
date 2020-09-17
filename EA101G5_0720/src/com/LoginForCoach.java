package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.coach.model.CoaService;
import com.coach.model.CoaVO;

@WebServlet("/loginForCoach.do")
public class LoginForCoach extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		// 【取得使用者 帳號(coamail) 密碼(coapsw)】
		String coamail = req.getParameter("coamail");
		String coapsw = req.getParameter("coapsw");
		System.out.println(String.format("coach login: coamail: %s, coapsw: %s", coamail, coapsw));

		CoaService coaService = new CoaService();
		CoaVO coaVO = coaService.getCoaByMailNPsw(coamail, coapsw);

		// 判斷是否登入成功index.js
		JSONObject resp = new JSONObject();
		try {
			if (coaVO == null) { // 【帳號 , 密碼無效時】

				resp.put("error_code", "1");
				resp.put("error_msg", "帳號或密碼錯誤!");
			} else { // 【帳號 , 密碼有效時, 才做以下工作】
				// *工作1: 才在session內做已經登入過的標識
				HttpSession session = req.getSession();
				session.setAttribute("coano", coaVO.getCoano());
				session.setAttribute("coaname", coaVO.getCoaname());
				session.setAttribute("role", "coach");
//				session.setAttribute("coaVO", coaVO);
				resp.put("error_code", "0");

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