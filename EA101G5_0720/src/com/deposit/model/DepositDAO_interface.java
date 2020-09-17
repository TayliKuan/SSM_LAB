package com.deposit.model;

import java.util.List;

public interface DepositDAO_interface {
	
	public void insertDep (DepositVO depVO);
	public List<DepositVO> selectAll (String stuno);
	public void alterStuPoint (String stuno , Integer newpoint);
}
