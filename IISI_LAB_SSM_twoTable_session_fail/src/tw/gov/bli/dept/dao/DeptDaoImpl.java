package tw.gov.bli.dept.dao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import tw.gov.bli.baseDao.BaseDao;
import tw.gov.bli.dept.domain.Dept;
import tw.gov.bli.user.domain.User;

@Repository
public class DeptDaoImpl extends BaseDao implements DeptDao{

	@Override
	public List<Dept> findAll() {
		List<Dept> list = getSqlSession().selectList("tw.gov.bli.dept.dao.DeptDao.findAll");
		return list;
	}

	@Override
	public void insert(Dept dept) {
		getSqlSession().insert("tw.gov.bli.dept.dao.DeptDao.insert",dept);

	}

	@Override
	public void update(Dept dept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String deptno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dept findByPrimaryKey(String deptno) {
		return null;

	}

	@Override
	public List<User> getUsersByDeptno(String deptno) {
		List<User> users = getSqlSession().selectList("tw.gov.bli.dept.dao.DeptDao.getUsersByDeptno",deptno);
		return users;
	}

}
