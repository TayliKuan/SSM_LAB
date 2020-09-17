package com.expertise.model;

import java.util.List;
import java.util.Set;

public interface ExpDAO_interface {
	
	public void insert(ExpVO expVO);

	public void update(ExpVO expVO);

	public void delete(String expno);

	public ExpVO findByPrimaryKey(String expno);

	public List<ExpVO> getAll();
	

}
