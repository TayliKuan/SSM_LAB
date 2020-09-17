package com.emp.model;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MybatisTest {
	private InputStream in;
	private EmpDAO_interface empDao;

	@Before
	public void init() throws Exception {
		// 1.讀取配置文件
		in = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2.創建SqlSessionFactory工廠
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 3.使用工廠產生DAO
		empDao=new EmpDAO(factory);
	}

	@After
	public void destroy() throws Exception {
		in.close();
	}

	//查全部
	@Test
	public void testFindAll() {
		List<EmpVO> emps = empDao.getAll();
		for (EmpVO emp : emps) {
			System.out.println(emp);
		}
	}
	
	//新增員工
	@Test
	public void testInsert() {
		EmpVO empVO = new EmpVO();
		empVO.setEname("testDAO");
		empVO.setJob("boss");
		empVO.setHiredate(java.sql.Date.valueOf("2005-01-01"));
		empVO.setSal(new Double(50000));
		empVO.setComm(new Double(500));
		empVO.setDeptno(10);
		System.out.println("新增前"+empVO.getEmpno());
		empDao.insert(empVO);
		System.out.println("新增後"+empVO.getEmpno());
	}
	
	//修改員工
	@Test
	public void testUpdate() {
		EmpVO empVO = new EmpVO();
		empVO.setEmpno(7016);
		empVO.setEname("updateDAO");
		empVO.setJob("worker");
		empVO.setHiredate(java.sql.Date.valueOf("2020-01-01"));
		empVO.setSal(new Double(30000));
		empVO.setComm(new Double(100));
		empVO.setDeptno(20);

		empDao.update(empVO);
	}
	
	//刪除員工
		@Test
		public void testdelete() {
			
			empDao.delete(7022);
		}
		
	//查一個員工
		@Test
		public void testone() {
			
			EmpVO empVO = empDao.findByPrimaryKey(7001);
			System.out.println(empVO);
		}
		
	//模糊查詢 依部門
		@Test
		public void testFindByDeptno() {
			List<EmpVO> emps = empDao.findByDeptno(20);
			for (EmpVO emp : emps) {
				System.out.println(emp);
			}
		}
	
	//模糊查詢 依name
		@Test
		public void testFindByName() {
			List<EmpVO> emps = empDao.findByName("%M%");
			for (EmpVO emp : emps) {
				System.out.println(emp);
			}
		}
	//查詢筆數
		@Test
		public void testFindTotal() {
			int count = empDao.findTotal();
			System.out.println(count);
		}
		
}
