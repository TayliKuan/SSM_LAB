package com.redemption.model;

import java.sql.Timestamp;

public class RedemptionVO implements java.io.Serializable{
	
	private String redno ;
	private String coano ;
	private Timestamp reddate ;
	private Integer redprice ;
	private String redsta ;
	private String lessno ;
	private String actno ;
	
	public String getRedno() {
		return redno;
	}
	public void setRedno(String redno) {
		this.redno = redno;
	}
	public String getCoano() {
		return coano;
	}
	public void setCoano(String coano) {
		this.coano = coano;
	}
	public Timestamp getReddate() {
		return reddate;
	}
	public void setReddate(Timestamp reddate) {
		this.reddate = reddate;
	}
	public Integer getRedprice() {
		return redprice;
	}
	public void setRedprice(Integer redprice) {
		this.redprice = redprice;
	}
	public String getRedsta() {
		return redsta;
	}
	public void setRedsta(String redsta) {
		this.redsta = redsta;
	}
	public String getLessno() {
		return lessno;
	}
	public void setLessno(String lessno) {
		this.lessno = lessno;
	}
	public String getActno() {
		return actno;
	}
	public void setActno(String actno) {
		this.actno = actno;
	}
	
}
