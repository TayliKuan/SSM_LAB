package com.employee.model;

import java.util.List;

public interface EmployeeDAO_interface {
	
	public void insertEmp (EmployeeVO EmployeeVO);	//for回傳密碼給寄信
	public void modifyEmpSta (EmployeeVO EmployeeVO);
	public void deleteEmp (String empno);
	public EmployeeVO selectByEmpno (String empno);
	public List<EmployeeVO> selectAllEmp();
	public EmployeeVO forgetPsw (String eacc , String email);
	public EmployeeVO selectByEacc (String eacc);
}
