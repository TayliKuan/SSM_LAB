package com.dept.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;

import com.MybatisUtil.MyBaitsUtil;
import com.emp.model.EmpVO;

public class DeptDAO implements DeptDAO_interface {

	@Override
	public void insert(DeptVO deptVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DeptVO deptVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer deptno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DeptVO findByPrimaryKey(Integer deptno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeptVO> getAll() {
		//1.�ھ�factory�o��SqlSession
		SqlSession session = MyBaitsUtil.getSessionFactory().openSession();
		//2.��SqlSession�d�ߦC��  ��selectList
		List<DeptVO> deptvos = session.selectList("com.dept.model.DeptDAO_interface.getAll");
		//3.����귽
		session.close();
		return deptvos;
	}

	@Override
	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
		// TODO Auto-generated method stub
		return null;
	}

	
}