package com.product_fav.model;

import java.util.List;


public interface Product_favDAO_interface {
	
	public void insert(Product_favVO product_favVO);
	public void delete(String stuno,String prodno);
	public List<Product_favVO>getAll();
	public List<Product_favVO>findByStuno(String stuno);
}
