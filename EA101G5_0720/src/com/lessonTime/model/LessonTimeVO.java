package com.lessonTime.model;

import java.sql.Date;

public class LessonTimeVO  implements java.io.Serializable{
	private String ltime_no;
	private Date ltime_date;
	private String ltime_ss;
	
	public String getLtime_no() {
		return ltime_no;
	}
	public void setLtime_no(String ltime_no) {
		this.ltime_no = ltime_no;
	}
	public Date getLtime_date() {
		return ltime_date;
	}
	public void setLtime_date(Date ltime_date) {
		this.ltime_date = ltime_date;
	}
	public String getLtime_ss() {
		return ltime_ss;
	}
	public void setLtime_ss(String ltime_ss) {
		this.ltime_ss = ltime_ss;
	}
	

}
