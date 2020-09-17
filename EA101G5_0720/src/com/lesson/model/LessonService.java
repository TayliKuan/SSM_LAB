package com.lesson.model;

import java.sql.Date;
import java.util.List;

import org.json.JSONArray;

import com.expertise.model.ExpVO;

public class LessonService {

	private LessonDAO_interface dao;
	
	public LessonService() {
		dao = new LessonDAO();//JNDI
	}
	
	/*新增一堂課程*/
	public String addLesson(String coano,String lessname,Integer lessmax,Integer lessmin,Integer lesscur,String lesstype,String lessloc,Integer lessprice,String lessdesc,java.sql.Date lessstart,java.sql.Date lessend,String lesssta,Integer lesstimes,byte[] lesspic) {
		LessonVO lessonVO= new LessonVO();
		
		lessonVO.setCoano(coano);
		lessonVO.setLessname(lessname);
		lessonVO.setLessmax(lessmax);
		lessonVO.setLessmin(lessmin);
		
		lessonVO.setLesscur(lesscur);
		lessonVO.setLesstype(lesstype);
		lessonVO.setLessloc(lessloc);
		lessonVO.setLessprice(lessprice);
		lessonVO.setLessdesc(lessdesc);
		
		lessonVO.setLessstart(lessstart);
		lessonVO.setLessend(lessend);
		lessonVO.setLesssta(lesssta);
		lessonVO.setLesstimes(lesstimes);
		lessonVO.setLesspic(lesspic);
		
		String lessno_seq = dao.insert(lessonVO);
		return lessno_seq;
		
	}
	
	/*修改課程*/
	public LessonVO updateLesson(String lessno, String coano,String lessname,Integer lessmax,Integer lessmin,Integer lesscur,String lesstype,String lessloc,Integer lessprice,String lessdesc,java.sql.Date lessstart,java.sql.Date lessend,String lesssta,Integer lesstimes,byte[] lesspic) {
		LessonVO lessonVO= new LessonVO();
		lessonVO.setLessno(lessno);
		lessonVO.setCoano(coano);
		lessonVO.setLessname(lessname);
		lessonVO.setLessmax(lessmax);
		lessonVO.setLessmin(lessmin);
		
		lessonVO.setLesscur(lesscur);
		lessonVO.setLesstype(lesstype);
		lessonVO.setLessloc(lessloc);
		lessonVO.setLessprice(lessprice);
		lessonVO.setLessdesc(lessdesc);
		
		lessonVO.setLessstart(lessstart);
		lessonVO.setLessend(lessend);
		lessonVO.setLesssta(lesssta);
		lessonVO.setLesstimes(lesstimes);
		lessonVO.setLesspic(lesspic);
		dao.update(lessonVO);
		return lessonVO;
	}
	
	/*此運動種類有多少課程 用類型查*/
	public List<LessonVO> findLessonByLessonType(String lesstype) {
		return dao.findLessonByLessonType(lesstype);
	}
	
	/*查此運動類型的詳細描述 要用到EXPERTISE的VO*/
	public List<ExpVO> getAllExpByExpno() {
		return dao.getAllExpByExpno();
	}
	
	/*查詢全部課程 課程總覽*/
	public List<LessonVO> getAllLesson() {
		return dao.getAll();
	}
	
	/*查詢某教練的課程資訊(一對多)(回傳一串 JSONArray 放入課表用)*/
	public JSONArray getCoachAllLesson(String coano) {
		return dao.getCoachAllLesson(coano);
	}
	
	/*用教練編號 查詢教練所有課程*/
	public List<LessonVO> getCoachLesson(String coano) {
		return dao.getCoachLesson(coano);
	}
	
	/*用課程編號 查詢單一課程*/
	public LessonVO getOneByPK(String lessno) {
		return dao.getOneByPK(lessno);
	}
	
	/*把課程狀態改為下架  備註:沒有刪除*/
	public void update_off(String lessno) {
		dao.update_off(lessno);
	}
	
	/*查出課程時間與時段 送到控制器與前端的課程時段比對*/
	public JSONArray checkTime(String coano) {
		return dao.checkTime(coano);
	}
}
