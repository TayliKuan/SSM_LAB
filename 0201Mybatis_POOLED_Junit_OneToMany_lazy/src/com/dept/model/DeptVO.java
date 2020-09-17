package com.dept.model;

import java.sql.Date;
import java.util.List;

import com.emp.model.EmpVO;

import lombok.Data;
@Data
public class DeptVO  implements java.io.Serializable{
	private Integer deptno;
	private String dname;
	private String loc;
	
	//¤@¹ï¦h
	private List<EmpVO> empvos;
	
}
