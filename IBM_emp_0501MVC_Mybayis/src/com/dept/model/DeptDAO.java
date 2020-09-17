package com.dept.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;

import com.emp.model.EmpVO;

import mybatis.util.MyBaitsUtil;

public class DeptDAO implements DeptDAO_interface {

	@Override
	public void insert(DeptVO deptVO) {
		SqlSession session = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			session.insert("com.dept.model.DeptDAO_interface.insert",deptVO);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			System.err.println(e);
			throw e;
		} finally {
			if(session!=null)
			    session.close();
		}

	}

	@Override
	public void update(DeptVO deptVO) {
		SqlSession session = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			session.update("com.dept.model.DeptDAO_interface.update",deptVO);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			System.err.println(e);
			throw e;
		} finally {
			if(session!=null)
			    session.close();
		}

	}

	@Override
	public void delete(Integer deptno) {
		SqlSession session = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			session.delete("com.dept.model.DeptDAO_interface.delete",deptno);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			System.err.println(e);
			throw e;
		} finally {
			if(session!=null)
			    session.close();
		}

	}

	@Override
	public DeptVO findByPrimaryKey(Integer deptno) {
		SqlSession session = null;
		DeptVO deptVO = null;
		List<DeptVO> list = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			list = session.selectList("com.dept.model.DeptDAO_interface.findByPrimaryKey",deptno);
			if(!list.isEmpty())
				deptVO = list.get(0);
		} catch(Exception e) {
			System.err.println(e);
			throw e;
		} finally {
			if(session!=null)
			    session.close();
		}
		return deptVO;
	}

	@Override
	public List<DeptVO> getAll() {
		SqlSession session = null;
		List<DeptVO> list = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			list = session.selectList("com.dept.model.DeptDAO_interface.getAll");
		} catch(Exception e) {
			System.err.println(e);
			throw e;
		} finally {
			if(session!=null)
			    session.close();
		}
		return list;
	}

	@Override
	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
		SqlSession session = null;
		Set<EmpVO> set = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			List<DeptVO> list = session.selectList("com.dept.model.DeptDAO_interface.getEmpsByDeptno",deptno);
			if(!list.isEmpty()) {
				DeptVO deptVO = list.get(0);
			    set = new LinkedHashSet<EmpVO>(deptVO.getEmps());
			}
		} catch(Exception e) {
			System.err.println(e);
			throw e;
		} finally {
			if(session!=null)
			    session.close();
		}
		return set;
	}
}