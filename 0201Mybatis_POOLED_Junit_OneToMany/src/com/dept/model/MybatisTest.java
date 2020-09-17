package com.dept.model;


import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MybatisTest {
	
	DeptDAO deptDao = new DeptDAO();

	//查全部
	@Test
	public void testFindAll() {
		List<DeptVO> depts = deptDao.getAll();
		for (DeptVO d : depts) {
			System.out.println("--------------");
			System.out.println(d);
			System.out.println(d.getEmpvos());
		}
	}
	
	
}
