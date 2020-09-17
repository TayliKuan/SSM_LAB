package com.dept.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dept.model.DeptDoMain;
import com.emp.dao.BaseDAO;

@Repository
public class DeptDAO extends BaseDAO implements DeptIN{

	@Override
	public DeptDoMain getOne(Integer deptno) {
		List<DeptDoMain> list = getSqlSession().selectList("com.dept.dao.DeptIN.getOne");
		return list.size()>0? list.get(0):null;
	}

	@Override
	public List<DeptDoMain> getAll() {
		return getSqlSession().selectList("com.dept.dao.DeptIN.getAll");
	}

	@Override
	public void insert(DeptDoMain dept) {
		getSqlSession().insert("com.dept.dao.DeptIN.insert", dept);
	}

}
