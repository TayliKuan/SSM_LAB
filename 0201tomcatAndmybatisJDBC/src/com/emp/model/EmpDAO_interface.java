package com.emp.model;

import java.util.*;

public interface EmpDAO_interface {
          public void insert(EmpVO empVO);
          public void update(EmpVO empVO);
          public void delete(Integer empno);
          public EmpVO findByPrimaryKey(Integer empno);
          public List<EmpVO> getAll();
          public List<EmpVO> findByDeptno(Integer deptno);
          public List<EmpVO> findByName(String ename);
          public int findTotal();
          public List<EmpVO> findEmpVOByVO(QueryVO vo);
         }
