package com.emp.model;

import java.util.*;

public interface EmpDAO_interface {
         
          public EmpVO findByPrimaryKey(Integer empno);
          public List<EmpVO> getAll();
          public List<EmpVO> findByDeptno(Integer deptno);
          public List<EmpVO> findByName(String ename);
          public List<EmpVO> findEmpVOByVO(QueryVO vo);
        //根據條件查詢
          public List<EmpVO> findEmpVOByCondition(EmpVO empVO);
         //根據QueryVO中提供的empnos 集合 查詢
          public List<EmpVO> findEmpNos(QueryVO vo);
}
