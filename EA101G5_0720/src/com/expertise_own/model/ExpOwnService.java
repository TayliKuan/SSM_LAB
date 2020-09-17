package com.expertise_own.model;

import java.util.List;

public class ExpOwnService {

	private ExpOwnDAO_interface dao;

	public ExpOwnService() {
		dao = new ExpOwnDAO();
	}

	public ExpOwnVO addExpOwn(String coano, String expno, byte[] expown) {

		ExpOwnVO expownVO = new ExpOwnVO();

		expownVO.setCoano(coano);
		expownVO.setExpno(expno);
		expownVO.setExpown(expown);

		dao.insert(expownVO);

		return expownVO;
	}

	public ExpOwnVO updateExpOwn(String coano, String expno, byte[] expown) {

		ExpOwnVO expownVO = new ExpOwnVO();

		expownVO.setExpown(expown);

		dao.update(expownVO);

		return expownVO;
	}

	public void deleteExp(String expno, String coano) {
		dao.delete(expno, coano);
	}

	public ExpOwnVO getOneExpOwn(String expno, String coano) {
		return dao.findByPrimaryKey(expno, coano);
	}

	public List<ExpOwnVO> getAll() {
		return dao.getAll();
	}

	public List<ExpOwnVO> getExpOwnsByCoano(String coano) {
		return dao.getExpOwnsByCoano(coano);
	}

	public List<ExpOwnVO> getCoachesByExpno(String expno) {
		return dao.getCoachesByExpno(expno);
	}
}
