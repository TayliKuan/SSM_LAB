package com.sale_project.model;

import java.sql.Date;
import java.util.List;

import com.sale_list.model.Sale_listVO;

public class Sale_projectService {
	private Sale_projectDAO_interface dao;
	
	public Sale_projectService() {
		dao = new Sale_projectDAO();
	}
	
	public Sale_projectVO addSp(String sapro_no,Date sapro_start,Date sapro_end) {
		Sale_projectVO spVO= new Sale_projectVO();
		spVO.setSapro_no(sapro_no);
		spVO.setSapro_start(sapro_start);
		spVO.setSapro_end(sapro_end);
		dao.insert(spVO);
		return spVO;
	}
	
	public Sale_projectVO updateSp(String sapro_no,Date sapro_start,Date sapro_end) {
		Sale_projectVO spVO= new Sale_projectVO();
		spVO.setSapro_no(sapro_no);
		spVO.setSapro_start(sapro_start);
		spVO.setSapro_end(sapro_end);
		dao.update(spVO);
		return spVO;
	}
	
	public void deleteSp(String sapro_no) {
		dao.delete(sapro_no);
	}
	
	public Sale_projectVO getOneSp(String sapro_no) {
		return dao.findByPrimaryKey(sapro_no);
	}
	
	public List<Sale_projectVO>getAll(){
		return dao.getAll();
	}
	
	public void insertWithSaleList(Sale_projectVO sale_projectVO, List<Sale_listVO> list) {
		dao.insertWithSaleList(sale_projectVO, list);
	}
	
	public void updatetWithSaleList(Sale_projectVO sale_projectVO, List<Sale_listVO> list) {
		dao.updateWithProduct(sale_projectVO, list);
	}
	
	public List<Sale_projectVO> getAllfilter(){
		return dao.getAllfilter();
	}
	
	public Sale_projectVO findByPrimaryKeyfilter(String sapro_no) {
		return dao.findByPrimaryKey(sapro_no);
	}
}
