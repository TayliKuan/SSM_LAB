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
	
	@NotEmpty(message = "�m�W���i�ť�")
	private String ename;
	

	@NotEmpty(message = "¾�줣�i�ť�")
	private String job;
	
	@NotNull(message = "������i�ť�")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date hiredate;
	
	@NotNull(message = "�~������ť�")
	private Integer sal;
	
	@NotNull(message = "��������ť�")
	private Integer comm;
	
	@NotNull(message = "��������ť�")
	private Integer deptno;
	private DeptDoMain dept;
}
