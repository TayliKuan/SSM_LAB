package com.article.model;

import java.sql.Timestamp;

import oracle.sql.BLOB;

public class ArticleVO implements java.io.Serializable{
	
	private String  artNo;
	private String  artTitle;
	private String  artCon;
	private String  artDate;
	private String  stuNo;
	private String  coaNo;
	private byte[]  artPic;
	private String  artSta;
	
	public String getArtNo() {
		return artNo;
	}
	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtCon() {
		return artCon;
	}
	public void setArtCon(String artCon) {
		this.artCon = artCon;
	}
	public String getArtDate() {
		return artDate;
	}
	public void setArtDate(String artDate) {
		this.artDate = artDate;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getCoaNo() {
		return coaNo;
	}
	public void setCoaNo(String coaNo) {
		this.coaNo = coaNo;
	}
	public byte[] getArtPic() {
		return artPic;
	}
	public void setArtPic(byte[] artPic) {
		this.artPic = artPic;
	}
	public String getArtSta() {
		return artSta;
	}
	public void setArtSta(String artSta) {
		this.artSta = artSta;
	}
	

}
