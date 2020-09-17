package com.dept.model;

import java.util.List;
import java.util.Set;
import com.emp.model.EmpVO;

public class DeptService {

	private DeptDAO_interface dao;

	public DeptService() {
		dao = new DeptDAO();
	}

	public List<DeptVO> getAll() {
		return dao.getAll();
	}

	public DeptVO getOneDept(Integer deptno) {
		return dao.findByPrimaryKey(deptno);
	}

	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
		return dao.getEmpsByDeptno(deptno);
	}

	public void deleteDept(Integer deptno) {
		dao.delete(deptno);
	}
}
