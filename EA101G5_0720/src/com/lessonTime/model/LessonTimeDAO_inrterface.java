package com.lessonTime.model;

import java.util.List;

import org.json.JSONArray;

import com.lessonDetail.model.LessonDetailVO;


public interface LessonTimeDAO_inrterface {
	
	/*新增時段*/
	public void insert(LessonTimeVO LessonTimeVO,String lessno);
	
	/*更新時段*/
	public void update(LessonTimeVO LessonTimeVO);
	
	/*下架課程(改狀態)的同時 刪除時段與時段明細*/
	public void delete(String lessno);
	
	/*查單筆 時段*/
	public List<LessonTimeVO> findByPrimaryKey(String lessno);
	
	/*查全部時段*/
	public List<LessonTimeVO> getAll();
	
	/*查詢此教練 所有課程與所有時段 送JSONArray到servlet去比對*/
	public  JSONArray getCoachAllLesson(String coano);
	
	/*查詢單筆課程所有時段*/
	public List<String> getOneTime(String lessno);
	
}
