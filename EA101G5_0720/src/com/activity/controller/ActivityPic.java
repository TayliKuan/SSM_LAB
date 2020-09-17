package com.activity.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

public class ActivityPic extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String actno = req.getParameter("actno");
		HttpSession session = req.getSession();

		if (session.getAttribute(actno) == null) {
			ActivityService activitySvc = new ActivityService();
			ActivityVO activityVO = activitySvc.getOneActivity(actno);
			session.setAttribute(actno, activityVO.getActpic());
		}
		out.write((byte[]) session.getAttribute(actno));

	}

}
