package com.emp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import com.dept.model.DeptDoMain;
import lombok.Data;

@Data
public class EmpDoMain implements Serializable{
	


	private static final long serialVersionUID = -40661017804967115L;

	private Integer empno;
	
	@Size(min = 4, max = 16, message = "�нT�{�m�W�ŦX�榡")
	@NotEmpty(message = "�m�W���i�ť�")
	private String ename;
	
	
	@NotEmpty(message = "¾�줣�i�ť�")
	private String job;
	
	
	@PastOrPresent(message = "�L�h�M����")
	@NotNull(message = "������i�ť�")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hiredate;
	
	
	@Min(value = 22000,message = "�~�����i�p��22000")
	@NotNull(message = "�~������ť�")
	private Integer sal;
	
	@NotNull(message = "��������ť�")
	private Integer comm;
	
	private Integer deptno;
	
	@Valid
	private DeptDoMain dept;
}
