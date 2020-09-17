package com.emp.dao;

import java.util.List;

import com.emp.model.EmpDoMain;

public interface EmpIN {
	List<EmpDoMain> getAll();
	void delete(Integer empno);
	void update(EmpDoMain emp);
	EmpDoMain getOne(Integer empno);
	void insert(EmpDoMain emp);
	void deletebatch(Integer[] empnos);
	List<EmpDoMain> findbycindition(EmpDoMain emp);
}
