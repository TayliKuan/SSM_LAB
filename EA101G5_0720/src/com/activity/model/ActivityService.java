package com.activity.model;

import java.sql.Date;
import java.util.List;

import org.json.JSONArray;

import com.activity.model.ActivityVO;
import com.expertise.model.ExpVO;

public class ActivityService {

	private ActivityDAO_interface dao;

	public ActivityService() {
		dao = new ActivityJNDIDAO();
	}

/* 基本 */
	// 新增
	public ActivityVO addActivity(String actname, String actloc, java.sql.Date actdate, String actss,
			java.sql.Date actstart, java.sql.Date actend, String acttype, Integer actprice, Integer actmin,
			Integer actmax,Integer actcur, String actdesc, String actsta, byte[] actpic, String stuno, String coano) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActname(actname);
		activityVO.setActloc(actloc);
		activityVO.setActdate(actdate);
		activityVO.setActss(actss);
		activityVO.setActstart(actstart);
		activityVO.setActend(actend);
		activityVO.setActtype(acttype);
		activityVO.setActprice(actprice);
		activityVO.setActmin(actmin);
		activityVO.setActmax(actmax);
		activityVO.setActcur(actcur);
		activityVO.setActdesc(actdesc);
		activityVO.setActsta(actsta);
		activityVO.setActpic(actpic);
		activityVO.setStuno(stuno);
		activityVO.setCoano(coano);
		dao.insert(activityVO);
		return activityVO;
	}

	// 修改
	public ActivityVO updateActivity(String actno, String actname, String actloc, java.sql.Date actdate, String actss,
			java.sql.Date actstart, java.sql.Date actend, String acttype, Integer actprice, Integer actmin,
			Integer actmax, String actdesc, String actsta, byte[] actpic, String stuno, String coano) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActno(actno);
		activityVO.setActname(actname);
		activityVO.setActloc(actloc);
		activityVO.setActdate(actdate);
		activityVO.setActss(actss);
		activityVO.setActstart(actstart);
		activityVO.setActend(actend);
		activityVO.setActtype(acttype);
		activityVO.setActprice(actprice);
		activityVO.setActmin(actmin);
		activityVO.setActmax(actmax);
		activityVO.setActdesc(actdesc);
		activityVO.setActsta(actsta);
		activityVO.setActpic(actpic);
		activityVO.setStuno(stuno);
		activityVO.setCoano(coano);
		dao.update(activityVO);
		return activityVO;
	}

	// 刪除
	public void deleteActivity(String actno) {
		dao.delete(actno);
	}

	// 單一查詢
	public ActivityVO getOneActivity(String actno) {
		return dao.findByPrimaryKey(actno);
	}

	// 全部查詢
	public List<ActivityVO> getAllActivity() {
		return dao.getAll();
	}

	/* 變化 */
	// 全部查詢 主揪由學員編號找到發起的全部活動
	public List<ActivityVO> getAllForHost(String stuno){
		return dao.getAllForHost(stuno);
	}
	// 全部查詢 查詢教練全部活動放入課表(轉JSON格式)
	public JSONArray getAllToCoachTable(String coano) {
		return dao.getAllToCoachTable(coano);
	}
	
	// 單一查詢 查詢教練專長
	public ExpVO findByExpertise(String expno) {
		return dao.findByExpertise(expno);
	}
	// 全部查詢 教練由教練編號找到預約待確認的活動
	public List<ActivityVO> getAllReservation(String coano){
		return dao.getAllReservation(coano);
	}
	// 活動狀態更新 教練確認預約更改活動狀態-->預約未成團
	public void update_sta_bycoach(String coano,String actno) {
		dao.update_sta_bycoach(coano,actno);
	}
	//活動狀態更新  主揪上架活動更改活動狀態-->上架待成團(開放報名)
	public void update_sta_byhost(String stuno,String actno){
		dao.update_sta_byhost(stuno,actno);
	};
	//活動狀態更新 人數不足系統自動更新活動狀態
	public void update_sta_auto(String actno) {
		dao.update_sta_auto(actno);
	};
	//訪客以類型找活動
	public List<ActivityVO> findByActType(String acttype){
		return dao.findByActType(acttype);
	}
	//報名人數達成團下限更新活動價格為促銷價
	public void update_actprice(int actprice,String actno) {
		dao.update_actprice(actprice, actno);
	};
	// 活動連接教練課表
	public JSONArray checkTime(String coano) {
		return dao.checkTime(coano);
	}
}
