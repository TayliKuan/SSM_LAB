package com.product_order.model;

import java.util.List;

import com.product_order.model.Product_orderVO;
import com.product_order_list.model.Product_order_listVO;

public interface Product_orderDAO_interface {
	public void insert (Product_orderVO product_orderVO);
	public void update(Product_orderVO product_orderVO);
	public void delete(String pordno);
	public Product_orderVO findByPrimaryKey(String pordno);
	public List<Product_orderVO>getAll();
	public void insertWithPordList(Product_orderVO product_orderVO,List<Product_order_listVO> list,int point);
	public List<Product_orderVO>getAllByStuno(String stuno);
 }
