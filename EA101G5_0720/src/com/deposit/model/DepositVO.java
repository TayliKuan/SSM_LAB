package com.deposit.model;

import java.sql.Timestamp;

public class DepositVO implements java.io.Serializable{
	
	private String depno ;
	private String stuno ;
	private Timestamp depdate ;
	private Integer depprice ;
	
	public String getDepno() {
		return depno;
	}
	public void setDepno(String depno) {
		this.depno = depno;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public Timestamp getDepdate() {
		return depdate;
	}
	public void setDepdate(Timestamp depdate) {
		this.depdate = depdate;
	}
	public Integer getDepprice() {
		return depprice;
	}
	public void setDepprice(Integer depprice) {
		this.depprice = depprice;
	}
		
}
