package com.lesson_fav.model;

import java.util.List;

//import com.lesson.model;

public interface Lesson_favDAO_interface {

		public void insert(Lesson_favVO lesson_favVO);
		public void delete(String stuno ,String lessno);		
		public List<Lesson_favVO> getfindByLessno( String lessno);//查詢一個課程有多少學生
		public List<Lesson_favVO> getfindByStuno(String stuno);//查詢一個學生有多少課程
		public List<Lesson_favVO> getAll();
		
		//public Set<LessonVO> getLessonByLesson_order(String Lessno);
		//public Set<StudentVO> getStudentByLessno(String Stunno);
}
