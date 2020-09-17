package com.lesson_order.model;

import java.util.List;


public class Lesson_orderService {
	
	private Lesson_orderDAO_interface dao;
	
	public Lesson_orderService() {
		dao = new Lesson_orderJDBCDAO();
	}
	
public Lesson_orderVO addLesson_order( Lesson_orderVO lesson_orderVO , int stupoint ) {
	/////	
			dao.insert(lesson_orderVO ,stupoint);
			
			return lesson_orderVO;
	
	
	
	}
	
	public void deleteLesson_order(String lord_no) {
		dao.delete(lord_no);

	}
		
	public List<Lesson_orderVO> getfindByLessno( String lessno) {
		return dao.getfindByLessno(lessno);
	}
	
	public List<Lesson_orderVO> getfindByStuno( String stuno) {
		return dao.getfindByStuno(stuno);
	}
	
	public Lesson_orderVO findByPrimaryKey( String lord_no) {
		return dao.getfindByPrimaryKey(lord_no);
	}

	public List<Lesson_orderVO> getAll() {
		return dao.getAll();
	}
	
	public Lesson_orderVO up_lesson_order_lord_sc(String lord_no, Integer lord_sc) {
		return dao.up_lesson_order_lord_sc(lord_no, lord_sc);
	}
}
