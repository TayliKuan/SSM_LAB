package com.activity_order.model;

import java.sql.Timestamp;

public class Activity_orderVO {
	private String aord_no;
	private String actno;
	private String stuno;
	private Integer aord_sc;
	private Timestamp aord_time;
	public String getAord_no() {
		return aord_no;
	}
	public void setAord_no(String aord_no) {
		this.aord_no = aord_no;
	}
	public String getActno() {
		return actno;
	}
	public void setActno(String actno) {
		this.actno = actno;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public Integer getAord_sc() {
		return aord_sc;
	}
	public void setAord_sc(Integer aord_sc) {
		this.aord_sc = aord_sc;
	}
	public Timestamp getAord_time() {
		return aord_time;
	}
	public void setAord_time(Timestamp aord_time) {
		this.aord_time = aord_time;
	}

}
