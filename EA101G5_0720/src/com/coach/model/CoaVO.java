package com.coach.model;

public class CoaVO implements java.io.Serializable {
	private String coano;
	private String coaname;
	private String coapsw;
	private String coamail;
	private String coatel;
	private String coaacc;
	private Integer coapoint;
	private String coasta;
	private byte[] coapic;
	private String coasex;
	private String coaintro;
	private Integer coasctotal;
	private Integer coascqty;

	// for front-end display not in the data schema
	private String coapicStr;

	public String getCoano() {
		return coano;
	}

	public void setCoano(String coano) {
		this.coano = coano;
	}

	public String getCoaname() {
		return coaname;
	}

	public void setCoaname(String coaname) {
		this.coaname = coaname;
	}

	public String getCoapsw() {
		return coapsw;
	}

	public void setCoapsw(String coapsw) {
		this.coapsw = coapsw;
	}

	public String getCoamail() {
		return coamail;
	}

	public void setCoamail(String coamail) {
		this.coamail = coamail;
	}

	public String getCoatel() {
		return coatel;
	}

	public void setCoatel(String coatel) {
		this.coatel = coatel;
	}

	public String getCoaacc() {
		return coaacc;
	}

	public void setCoaacc(String coaacc) {
		this.coaacc = coaacc;
	}

	public Integer getCoapoint() {
		return coapoint;
	}

	public void setCoapoint(Integer coapoint) {
		this.coapoint = coapoint;
	}

	public String getCoasta() {
		return coasta;
	}

	public void setCoasta(String coasta) {
		this.coasta = coasta;
	}

	public byte[] getCoapic() {
		return coapic;
	}

	public void setCoapic(byte[] coapic) {
		this.coapic = coapic;
	}

	public String getCoasex() {
		return coasex;
	}

	public void setCoasex(String coasex) {
		this.coasex = coasex;
	}

	public String getCoaintro() {
		return coaintro;
	}

	public void setCoaintro(String coaintro) {
		this.coaintro = coaintro;
	}

	public Integer getCoasctotal() {
		return coasctotal;
	}

	public void setCoasctotal(Integer coasctotal) {
		this.coasctotal = coasctotal;
	}

	public Integer getCoascqty() {
		return coascqty;
	}

	public void setCoascqty(Integer coascqty) {
		this.coascqty = coascqty;
	}

	public String getCoapicStr() {
		return coapicStr;
	}

	public void setCoapicStr(String coapicStr) {
		this.coapicStr = coapicStr;
	}

}
