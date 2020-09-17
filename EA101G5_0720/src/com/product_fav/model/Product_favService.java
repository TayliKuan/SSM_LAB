package com.product_fav.model;

import java.util.List;

public class Product_favService {
	
	private Product_favDAO_interface dao;
	
	public Product_favService() {
		dao = new Product_favDAO();
	}
	
	public Product_favVO addProduct_fav(String stuno , String prodno) {
		Product_favVO pfVO= new Product_favVO();
		
		pfVO.setStuno(stuno);
		pfVO.setProdno(prodno);
		dao.insert(pfVO);
		return pfVO;
		
	}
	
	public void deleteProduct_fav(String stuno,String prodno) {
		dao.delete(stuno, prodno);
	}
	
	public List<Product_favVO>getAll(){
		return dao.getAll();
	}
	
	public List<Product_favVO>getOnePf(String stuno){
		return dao.findByStuno(stuno);
	}
	

}
