package com.expertise_own.model;

import java.util.List;

public interface ExpOwnDAO_interface {

	public void insert(ExpOwnVO expownVO);

	public void update(ExpOwnVO expownVO);

	public void delete(String coano, String expno);

	public ExpOwnVO findByPrimaryKey(String coano, String expno);

	public List<ExpOwnVO> getAll();

	public List<ExpOwnVO> getExpOwnsByCoano(String coano);

	public List<ExpOwnVO> getCoachesByExpno(String expno);
}
