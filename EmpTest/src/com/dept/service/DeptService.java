package com.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dept.dao.DeptIN;
import com.dept.model.DeptDoMain;

@Service
public class DeptService {

	@Autowired
	private DeptIN dao;
	

	public DeptDoMain getOne(Integer deptno) {
		return dao.getOne(deptno);
	}
	
	public List<DeptDoMain> getAll(){
		return dao.getAll();
	}
	
}
