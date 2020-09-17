package com.complaint.model;

import java.util.*;
//import com.student.model;
//import com.choah.model;


public interface ComplaintDAO_interface {
	
	public void insert(ComplaintVO complaintVO);
	public void update(ComplaintVO complaintVO);
	public void updatecomsta(ComplaintVO complaintVO);
	public void delete(String comno);
	public ComplaintVO findByPrimaryKey(String comno);
	public  List<ComplaintVO> findByStuno(String stuno);

	public List<ComplaintVO> getALL();
	
	//public set<CaochVO> getCaochByCoano(String coano); 
	//public set<StudentVO> getStudentByStudent(String stuno); 


}
