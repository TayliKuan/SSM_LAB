package com.lesson_order.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Lesson_orderVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String lord_no;
	private String stuno;
	private String lessno;
	private Integer lord_sc;
	private Timestamp lord_time;
	public String getLord_no() {
		return lord_no;
	}
	public void setLord_no(String lord_no) {
		this.lord_no = lord_no;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getLessno() {
		return lessno;
	}
	public void setLessno(String lessno) {
		this.lessno = lessno;
	}
	public Integer getLord_sc() {
		return lord_sc;
	}
	public void setLord_sc(Integer lord_sc) {
		this.lord_sc = lord_sc;
	}
	public Timestamp getLord_time() {
		return lord_time;
	}
	public void setLord_time(Timestamp lord_time) {
		this.lord_time = lord_time;
	}
	
	
}
