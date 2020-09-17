package com.complaint.model;

import java.util.List;


public class ComplaintService {
	
	private ComplaintDAO_interface dao;
	
	public ComplaintService() {
		dao = new ComplaintJDBCDAO();
	}
	
	public ComplaintVO addComplaint( String stuno, String coano,java.sql.Timestamp comdate, String comdesc, String comsta) {
		ComplaintVO complaintVO = new ComplaintVO();
	
		comsta = "0";//預設值
		complaintVO.setStuno(stuno);
		complaintVO.setCoano(coano);	
		complaintVO.setComdate(new java.sql.Timestamp(System.currentTimeMillis()));
		
		complaintVO.setComdesc(comdesc);
		complaintVO.setComsta(comsta);
	
		dao.insert(complaintVO);
		
		return complaintVO;
	
	}
	
	public ComplaintVO updateComplaint( String comno, String stuno, String coano,java.sql.Timestamp comdate, String comdesc,  String comsta) {
		ComplaintVO complaintVO = new ComplaintVO();
	
		complaintVO.setComno(comno);
		complaintVO.setComno(coano);
		complaintVO.setComdate(comdate);
		complaintVO.setComdesc(comdesc);
		complaintVO.setComno(comsta);

		dao.update(complaintVO);
		return complaintVO;
	
	}
	
	public ComplaintVO updatecomstaComplaint( String comno, String comsta) {
		comsta = "1";
		ComplaintVO complaintVO = new ComplaintVO();
		complaintVO.setComno(comno);
		complaintVO.setComsta(comsta);
		dao.updatecomsta(complaintVO);
		return complaintVO;

	}
	
	public void deleteComplaint( String comno) {
		dao.delete(comno);
	}
	
	public ComplaintVO getOneComplaint( String comno) {
		return dao.findByPrimaryKey(comno);
	}

	public List<ComplaintVO> getAll() {
		return dao.getALL();
	}
	
	public List<ComplaintVO> findByStuno(String stuno) {
		return dao.findByStuno(stuno);
	}
	


}
