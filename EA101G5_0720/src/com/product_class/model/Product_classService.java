package com.product_class.model;

import java.util.List;

public class Product_classService {
	private Product_classDAO_interface dao;
	
	public Product_classService() {
		dao = new Product_classDAO();
	}
	
	public Product_classVO addProduct_class(String pclass_name) {
		
		Product_classVO pcVO = new Product_classVO();
		
		pcVO.setPclass_name(pclass_name);
		dao.insert(pcVO);
		
		return pcVO;
	}
	
	//預留給 Struts 2用的
	public void addProduct_class(Product_classVO pcVO) {
		dao.insert(pcVO);
	}
	
	public Product_classVO upsateProduct_class(String pclass_id,String pclass_name) {
		Product_classVO pcVO = new Product_classVO();
		
		pcVO.setPclass_id(pclass_id);
		pcVO.setPclass_name(pclass_name);
		dao.update(pcVO);
		
		return dao.findByPrimaryKey(pclass_id);
	}
	
	//預留給 Struts2用的
	public void updateProduct_class(Product_classVO pcVO) {
		dao.update(pcVO);
	}
	
	public void deleteProduct_class(String pclass_id) {
		dao.delete(pclass_id);
	}
	
	public Product_classVO getOnePc(String pclass_id) {
		return dao.findByPrimaryKey(pclass_id);
	}
	
	public List<Product_classVO>getAll(){
		return dao.getAll();
	}
}
