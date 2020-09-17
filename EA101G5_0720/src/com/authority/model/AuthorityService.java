package com.authority.model;

import java.util.List;

public class AuthorityService {
	
	private AuthorityDAO_interface authdao ;
	
	public AuthorityService() {
		authdao = new AuthorityDAO();
	}
	
	public List<AuthorityVO> getNorAuth(){
		return authdao.selectAllFuncNor();
	}
	
	public List<AuthorityVO> getSysAuth(){
		return authdao.selectAllFuncSys();
	}
	
	public AuthorityVO beTheSys(String empno) {
		AuthorityVO authVO = new AuthorityVO();
		authVO.setEmpno(empno);
		authdao.changeToSys(authVO);
		return authVO ;
	}
	
	//目前要跑此方法6次
	public AuthorityVO beTheNor(String empno , String funcno) {
		AuthorityVO authVO = new AuthorityVO();
		authVO.setEmpno(empno);
		authVO.setFunno(funcno);
		authdao.addNorAuth(authVO);
		return authVO ;
	}
}
