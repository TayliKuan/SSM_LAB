package com.sale_project.model;

import java.io.Serializable;
import java.sql.Date;

public class Sale_projectVO implements Serializable{
	private String sapro_no;
	private String sapro_name;
	private Date sapro_start;
	private Date sapro_end;
	public String getSapro_no() {
		return sapro_no;
	}
	public void setSapro_no(String sapro_no) {
		this.sapro_no = sapro_no;
	}
	public String getSapro_name() {
		return sapro_name;
	}
	public void setSapro_name(String sapro_name) {
		this.sapro_name = sapro_name;
	}
	public Date getSapro_start() {
		return sapro_start;
	}
	public void setSapro_start(Date sapro_start) {
		this.sapro_start = sapro_start;
	}
	public Date getSapro_end() {
		return sapro_end;
	}
	public void setSapro_end(Date sapro_end) {
		this.sapro_end = sapro_end;
	}
	

}
