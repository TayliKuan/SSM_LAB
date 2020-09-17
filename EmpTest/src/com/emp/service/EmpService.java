package com.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.dao.EmpIN;
import com.emp.model.EmpDoMain;

@Service
public class EmpService {
	
	@Autowired
	private EmpIN dao;
	
	public List<EmpDoMain> getAll(){
		return dao.getAll();
	}
	
	public void delete(Integer empno) {
		dao.delete(empno);
	}
	
	public void update(EmpDoMain emp) {
		dao.update(emp);
	}
	
	public EmpDoMain getOne(Integer empno) {
		return dao.getOne(empno);
	}
	public void insert(EmpDoMain emp) {
		dao.insert(emp);
	}
	
	public void deletebatch(Integer[] empnos) {
		dao.deletebatch(empnos);
	}
	public List<EmpDoMain> findbycondition(EmpDoMain emp){
		return dao.findbycindition(emp);
	}
}
