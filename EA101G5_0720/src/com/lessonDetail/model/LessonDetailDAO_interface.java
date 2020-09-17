package com.lessonDetail.model;

import java.util.List;

import com.lesson.model.LessonVO;
import com.lessonTime.model.LessonTimeVO;



public interface LessonDetailDAO_interface {
	 
	public void insert(LessonVO LessonVO,List<LessonTimeVO> list);
	 
    public List<LessonDetailVO> findAllTimesBylessno(String lessno);
     
    public List<LessonDetailVO> getAll();
     
}
