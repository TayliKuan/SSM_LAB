package com.dept.model;

import java.util.*;
import com.emp.model.EmpVO;

public interface DeptDAO_interface {
	      public void insert(DeptVO deptVO);
          public void update(DeptVO deptVO);
          public void delete(Integer deptno);
          public DeptVO findByPrimaryKey(Integer deptno);
	      public List<DeptVO> getAll();
	      //查詢某部門的員工(一對多)(回傳 Set)
	      public Set<EmpVO> getEmpsByDeptno(Integer deptno);
}
