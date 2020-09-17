package com.product_class.model;

import java.io.Serializable;

public class Product_classVO implements Serializable{
	
	private String pclass_id;
	private String pclass_name;
	
	public String getPclass_id() {
		return pclass_id;
	}
	public void setPclass_id(String pclass_id) {
		this.pclass_id = pclass_id;
	}
	public String getPclass_name() {
		return pclass_name;
	}
	public void setPclass_name(String pclass_name) {
		this.pclass_name = pclass_name;
	}

}
