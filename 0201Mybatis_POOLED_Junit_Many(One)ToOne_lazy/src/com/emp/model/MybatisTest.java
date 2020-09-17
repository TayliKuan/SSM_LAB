package com.emp.model;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MybatisTest {
	

//	  public static void main(String[] args) {
//	    Logger logger = LoggerFactory.getLogger(MybatisTest.class);
//	    logger.info("Hello World");
//	  }
	
	EmpDAO empDao = new EmpDAO();

	//查全部
	@Test
	public void testFindAll() {
		List<EmpVO> emps = empDao.getAll();
		for (EmpVO emp : emps) {
			System.out.println(emp);
//			System.out.println(emp.getDeptVO().getDname());
//			System.out.println(emp.getDeptVO().getLoc());
		}
	}
}
