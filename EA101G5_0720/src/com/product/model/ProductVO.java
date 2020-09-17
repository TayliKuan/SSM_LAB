package com.product.model;

import java.io.Serializable;

public class ProductVO implements Serializable{

	private String prodno;
	private String pclass_id;
	private String prodname;
	private int prodprice;
	private int prodqty;
	private byte[] prodpic;
	private String proddesc;
	private String prodsta;
	private int qty;
	
	
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getProdno() {
		return prodno;
	}
	public void setProdno(String prodno) {
		this.prodno = prodno;
	}
	public String getPclass_id() {
		return pclass_id;
	}
	public void setPclass_id(String pclass_id) {
		this.pclass_id = pclass_id;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public int getProdprice() {
		return prodprice;
	}
	public void setProdprice(int prodprice) {
		this.prodprice = prodprice;
	}
	public int getProdqty() {
		return prodqty;
	}
	public void setProdqty(int prodqty) {
		this.prodqty = prodqty;
	}
	public byte[] getProdpic() {
		return prodpic;
	}
	public void setProdpic(byte[] prodpic) {
		this.prodpic = prodpic;
	}
	public String getProddesc() {
		return proddesc;
	}
	public void setProddesc(String proddesc) {
		this.proddesc = proddesc;
	}
	public String getProdsta() {
		return prodsta;
	}
	public void setProdsta(String prodsta) {
		this.prodsta = prodsta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodname == null) ? 0 : prodname.hashCode());
		result = prime * result + ((prodno == null) ? 0 : prodno.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductVO other = (ProductVO) obj;
		if (prodname == null) {
			if (other.prodname != null)
				return false;
		} else if (!prodname.equals(other.prodname))
			return false;
		if (prodno == null) {
			if (other.prodno != null)
				return false;
		} else if (!prodno.equals(other.prodno))
			return false;
		return true;
	}
	
	


	
}
