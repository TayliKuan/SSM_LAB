package com.lessonDetail.model;

import java.sql.Date;

public class LessonDetailVO  implements java.io.Serializable{

	private String lessno;
	private String ltime_no;
	private Integer ltime_ss; 
	private Date ltime_date;
	
	public Date getLtime_date() {
		return ltime_date;
	}
	public void setLtime_date(Date ltime_date) {
		this.ltime_date = ltime_date;
	}
	public Integer getLtime_ss() {
		return ltime_ss;
	}
	public void setLtime_ss(Integer ltime_ss) {
		this.ltime_ss = ltime_ss;
	}
	public String getLessno() {
		return lessno;
	}
	public void setLessno(String lessno) {
		this.lessno = lessno;
	}
	public String getLtime_no() {
		return ltime_no;
	}
	public void setLtime_no(String ltime_no) {
		this.ltime_no = ltime_no;
	}
	
	
}
