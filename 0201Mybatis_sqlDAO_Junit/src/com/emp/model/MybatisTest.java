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
	private SqlSession sqlSession;
	private EmpDAO_interface empDao;

	@Before
	public void init() throws Exception {
		// 1.讀取配置文件
		in = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 2.創建SqlSessionFactory工廠
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 3.使用工廠產生SqlSession
		sqlSession = factory.openSession();
		// 4.用SqlSession創Dao代理對象
		empDao = sqlSession.getMapper(EmpDAO_interface.class);
	}

	@After
	public void destroy() throws Exception {
		//commit
		sqlSession.commit();
		// 6.釋放資源
		sqlSession.close();
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
	
	//模糊查詢 依name
			@Test
			public void testFindByVO() {
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
