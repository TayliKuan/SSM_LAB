package com.sale_list.model;

import java.io.Serializable;

public class Sale_listVO implements Serializable{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodno == null) ? 0 : prodno.hashCode());
		result = prime * result + ((sapro_no == null) ? 0 : sapro_no.hashCode());
		result = prime * result + sapro_price;
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
		Sale_listVO other = (Sale_listVO) obj;
		if (prodno == null) {
			if (other.prodno != null)
				return false;
		} else if (!prodno.equals(other.prodno))
			return false;
		if (sapro_no == null) {
			if (other.sapro_no != null)
				return false;
		} else if (!sapro_no.equals(other.sapro_no))
			return false;
		if (sapro_price != other.sapro_price)
			return false;
		return true;
	}
	private String sapro_no;
	private String prodno;
	private int sapro_price;
	
	public String getSapro_no() {
		return sapro_no;
	}
	public void setSapro_no(String sapro_no) {
		this.sapro_no = sapro_no;
	}
	public String getProdno() {
		return prodno;
	}
	public void setProdno(String prodno) {
		this.prodno = prodno;
	}
	public int getSapro_price() {
		return sapro_price;
	}
	public void setSapro_price(int sapro_price) {
		this.sapro_price = sapro_price;
	}

}
