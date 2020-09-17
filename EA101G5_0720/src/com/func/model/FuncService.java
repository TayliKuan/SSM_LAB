package com.func.model;

import java.util.List;

public class FuncService {

	private FuncDAO_interface funcdao ;
	
	public FuncService() {
		funcdao = new FuncDAO();
	}
	
	public FuncVO addFunc (String funcname , String funcdesc) {
		FuncVO funcVO = new FuncVO();
		funcVO.setFuncname(funcname);
		funcVO.setFuncdesc(funcdesc);
		funcdao.insertFunc(funcVO);
		return funcVO ;
	}
	
	public FuncVO alterFunc (String funcname ,String funcdesc , String funcno) {
		FuncVO funcVO = new FuncVO();
		funcVO.setFuncname(funcname);
		funcVO.setFuncdesc(funcdesc);
		funcVO.setFuncno(funcno);
		funcdao.modifyFunc(funcVO);
		return funcVO ;
	}
	
	public void byeFunc (String funcno) {
		funcdao.deleteFunc(funcno);
	}
	
	public FuncVO getOneFunc (String funcno) {
		return funcdao.selectByFuncno(funcno);
	}
	
	public List<FuncVO> getAllFunc (){
		return funcdao.selectAllFunc();
	}
}
