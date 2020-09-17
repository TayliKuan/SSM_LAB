package com.emp.model;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {
	private static InputStream in;
	private static SqlSession sqlSession;
	private static EmpDAO_interface empDao;

	static {
		try {
			// 1.讀取配置文件
			in = Resources.getResourceAsStream("SqlMapConfig.xml");
			// 2.創建SqlSessionFactory工廠
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
			// 3.使用工廠產生SqlSession
			sqlSession = factory.openSession();
			// 4.用SqlSession創Dao代理對象
			empDao = sqlSession.getMapper(EmpDAO_interface.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
//		testFindAll();
//		testInsert();
//		testUpdate();
//		testdelete();
		testone();
//		testFindByDeptno();
//		testFindByName();
//		testFindTotal();
//		testFindByVO();
		destroy();
	}
		

public static void destroy() throws Exception {
	//commit
	sqlSession.commit();
	// 6.釋放資源
	sqlSession.close();
	in.close();
}

//查全部
public static void testFindAll() {
	List<EmpVO> emps = empDao.getAll();
	for (EmpVO emp : emps) {
		System.out.println(emp);
	}
}

//新增員工
public static void testInsert() {
	EmpVO empVO = new EmpVO();
	empVO.setEname("Q");
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
public static void testUpdate() {
	EmpVO empVO = new EmpVO();
	empVO.setEmpno(7015);
	empVO.setEname("testupdate");
	empVO.setJob("worker");
	empVO.setHiredate(java.sql.Date.valueOf("2020-01-01"));
	empVO.setSal(new Double(30000));
	empVO.setComm(new Double(100));
	empVO.setDeptno(20);

	empDao.update(empVO);
}

//刪除員工
	public static void testdelete() {
		
		empDao.delete(7002);
	}
	
//查一個員工
	public static void testone() {
		
		EmpVO empVO = empDao.findByPrimaryKey(7001);
		System.out.println(empVO);
	}
	
//模糊查詢 依部門
	public static void testFindByDeptno() {
		List<EmpVO> emps = empDao.findByDeptno(20);
		for (EmpVO emp : emps) {
			System.out.println(emp);
		}
	}

//模糊查詢 依name
	public static void testFindByName() {
		List<EmpVO> emps = empDao.findByName("%M%");
		for (EmpVO emp : emps) {
			System.out.println(emp);
		}
	}
//查詢筆數
	public static void testFindTotal() {
		int count = empDao.findTotal();
		System.out.println(count);
	}
//模糊查詢 依name
	public static void testFindByVO() {
			QueryVO vo= new QueryVO();
			EmpVO empVO = new EmpVO();
			empVO.setEname("%M%");
			vo.setEmpVO(empVO);
			List<EmpVO> emps = empDao.findEmpVOByVO(vo);
			for (EmpVO e : emps) {
				System.out.println(e);
			}
		}
}
