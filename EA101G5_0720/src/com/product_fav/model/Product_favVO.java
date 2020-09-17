package com.product_fav.model;

import java.io.Serializable;

public class Product_favVO implements Serializable{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodno == null) ? 0 : prodno.hashCode());
		result = prime * result + ((stuno == null) ? 0 : stuno.hashCode());
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
		Product_favVO other = (Product_favVO) obj;
		if (prodno == null) {
			if (other.prodno != null)
				return false;
		} else if (!prodno.equals(other.prodno))
			return false;
		if (stuno == null) {
			if (other.stuno != null)
				return false;
		} else if (!stuno.equals(other.stuno))
			return false;
		return true;
	}
	private String stuno;
	private String prodno;
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getProdno() {
		return prodno;
	}
	public void setProdno(String prodno) {
		this.prodno = prodno;
	}

}
