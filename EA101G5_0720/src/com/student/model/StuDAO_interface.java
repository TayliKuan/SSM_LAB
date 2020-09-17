package com.student.model;

import java.util.List;

public interface StuDAO_interface {

	public String insert(StuVO stuVO);

	public void update(StuVO stuVO);

	public void delete(String stuno);

	public StuVO findByPrimaryKey(String stuno);

	public List<StuVO> getAll();

	public StuVO findByMailNPsw(String stumail, String stupsw);

	StuVO findByMail(String stumail);

}
