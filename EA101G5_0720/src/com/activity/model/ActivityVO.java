package com.activity.model;

import java.sql.Date;


public class ActivityVO implements java.io.Serializable {
	private String actno;
	private String actname;
	private String actloc;
	private Date actdate;
	private String actss; //5
	private Date actstart;
	private Date actend;
	private String acttype;
	private Integer actprice;
	private Integer actmin;//10
	private Integer actmax;
	private Integer actcur;
	private String actdesc;
	private String actsta; 
	private byte[] actpic; //15
	private String stuno;
	private String coano; //17
	

	
	public String getActno() {
		return actno;
	}
	public void setActno(String actno) {
		this.actno = actno;
	}
	public String getActname() {
		return actname;
	}
	public void setActname(String actname) {
		this.actname = actname;
	}
	public String getActloc() {
		return actloc;
	}
	public void setActloc(String actloc) {
		this.actloc = actloc;
	}
	public Date getActdate() {
		return actdate;
	}
	public void setActdate(Date actdate) {
		this.actdate = actdate;
	}
	public String getActss() {
		return actss;
	}
	public void setActss(String actss) {
		this.actss = actss;
	}
	public Date getActstart() {
		return actstart;
	}
	public void setActstart(Date actstart) {
		this.actstart = actstart;
	}
	public Date getActend() {
		return actend;
	}
	public void setActend(Date actend) {
		this.actend = actend;
	}
	public String getActtype() {
		return acttype;
	}
	public void setActtype(String acttype) {
		this.acttype = acttype;
	}
	public Integer getActprice() {
		return actprice;
	}
	public void setActprice(Integer actprice) {
		this.actprice = actprice;
	}
	public Integer getActmin() {
		return actmin;
	}
	public void setActmin(Integer actmin) {
		this.actmin = actmin;
	}
	public Integer getActmax() {
		return actmax;
	}
	public void setActmax(Integer actmax) {
		this.actmax = actmax;
	}
	public Integer getActcur() {
		return actcur;
	}
	public void setActcur(Integer actcur) {
		this.actcur = actcur;
	}
	public String getActdesc() {
		return actdesc;
	}
	public void setActdesc(String actdesc) {
		this.actdesc = actdesc;
	}
	public String getActsta() {
		return actsta;
	}
	public void setActsta(String actsta) {
		this.actsta = actsta;
	}
	public byte[] getActpic() {
		return actpic;
	}
	public void setActpic(byte[] actpic) {
		this.actpic = actpic;
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
	

}
