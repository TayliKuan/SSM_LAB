package com.lesson_fav.model;

import java.util.List;


public class Lesson_favService {
	
	private Lesson_favDAO_interface dao;
	
	public Lesson_favService() {
		dao = new Lesson_favJDBCDAO();
	}
	
	public Lesson_favVO addLesson_fav(String stuno, String lessno) {
		Lesson_favVO lesson_favVO = new Lesson_favVO();
	
		lesson_favVO.setStuno(stuno);
		lesson_favVO.setLessno(lessno);	

		dao.insert(lesson_favVO);
		
		return lesson_favVO;
	
	}
	
	public void deleteLessno_fav(String stuno ,String lessno) {
		dao.delete(stuno, lessno);

	}
		
	public List<Lesson_favVO> getfindByLessno( String lessno) {
		return dao.getfindByLessno(lessno);
	}
	
	public List<Lesson_favVO> getfindByStuno( String stuno) {
		return dao.getfindByStuno(stuno);
	}

	public List<Lesson_favVO> getAll() {
		return dao.getAll();
	}
	

}
