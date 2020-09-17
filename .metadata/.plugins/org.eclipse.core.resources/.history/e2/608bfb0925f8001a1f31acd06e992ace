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
	
	@Size(min = 4, max = 16, message = "請確認姓名符合格式")
	@NotEmpty(message = "姓名不可空白")
	private String ename;
	
	
	@NotEmpty(message = "職位不可空白")
	private String job;
	
	
	@PastOrPresent(message = "過去和今天")
	@NotNull(message = "日期不可空白")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hiredate;
	
	
	@Min(value = 22000,message = "薪水不可小於22000")
	@NotNull(message = "薪水不能空白")
	private Integer sal;
	
	@NotNull(message = "獎金不能空白")
	private Integer comm;
	
	private Integer deptno;
	
	@Valid
	private DeptDoMain dept;
}
