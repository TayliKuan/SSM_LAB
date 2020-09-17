package com.sale_list.model;

import java.util.List;

public class Sale_listService {
	
	private Sale_listDAO_interface dao;
	
	public Sale_listService() {
		dao = new Sale_listDAO();
	}
	
	public Sale_listVO addSl(String sapro_no,String prodno,Integer sapro_price) {
		Sale_listVO slVO = new Sale_listVO();
		slVO.setSapro_no(sapro_no);
		slVO.setProdno(prodno);
		slVO.setSapro_price(sapro_price);
		dao.insert(slVO);
		
		return slVO;
	}
	
	public Sale_listVO updateSl(String sapro_no,String prodno,Integer sapro_price) {
		Sale_listVO slVO = new Sale_listVO();
		slVO.setSapro_no(sapro_no);
		slVO.setProdno(prodno);
		slVO.setSapro_price(sapro_price);
		dao.update(slVO);
		
		return slVO;
	}
	
	public void deleteSl(String sapro_no,String prodno) {
		dao.delete(sapro_no, prodno);
	}
	
	public List<Sale_listVO>getOneSl(String sapro_no){
		return dao.findBySaprono(sapro_no);
	}
	
	public List<Sale_listVO>getAll(){
		return dao.getAll();
	}
	
}
