package com.lesson.model;

import java.sql.Date;

public class LessonVO implements java.io.Serializable{
	private String lessno;
	private String coano;
	private String lessname;
	private Integer lessmax;
	private Integer lessmin;
	
	private Integer lesscur;
	private String lesstype;
	private String lessloc;
	private Integer lessprice;
	private String lessdesc;
	
	private Date lessstart;
	private Date lessend;
	private String lesssta;
	private Integer lesstimes;
	private byte[] lesspic;
	
	public String getLessno() {
		return lessno;
	}
	public void setLessno(String lessno) {
		this.lessno = lessno;
	}
	public String getCoano() {
		return coano;
	}
	public void setCoano(String coano) {
		this.coano = coano;
	}
	public String getLessname() {
		return lessname;
	}
	public void setLessname(String lessname) {
		this.lessname = lessname;
	}
	public Integer getLessmax() {
		return lessmax;
	}
	public void setLessmax(Integer lessmax) {
		this.lessmax = lessmax;
	}
	public Integer getLessmin() {
		return lessmin;
	}
	public void setLessmin(Integer lessmin) {
		this.lessmin = lessmin;
	}
	public Integer getLesscur() {
		return lesscur;
	}
	public void setLesscur(Integer lesscur) {
		this.lesscur = lesscur;
	}
	public String getLesstype() {
		return lesstype;
	}
	public void setLesstype(String lesstype) {
		this.lesstype = lesstype;
	}
	public String getLessloc() {
		return lessloc;
	}
	public void setLessloc(String lessloc) {
		this.lessloc = lessloc;
	}
	public Integer getLessprice() {
		return lessprice;
	}
	public void setLessprice(Integer lessprice) {
		this.lessprice = lessprice;
	}
	public String getLessdesc() {
		return lessdesc;
	}
	public void setLessdesc(String lessdesc) {
		this.lessdesc = lessdesc;
	}
	public Date getLessstart() {
		return lessstart;
	}
	public void setLessstart(Date lessstart) {
		this.lessstart = lessstart;
	}
	public Date getLessend() {
		return lessend;
	}
	public void setLessend(Date lessend) {
		this.lessend = lessend;
	}
	public String getLesssta() {
		return lesssta;
	}
	public void setLesssta(String lesssta) {
		this.lesssta = lesssta;
	}
	public Integer getLesstimes() {
		return lesstimes;
	}
	public void setLesstimes(Integer lesstimes) {
		this.lesstimes = lesstimes;
	}
	public byte[] getLesspic() {
		return lesspic;
	}
	public void setLesspic(byte[] lesspic) {
		this.lesspic = lesspic;
	}
	
	
}
