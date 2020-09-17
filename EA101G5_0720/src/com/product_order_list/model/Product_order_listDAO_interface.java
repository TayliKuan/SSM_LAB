package com.product_order_list.model;

import java.util.List;


public interface Product_order_listDAO_interface {
	public void insert(Product_order_listVO product_order_listVO);
	public void update(Product_order_listVO product_order_listVO);
	public void delete(String prodno,String pordno);
	public List<Product_order_listVO>getAll();
	public List<Product_order_listVO> findByPordno(String pordno);
	public void insert2(Product_order_listVO product_order_listVO ,java.sql.Connection con);
}
