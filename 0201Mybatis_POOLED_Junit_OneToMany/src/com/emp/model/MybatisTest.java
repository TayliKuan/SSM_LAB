package com.emp.model;


import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MybatisTest {
	
	EmpDAO empDao = new EmpDAO();

	//查全部
	@Test
	public void testFindAll() {
		List<EmpVO> emps = empDao.getAll();
		for (EmpVO emp : emps) {
			System.out.println(emp);
			System.out.println(emp.getDeptVO().getDname());
			System.out.println(emp.getDeptVO().getLoc());
		}
	}
	
	//新增員工
	@Test
	public void testInsert() {
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
	@Test
	public void testUpdate() {
		EmpVO empVO = new EmpVO();
		empVO.setEmpno(7038);
		empVO.setEname("testupdate");
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
			empDao.delete(7038);
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
		//查兩個以上的條件
		@Test
		public void testfindEmpVOByCondition() {
			EmpVO empVO = new EmpVO();
			empVO.setEname("%Q%");
			empVO.setDeptno(10);
			List<EmpVO> emps = empDao.findEmpVOByCondition(empVO);
			for (EmpVO emp : emps) {
				System.out.println(emp);
			}
		}
		//用empno 查詢 多筆 FOREACH印出
		@Test
		public void testfindEmpNos() {
			QueryVO vo= new QueryVO();
			List<Integer> list = new ArrayList<Integer>();
			list.add(7001);
			list.add(7003);
			list.add(7004);
			vo.setEmpnos(list);
			
			List<EmpVO> emps = empDao.findEmpNos(vo);
			for (EmpVO e : emps) {
				System.out.println(e);
			}
		}
		
}
