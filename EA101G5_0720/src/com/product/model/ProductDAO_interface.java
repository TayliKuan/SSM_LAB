package com.product.model;

import java.sql.Connection;
import java.util.List;

import com.sale_list.model.Sale_listVO;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void delete(String prodno);
	public ProductVO findByPrimaryKey(String prodno);
	public List<ProductVO>getAll();
	public List<ProductVO>getOnShelves();
	public List<ProductVO>findyByPclass(String pclass_id);
	public void updateByPrice(ProductVO productVO, Connection con);
}
