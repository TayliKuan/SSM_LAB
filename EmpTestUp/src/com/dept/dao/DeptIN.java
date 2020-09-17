package com.dept.dao;

import java.util.List;

import com.dept.model.DeptDoMain;

public interface DeptIN {
	DeptDoMain getOne(Integer deptno);
	List<DeptDoMain> getAll();
	void insert(DeptDoMain dept);
}
