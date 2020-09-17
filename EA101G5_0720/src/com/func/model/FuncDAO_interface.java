package com.func.model;

import java.util.List;

public interface FuncDAO_interface {
	
	public void insertFunc (FuncVO FuncVO);
	public void modifyFunc (FuncVO FuncVO);
	public void deleteFunc (String funcno);
	public FuncVO selectByFuncno (String funcno);
	public List<FuncVO> selectAllFunc();
	public List<FuncVO> selectAllFuncSys();
	public List<FuncVO> selectAllFuncAuth(String esta);
}
