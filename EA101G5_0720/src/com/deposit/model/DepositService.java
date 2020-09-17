package com.deposit.model;

import java.util.List;

public class DepositService {

	private DepositDAO_interface depdao ;
	
	public DepositService() {
		depdao = new DepositDAO();
	}
	
	public DepositVO addDeposit (String stuno , Integer depprice) {
		DepositVO depVO = new DepositVO();
		depVO.setStuno(stuno);
		depVO.setDepprice(depprice);
		depdao.insertDep(depVO);
		return depVO ;
	}
	
	public List<DepositVO> showAllDep (String stuno){
		return depdao.selectAll(stuno);
	}
	
	public void alterStuPoint (String stuno , Integer stupoint) {
		depdao.alterStuPoint(stuno, stupoint);
	}
}
