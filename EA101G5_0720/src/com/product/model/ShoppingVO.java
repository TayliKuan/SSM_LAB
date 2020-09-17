package com.product.model;

public class ShoppingVO {
	private String stuno;
	private String prodname;
	private String prodno;
	private Integer prodprice;
	private Integer prodqty;
	private Integer prodtotal;
	private byte[] prodpic;
	

	
	public ShoppingVO(){
		stuno="";
		prodname="";
		prodno="";
		prodprice=0;
		prodqty=0;
		prodtotal=0;
	}
	
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getProdno() {
		return prodno;
	}
	public void setProdno(String prodno) {
		this.prodno = prodno;
	}
	public Integer getProdprice() {
		return prodprice;
	}
	public void setProdprice(Integer prodprice) {
		this.prodprice = prodprice;
	}
	public Integer getProdqty() {
		return prodqty;
	}
	public void setProdqty(Integer prodqty) {
		this.prodqty = prodqty;
	}
	public Integer getProdtotal() {
		return prodtotal;
	}
	public void setProdtotal(Integer prodtotal) {
		this.prodtotal = prodtotal;
	}
	public byte[] getProdpic() {
		return prodpic;
	}

	public void setProdpic(byte[] prodpic) {
		this.prodpic = prodpic;
	}
	
}
