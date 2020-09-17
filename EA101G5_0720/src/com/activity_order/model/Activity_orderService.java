package com.activity_order.model;

import java.sql.Timestamp;
import java.util.List;

import com.student.model.StuVO;


public class Activity_orderService {
	private Acitivity_orderDAO_interface dao;
	
	public Activity_orderService() {
		dao = new Activity_orderJNDIDAO();
	}
	
	//新增
	public Activity_orderVO addActivityOrder(String actno,String stuno,Timestamp aord_time ,int stupoint) {
		Activity_orderVO activity_orderVO = new Activity_orderVO();
		activity_orderVO.setActno(actno);
		activity_orderVO.setStuno(stuno);
		activity_orderVO.setAord_time(aord_time);
		activity_orderVO.setAord_sc(0);

		StuVO stuVO = new StuVO();
		stuVO.setStupoint(stupoint);
		
		dao.insert(activity_orderVO,stupoint);
		return activity_orderVO;
	}
	
	//修改
	public Activity_orderVO updateActivityOrder(String aord_no,String actno,String stuno,Integer aord_sc) {
		Activity_orderVO activity_orderVO = new Activity_orderVO();
		activity_orderVO.setAord_no(aord_no);
		activity_orderVO.setActno(actno);
		activity_orderVO.setStuno(stuno);
		activity_orderVO.setAord_sc(aord_sc);
		dao.update(activity_orderVO);
		return activity_orderVO;
	}
	
	//刪除
	public void deleteActivityOrder(String aord_no) {
		dao.delete(aord_no);
	}
	
	//單一查詢
	public Activity_orderVO getOneActivityOrder(String aord_no) {
		return dao.findByPrimaryKey(aord_no);
	}
	
	//全部查詢
	public List<Activity_orderVO> getAllActivityOrder() {
		return dao.getAll();
	}
	//單一查詢由活動編號查有多少學員報名
	public List<Activity_orderVO> findStudentByactno(String actno){
		return dao.findStudentByactno(actno);
	}
	//單一查詢由學員編號查報名多少活動
	public List<Activity_orderVO> findActivityBystuno(String stuno){
		return dao.findActivityBystuno(stuno);
	}
	//更改活動教練評價(Ajax)
	public Activity_orderVO update_activity_order_aord_sc(String aord_no, Integer aord_sc) {
		return dao.update_activity_order_aord_sc(aord_no, aord_sc); 
	}
	
		
	
}
