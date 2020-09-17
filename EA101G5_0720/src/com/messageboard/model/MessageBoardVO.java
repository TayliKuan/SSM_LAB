package com.messageboard.model;

public class MessageBoardVO implements java.io.Serializable{
	
	private String  askNone;
	private String  artNo;
	private String  askDesc;
	private String  mesRep;
	private String  repDesc;
	private String  mesDate;
	
	public String getAskNone() {
		return askNone;
	}
	public void setAskNone(String askNone) {
		this.askNone = askNone;
	}
	public String getArtNo() {
		return artNo;
	}
	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}
	public String getAskDesc() {
		return askDesc;
	}
	public void setAskDesc(String askDesc) {
		this.askDesc = askDesc;
	}
	public String getMesRep() {
		return mesRep;
	}
	public void setMesRep(String mesRep) {
		this.mesRep = mesRep;
	}
	public String getRepDesc() {
		return repDesc;
	}
	public void setRepDesc(String repDesc) {
		this.repDesc = repDesc;
	}
	public String getMesDate() {
		return mesDate;
	}
	public void setMesDate(String mesDate) {
		this.mesDate = mesDate;
	}
	
	
}
