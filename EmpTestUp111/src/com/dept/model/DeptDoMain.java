package com.dept.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ValueGenerationType;

import com.emp.model.EmpDoMain;

import lombok.Data;

@Data
public class DeptDoMain implements Serializable{
	
	
	private static final long serialVersionUID = 205659353862436108L;

	private Integer deptno;
	
	@NotEmpty(message = "部門名稱不可空白")
	private String dname;
	
	@NotEmpty(message = "地址不可空白")
	private String loc;
	
	@Valid
	private EmpDoMain emp;
	
	private List<EmpDoMain> emps = new ArrayList<>();

	public DeptDoMain() {
		super();
		System.out.println("!");
	}

	
	
}
