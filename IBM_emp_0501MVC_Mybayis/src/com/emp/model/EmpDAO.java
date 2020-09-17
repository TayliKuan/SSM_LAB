package com.emp.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;

import com.dept.model.DeptVO;

import mybatis.util.MyBaitsUtil;

public class EmpDAO implements EmpDAO_interface {

	@Override
	public void insert(EmpVO empVO) {

		SqlSession session = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			session.insert("com.emp.model.EmpDAO_interface.insert",empVO);
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
	public void update(EmpVO empVO) {

		SqlSession session = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			session.update("com.emp.model.EmpDAO_interface.update",empVO);
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
	public void delete(Integer empno) {

		SqlSession session = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			session.delete("com.emp.model.EmpDAO_interface.delete",empno);
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
	public EmpVO findByPrimaryKey(Integer empno) {

		SqlSession session = null;
		EmpVO empVO = null;
		List<EmpVO> list = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			list = session.selectList("com.emp.model.EmpDAO_interface.findByPrimaryKey",empno);
			if(!list.isEmpty())
				empVO = list.get(0);
		} catch(Exception e) {
			System.err.println(e);
			throw e;
		} finally {
			if(session!=null)
			    session.close();
		}
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		SqlSession session = null;
		List<EmpVO> list = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			list = session.selectList("com.emp.model.EmpDAO_interface.getAll");
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
	public List<EmpVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> findByDeptno(Integer deptno) {
		// TODO Auto-generated method stub
		SqlSession session = null;
		List<EmpVO> list = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			list = session.selectList("com.emp.model.EmpDAO_interface.findByDeptno",deptno);
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
	public void insert(List<EmpVO> empVoList) {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = MyBaitsUtil.getSessionFactory().openSession();
			session.insert("com.emp.model.EmpDAO_interface.inserts",empVoList);
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
}