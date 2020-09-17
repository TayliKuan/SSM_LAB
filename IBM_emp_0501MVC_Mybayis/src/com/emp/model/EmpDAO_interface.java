package com.emp.model;

import java.util.*;

public interface EmpDAO_interface {
          public void insert(EmpVO empVO);
          public void insert(List<EmpVO> empVoList);
          public void update(EmpVO empVO);
          public void delete(Integer empno);
          public EmpVO findByPrimaryKey(Integer empno);
          public List<EmpVO> findByDeptno(Integer deptno);
          public List<EmpVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
          public List<EmpVO> getAll(Map<String, String[]> map); 
}
