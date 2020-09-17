package com.emp.model;
import java.sql.*;

import lombok.Data;

@Data
public class EmpVO implements java.io.Serializable{
	private Integer empno;
	private String ename;
	private String job;
	private Date hiredate;
	private Double sal;
	private Double comm;
	private Integer deptno;
	
}
