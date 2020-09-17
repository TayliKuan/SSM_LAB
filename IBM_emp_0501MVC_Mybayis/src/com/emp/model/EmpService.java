package com.emp.model;

import java.util.List;
import java.util.Map;

import com.dept.model.DeptVO;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
	}

	public EmpVO addEmp(String ename, String job, java.sql.Date hiredate,
			Double sal, Double comm, Integer deptno) {
		
		EmpVO empVO = new EmpVO();
		empVO.setEname(ename);
		empVO.setJob(job);
		empVO.setHiredate(hiredate);
		empVO.setSal(sal);
		empVO.setComm(comm);
		DeptVO deptVO = new DeptVO();
		deptVO.setDeptno(deptno);
		empVO.setDeptVO(deptVO);

		dao.insert(empVO);

		return empVO;
	}

	public EmpVO updateEmp(Integer empno, String ename, String job,
			java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {

		EmpVO empVO = new EmpVO();
		empVO.setEmpno(empno);
		empVO.setEname(ename);
		empVO.setJob(job);
		empVO.setHiredate(hiredate);
		empVO.setSal(sal);
		empVO.setComm(comm);
		DeptVO deptVO = new DeptVO();
		deptVO.setDeptno(deptno);
		empVO.setDeptVO(deptVO);

		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public EmpVO getOneEmp(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
	
	public List<EmpVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
