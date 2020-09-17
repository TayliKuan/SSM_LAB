package com.product_order.model;

import java.sql.Date;
import java.util.List;

import com.product_order_list.model.Product_order_listVO;

public class Product_orderService {

	private Product_orderDAO_interface dao;
	
	public Product_orderService() {
		dao=new Product_orderDAO();
	}
	
	public Product_orderVO addPo(String stuno,Integer pordtotal,String recipient,String phonenumber,String pordadd,String pordsta,Integer fare) {
		Product_orderVO poVO = new Product_orderVO();
		
	
		poVO.setStuno(stuno);
		poVO.setPordtotal(pordtotal);
		poVO.setRecipient(recipient);
		poVO.setPhonenumber(phonenumber);
		poVO.setPordadd(pordadd);
		poVO.setPordsta(pordsta);
		poVO.setFare(fare);
		dao.insert(poVO);
		
		return poVO;
	}
	
	public Product_orderVO updatePo(String pordno,String stuno,Integer pordtotal,String recipient,String phonenumber,String pordadd,String pordsta,Integer fare) {
		Product_orderVO poVO = new Product_orderVO();
		
		poVO.setPordno(pordno);
		poVO.setStuno(stuno);
		poVO.setPordtotal(pordtotal);
		poVO.setRecipient(recipient);
		poVO.setPhonenumber(phonenumber);
		poVO.setPordadd(pordadd);
		poVO.setPordsta(pordsta);
		poVO.setFare(fare);
		dao.update(poVO);
		return poVO;
	}
	
	public void deletePo(String pordno) {
		dao.delete(pordno);
	}
	
	public Product_orderVO getOnePo(String pordno) {
		return dao.findByPrimaryKey(pordno);		
	}
	
	public List<Product_orderVO>getAll(){
		return dao.getAll();
	}
	public List<Product_orderVO>getAllByStuno(String stuno){
		return dao.getAllByStuno(stuno);
	}
	
	public void insertWithPordList(Product_orderVO product_orderVO, List<Product_order_listVO> list,int point) {
		 dao.insertWithPordList(product_orderVO, list, point);
	}
	


}
