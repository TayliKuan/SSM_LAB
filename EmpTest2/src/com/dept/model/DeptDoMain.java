package com.dept.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ValueGenerationType;

import com.emp.model.EmpDoMain;

public class DeptDoMain implements Serializable{
	
	
	private static final long serialVersionUID = 205659353862436108L;

	private Integer deptno;
	
	@NotEmpty(message = "部門名稱不可空白")
	private String dname;
	
	@NotEmpty(message = "地址不可空白")
	private String loc;
	
	@Valid
	private EmpDoMain emp;
	
	private List<EmpDoMain> emps;

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public EmpDoMain getEmp() {
		return emp;
	}

	public void setEmp(EmpDoMain emp) {
		this.emp = emp;
	}

	public List<EmpDoMain> getEmps() {
		return emps;
	}

	public void setEmps(List<EmpDoMain> emps) {
		this.emps = emps;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
