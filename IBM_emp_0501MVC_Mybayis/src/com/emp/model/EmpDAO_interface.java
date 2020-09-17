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
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
          public List<EmpVO> getAll(Map<String, String[]> map); 
}
