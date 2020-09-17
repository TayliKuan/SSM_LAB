package com.emp.model;

import java.util.*;

public interface EmpDAO_interface {
         
          public EmpVO findByPrimaryKey(Integer empno);
          public List<EmpVO> getAll();
          public List<EmpVO> findByDeptno(Integer deptno);
          public List<EmpVO> findByName(String ename);
          public List<EmpVO> findEmpVOByVO(QueryVO vo);
          //�ھڱ���d��
          public List<EmpVO> findEmpVOByCondition(EmpVO empVO);
         }
