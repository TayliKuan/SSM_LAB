package com.emp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.dept.model.DeptDoMain;

import lombok.Data;

@Data
public class EmpDoMain implements Serializable{
	private Integer empno;
	
	@NotEmpty(message = "姓名不可空白")
	private String ename;
	

	@NotEmpty(message = "職位不可空白")
	private String job;
	
	@NotNull(message = "日期不可空白")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date hiredate;
	
	@NotNull(message = "薪水不能空白")
	private Integer sal;
	
	@NotNull(message = "獎金不能空白")
	private Integer comm;
	
	@NotNull(message = "部門不能空白")
	private Integer deptno;
	private DeptDoMain dept;
}
