package com.coach.model;

import java.util.List;

public interface CoaDAO_interface {

	public String insert(CoaVO coaVO);

	public void update(CoaVO coaVO);

	public void delete(String coano);

	public CoaVO findByPrimaryKey(String coano);

	public List<CoaVO> getAll();

	public CoaVO findByMailNPsw(String coamail, String coapsw);

	public CoaVO findByMail(String coamail);

}
