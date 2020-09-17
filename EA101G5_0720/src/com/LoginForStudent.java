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

import com.student.model.StuService;
import com.student.model.StuVO;

@WebServlet("/loginForStudent.do")
public class LoginForStudent extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		// 【取得使用者 帳號(stumail) 密碼(stupsw)】
		String stumail = req.getParameter("stumail");
		String stupsw = req.getParameter("stupsw");
		System.out.println(String.format("student login: stumail: %s, stupsw: %s", stumail, stupsw));

		StuService stuService = new StuService();
		StuVO stuVO = stuService.getStuByMailNPsw(stumail, stupsw);

		JSONObject resp = new JSONObject();
		try {
			if (stuVO == null) { // 【帳號 , 密碼無效時】
				resp.put("error_code", "1");
				resp.put("error_msg", "帳號或密碼錯誤!");			
			} else { // 【帳號 , 密碼有效時, 才做以下工作】
				// *工作1: 才在session內做已經登入過的標識
				HttpSession session = req.getSession();
				session.setAttribute("stuno", stuVO.getStuno());
				session.setAttribute("stuname", stuVO.getStuname());
				session.setAttribute("role", "student");
//				session.setAttribute("stuVO", stuVO);

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