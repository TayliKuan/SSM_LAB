package com.expertise.model;

import java.util.List;

public class ExpService {

	private ExpDAO_interface dao;

	public ExpService() {
		dao = new ExpDAO();
	}

	public ExpVO addExp(String expdesc) {

		ExpVO expVO = new ExpVO();

		expVO.setExpdesc(expdesc);

		dao.insert(expVO);

		return expVO;
	}

	public ExpVO updateExp(String expno, String expdesc) {

		ExpVO expVO = new ExpVO();

		expVO.setExpdesc(expdesc);

		dao.update(expVO);

		return expVO;
	}

	public void deleteExp(String expno) {
		dao.delete(expno);
	}

	public ExpVO getOneExp(String expno) {
		return dao.findByPrimaryKey(expno);
	}

	public List<ExpVO> getAll() {
		return dao.getAll();
	}
}
