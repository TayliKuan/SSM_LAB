package com.authority.model;

import java.util.List;

public interface AuthorityDAO_interface {
	
	//系統管理員可操作功能(編號)
	public List<AuthorityVO> selectAllFuncSys(); 
	//一般管理員可操作功能(編號)
	public List<AuthorityVO> selectAllFuncNor(); 
	//一般管理員升為系統管理員(新增F007=員工管理)
	public void changeToSys (AuthorityVO authVO);
	//給與新進員工一般管理員權限(新增F001~F006)
	public void addNorAuth (AuthorityVO authVO);
}
