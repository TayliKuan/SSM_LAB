package com.emp.model;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dept.model.DeptVO;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpVO implements java.io.Serializable{
	
	private Integer empno;
	
	@NotEmpty(message = "ename空白")
	private String ename;
	
	@NotEmpty(message = "job空白")
	private String job;
	
	private Date hiredate;
	
	@Max(value = 10000 , message = "sal太大")
	@Min(value = 0 , message = "sal太小")
	@NotNull(message = "sal空白")
	private Double sal;
	
	@Max(value = 10000 , message = "comm太大")
	@Min(value = 0 , message = "comm太小")
	@NotNull(message = "comm空白")
	private Double comm;
	
	
	private DeptVO deptVO;
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empno == null) ? 0 : empno.hashCode());
		result = prime * result + ((ename == null) ? 0 : ename.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpVO other = (EmpVO) obj;
		if (empno == null) {
			if (other.empno != null)
				return false;
		} else if (!empno.equals(other.empno))
			return false;
		if (ename == null) {
			if (other.ename != null)
				return false;
		} else if (!ename.equals(other.ename))
			return false;
		return true;
	}
	
	
	
}
