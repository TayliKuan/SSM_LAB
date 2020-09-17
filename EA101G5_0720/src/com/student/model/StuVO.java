package com.student.model;

import java.sql.Date;

public class StuVO implements java.io.Serializable {
	private String stuno;
	private String stuname;
	private String stupsw;
	private String stumail;
	private String stutel;
	private String stuadd;
	private Integer stupoint;
	private String stusta;
	private String stusex;
	private Date stubir;
	private byte[] stupic;

	// for front-end display not in the data schema
	private String stupicStr;

	public String getStuno() {
		return stuno;
	}

	public void setStuno(String stuno) {
		this.stuno = stuno;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getStupsw() {
		return stupsw;
	}

	public void setStupsw(String stupsw) {
		this.stupsw = stupsw;
	}

	public String getStumail() {
		return stumail;
	}

	public void setStumail(String stumail) {
		this.stumail = stumail;
	}

	public String getStutel() {
		return stutel;
	}

	public void setStutel(String stutel) {
		this.stutel = stutel;
	}

	public String getStuadd() {
		return stuadd;
	}

	public void setStuadd(String stuadd) {
		this.stuadd = stuadd;
	}

	public Integer getStupoint() {
		return stupoint;
	}

	public void setStupoint(Integer stupoint) {
		this.stupoint = stupoint;
	}

	public String getStusta() {
		return stusta;
	}

	public void setStusta(String stusta) {
		this.stusta = stusta;
	}

	public String getStusex() {
		return stusex;
	}

	public void setStusex(String stusex) {
		this.stusex = stusex;
	}

	public Date getStubir() {
		return stubir;
	}

	public void setStubir(Date stubir) {
		this.stubir = stubir;
	}

	public byte[] getStupic() {
		return stupic;
	}

	public void setStupic(byte[] stupic) {
		this.stupic = stupic;
	}

	public String getStupicStr() {
		return stupicStr;
	}

	public void setStupicStr(String stupicStr) {
		this.stupicStr = stupicStr;
	}

}
