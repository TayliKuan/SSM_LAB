package com.product_order_list.model;

import java.io.Serializable;

public class Product_order_listVO implements Serializable{
	private String prodno;
	private String pordno;
	private int pord_listqty;
	private int pord_listprice;
	public String getProdno() {
		return prodno;
	}
	public void setProdno(String prodno) {
		this.prodno = prodno;
	}
	public String getPordno() {
		return pordno;
	}
	public void setPordno(String pordno) {
		this.pordno = pordno;
	}
	public int getPord_listqty() {
		return pord_listqty;
	}
	public void setPord_listqty(int pord_listqty) {
		this.pord_listqty = pord_listqty;
	}
	public int getPord_listprice() {
		return pord_listprice;
	}
	public void setPord_listprice(int pord_listprice) {
		this.pord_listprice = pord_listprice;
	}
	

}
