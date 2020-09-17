package com.emp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.emp.model.EmpDoMain;

@Repository
public class EmpDAO extends BaseDAO implements EmpIN{

	//查詢全部
	@Override
	public List<EmpDoMain> getAll() {
		return getSqlSession().selectList("com.emp.dao.EmpIN.getAll");
	}
	//刪除
	@Override
	public void delete(Integer empno) {
		getSqlSession().delete("com.emp.dao.EmpIN.delete",empno);
	}
	//修改
	public void update(EmpDoMain emp) {
		getSqlSession().update("com.emp.dao.EmpIN.update",emp);
	}
	//查單一， 回傳使用三元運算判斷
	@Override
	public EmpDoMain getOne(Integer empno) {
		List<EmpDoMain> emps = getSqlSession().selectList("com.emp.dao.EmpIN.getOne",empno);
		return emps.size()>0? emps.get(0):null;
	}
	@Override
	public void insert(EmpDoMain emp) {
		getSqlSession().insert("com.emp.dao.EmpIN.insert",emp);
	}
	
	@Override
	public void deletebatch(Integer[] empnos) {
		getSqlSession().delete("com.emp.dao.EmpIN.deletebatch", empnos);
	}
	@Override
	public List<EmpDoMain> findbycindition(EmpDoMain emp) {
		List<EmpDoMain> emps = getSqlSession().selectList("com.emp.dao.EmpIN.findbycondition",emp);
		return emps.size()>0? emps:null;
	}

}
