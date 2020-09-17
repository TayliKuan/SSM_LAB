package com.emp.model;

import java.util.List;

import lombok.Data;

@Data
public class QueryVO {
	private EmpVO empVO;
	private List<Integer> empnos;
}
