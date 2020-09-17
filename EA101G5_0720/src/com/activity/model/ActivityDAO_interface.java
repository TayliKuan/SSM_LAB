package com.activity.model;

import java.util.*;

import org.json.JSONArray;

import com.expertise.model.ExpVO;


public interface ActivityDAO_interface {
	/*基本*/
    public void insert(ActivityVO actVO);
    public void update(ActivityVO actVO);
    public void delete(String actno);
    public ActivityVO findByPrimaryKey(String actno);
    public List<ActivityVO> getAll();
    /*變化*/
    /*由學員編號找到發起的全部活動*/
    public List<ActivityVO> getAllForHost(String stuno) ;
    /*由教練編號查詢教練活動相關課表*/
    public JSONArray getAllToCoachTable(String coano);
    /*由專長編號找到對應的教練*/
    public ExpVO findByExpertise(String expno);
    /*全部查詢 教練由教練編號找到預約待確認的活動*/
    public List<ActivityVO> getAllReservation(String coano);
    /*-------------------------------------------------------*/
    
    /*狀態由四種(1.待預約(預設) 2.預約未上架 3.上架待成團 4.上架已成團 5.下架中)*/
    
    /*活動狀態更新 教練確認預約更改活動狀態-->未成團*/
	public void update_sta_bycoach(String coano,String actno);
	/*活動狀態更新  主揪上架活動更改活動狀態-->上架待成團(開放報名)*/
	public void update_sta_byhost(String stuno,String actno);
	/*活動狀態更新 成團人數不足系統自動更新活動狀態-->下架*/
	public void update_sta_auto(String actno);
	/*確認教練的活動日期時段*/
	public JSONArray checkTime(String coano);
	/*從活動類型查詢活動*/
	public List<ActivityVO> findByActType(String acttype);
	/*報名人數達成團下限更新活動價格為促銷價*/
	public void update_actprice(int actprice,String actno);
	
	
	
    
}


