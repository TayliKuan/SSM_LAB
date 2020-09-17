package com.information.model;

import java.util.List;

public interface InformationDAO_interface {
	
	public void insertInfo (InformationVO InformationVO);
	public void modifyInfo (InformationVO InformationVO);
	public void deleteInfo (String inno);
	public InformationVO selectByInno (String inno);
	public List<InformationVO> selectAllInfoByDate ();
}
