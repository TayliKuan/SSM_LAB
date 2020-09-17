package tw.gov.bli.dao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import tw.gov.bli.baseDao.BaseDao;
import tw.gov.bli.domain.Dept;
import tw.gov.bli.domain.User;

@Repository
public class DeptDaoImpl extends BaseDao implements DeptDao{

	@Override
	public List<Dept> findAll() {
		List<Dept> list = getSqlSession().selectList("tw.gov.bli.dao.DeptDao.findAll");
		return list;
	}

	@Override
	public void insert(Dept dept) {
		getSqlSession().insert("tw.gov.bli.dao.DeptDao.insert",dept);

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
	public List<User> getUsersByDeptno(String deptno) {
		List<Dept> list = getSqlSession().selectList("tw.gov.li.dao.DeptDao.getUsersByDeptno",deptno);
		List<User> listUsers=null;
		if(!list.isEmpty()) {
		Dept dept = list.get(0);
		   listUsers = new ArrayList<User>(dept.getUsers());
		}
		return listUsers;
	}

	@Override
	public Dept findByPrimaryKey(String deptno) {
		// TODO Auto-generated method stub
		return null;
	}

}
