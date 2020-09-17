package com.complaint.model;

import java.io.*;
//import java.sql.Date;
import java.sql.Timestamp;

public class ComplaintVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String comno;
	private String stuno;
	private String coano;
	private String comdesc;
	private Timestamp comdate;
	private String comsta;
	
	public ComplaintVO() {
		
	}
		
	public String getComno() {
		return comno;
	}
	public void setComno(String comno) {
		this.comno = comno;
	}
	
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno; 
	}
	
	public String getCoano() {
		return coano;
	}
	public void setCoano(String coano) {
		this.coano = coano;
	}
	
	public String getComdesc() {
		return comdesc;
	}
	public void setComdesc(String comdesc) {
		this.comdesc = comdesc;
	}
	
	public Timestamp getComdate() {
		return comdate;
	}
	public void setComdate(Timestamp comdate) {
		this.comdate = comdate;
	}
	
	public String getComsta() {
		return comsta;
	}
	public void setComsta(String comsta) {
		this.comsta = comsta;
	}
}
