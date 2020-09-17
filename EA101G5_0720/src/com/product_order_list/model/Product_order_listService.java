package com.product_order_list.model;

import java.util.List;

public class Product_order_listService {
	private Product_order_listDAO_interface dao;
	
	public Product_order_listService() {
		dao = new Product_order_listDAO();
	}
	
	public Product_order_listVO addPol(String prodno,String pordno,Integer pord_listqty,Integer pord_listprice) {
		Product_order_listVO polVO = new Product_order_listVO();
		
		polVO.setProdno(prodno);
		polVO.setPordno(pordno);
		polVO.setPord_listqty(pord_listqty);
		polVO.setPord_listprice(pord_listprice);
		dao.insert(polVO);
		
		return polVO;
	}
	
	public Product_order_listVO updatePol(String prodno,String pordno,Integer pord_listqty,Integer pord_listprice) {
		Product_order_listVO polVO = new Product_order_listVO();
		
		polVO.setProdno(prodno);
		polVO.setPordno(pordno);
		polVO.setPord_listqty(pord_listqty);
		polVO.setPord_listprice(pord_listprice);
		dao.update(polVO);
		
		return polVO;
	}

	public void deletePol(String prodno, String pordno) {
		dao.delete(prodno, pordno);
	}
	
	public List<Product_order_listVO> getOnePol(String pordno) {
		return dao.findByPordno(pordno);
	}
	
	public List<Product_order_listVO>getAll(){
		return dao.getAll();
	}
	
}
