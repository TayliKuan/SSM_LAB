package com.dept.model;

import java.util.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.emp.model.EmpVO;

import lombok.Data;

@Data
public class DeptVO  implements java.io.Serializable{
	private Integer deptno;
	
	@NotEmpty(message = "dname空白")
	private String dname;
	
	@NotEmpty(message = "loc空白")
	private String loc;
	
	@Valid
	private List<EmpVO> emps = new ArrayList<EmpVO>();
	
	
	
}
