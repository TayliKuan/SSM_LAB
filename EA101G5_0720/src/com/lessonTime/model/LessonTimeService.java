package com.lessonTime.model;

import java.sql.Date;
import java.util.List;

import org.json.JSONArray;

public class LessonTimeService {
	private LessonTimeDAO_inrterface dao;

	public LessonTimeService() {
		dao = new LessonTimeDAO();//之後轉JNDI
	}

	public LessonTimeVO addLessonTime(java.sql.Date ltime_date, String ltime_ss ,String lessno) {

		LessonTimeVO LessonTimeVO = new LessonTimeVO();

		LessonTimeVO.setLtime_date(ltime_date);
		LessonTimeVO.setLtime_ss(ltime_ss);
		dao.insert(LessonTimeVO,lessno);

		return LessonTimeVO;

	}

	public LessonTimeVO updateLessonTime( java.sql.Date ltime_date, String ltime_ss,String ltime_no) {

		LessonTimeVO LessonTimeVO = new LessonTimeVO();

		LessonTimeVO.setLtime_date(ltime_date);
		LessonTimeVO.setLtime_ss(ltime_ss);
		LessonTimeVO.setLtime_no(ltime_no);
		dao.update(LessonTimeVO);

		return LessonTimeVO;

	}

	public void deleteLessonTime(String lessno) {
		dao.delete(lessno);
	}

	public List<LessonTimeVO> findTimeByPK(String lessno) {
		return dao.findByPrimaryKey(lessno);
	}

	public List<LessonTimeVO> getAllLessonTime() {
		return dao.getAll();
	}

	public JSONArray getCoachAllLesson(String coano) {
		return dao.getCoachAllLesson(coano);
	}
	
	public List<String> getOneTime(String lessno) {
		return dao.getOneTime(lessno);
	}
}
