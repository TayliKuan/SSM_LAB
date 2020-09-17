package com.lesson.model;

import java.util.*;

import org.json.JSONArray;

import com.expertise.model.ExpVO;

public interface LessonDAO_interface {
	
	/*新增一堂課程*/
	public String insert(LessonVO lessonVO);
	
	/*修改課程*/
	public void update(LessonVO lessonVO);
	
	/*此運動種類有多少課程 用類型查*/
	public List<LessonVO> findLessonByLessonType(String lesstype);
	
	/*查此運動類型的詳細描述 要用到EXPERTISE的VO*/
	public List<ExpVO> getAllExpByExpno();
	
	/*查詢全部課程 課程總覽*/
	public List<LessonVO> getAll();
	
	/*查詢某教練的課程資訊(一對多)(回傳一串 JSONArray 放入課表用)*/
	public JSONArray getCoachAllLesson(String coano);
	
	/*用教練編號 查詢教練所有課程*/
	public List<LessonVO> getCoachLesson(String coano);
	
	/*用課程編號 查詢單一課程*/
	public LessonVO getOneByPK(String lessno);
	
	/*把課程狀態改為下架  備註:沒有刪除*/
	public void update_off(String lessno);
	
	/*查出課程時間與時段 送到控制器與前端的課程時段比對*/
	public JSONArray checkTime(String coano);
	
}
