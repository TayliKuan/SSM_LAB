package com.expertise_own.model;

public class ExpOwnVO implements java.io.Serializable {

	private String coano;
	private String expno;
	private byte[] expown;

	// for front-end display not in the data schema
	private String expdesc;

	// for front-end display not in the data schema
	private String expownStr;

	public String getCoano() {
		return coano;
	}

	public void setCoano(String coano) {
		this.coano = coano;
	}

	public String getExpno() {
		return expno;
	}

	public void setExpno(String expno) {
		this.expno = expno;
	}

	public byte[] getExpown() {
		return expown;
	}

	public void setExpown(byte[] expown) {
		this.expown = expown;
	}

	public String getExpdesc() {
		return expdesc;
	}

	public void setExpdesc(String expdesc) {
		this.expdesc = expdesc;
	}

	public String getExpownStr() {
		return expownStr;
	}

	public void setExpownStr(String expownStr) {
		this.expownStr = expownStr;
	}

}
