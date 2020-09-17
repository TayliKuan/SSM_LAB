package com.sale_project.model;

import java.util.List;

import com.product.model.ProductVO;
import com.sale_list.model.Sale_listVO;
import com.sale_project.model.Sale_projectVO;

public interface Sale_projectDAO_interface {
	public void insert(Sale_projectVO sale_projectVO);
	public void update(Sale_projectVO sale_projectVO);
	public void delete(String sapro_no);
	public Sale_projectVO findByPrimaryKey(String sapro_no);
	public List<Sale_projectVO>getAll();
	/*新增促銷專案同時新增促銷明細(舊)*/
	public void insertWithSaleList(Sale_projectVO sale_projectVO,List<Sale_listVO>list);
	/*新增促銷專案同時新增促銷明細並更改商品價格*/
	public void insertWithProduct(Sale_projectVO sale_projectVO, List<Sale_listVO> list);
	public void updateWithProduct(Sale_projectVO sale_projectVO, List<Sale_listVO> list);
	public List<Sale_projectVO> getAllfilter();
	Sale_projectVO findByPrimaryKeyfilter(String sapro_no);
	public List<Sale_projectVO>getNowsapro();
	
}
