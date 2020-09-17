package com.coach.model;

import java.util.List;

public class CoaService {

	private CoaDAO_interface dao;

	public CoaService() {
		dao = new CoaDAO();
	}

	public String addCoa(String coaname, String coapsw, String coamail, String coatel, String coaacc, byte[] coapic,
			String coasex, String coaintro) {

		CoaVO coaVO = new CoaVO();

		coaVO.setCoaname(coaname);
		coaVO.setCoapsw(coapsw);
		coaVO.setCoamail(coamail);
		coaVO.setCoatel(coatel);
		coaVO.setCoaacc(coaacc);
		coaVO.setCoapic(coapic);
		coaVO.setCoasex(coasex);
		coaVO.setCoaintro(coaintro);

		return dao.insert(coaVO);
	}

	public CoaVO updateCoa(String coano, String coaname, String coapsw, String coamail, String coatel, String coaacc,
			Integer coapoint, String coasta, byte[] coapic, String coasex, String coaintro, Integer coasctotal,
			Integer coascqty) {

		CoaVO coaVO = new CoaVO();
		coaVO.setCoano(coano);
		coaVO.setCoaname(coaname);
		coaVO.setCoapsw(coapsw);
		coaVO.setCoamail(coamail);
		coaVO.setCoatel(coatel);
		coaVO.setCoaacc(coaacc);
		coaVO.setCoapoint(coapoint);
		coaVO.setCoasta(coasta);
		coaVO.setCoapic(coapic);
		coaVO.setCoasex(coasex);
		coaVO.setCoaintro(coaintro);
		coaVO.setCoasctotal(coasctotal);
		coaVO.setCoascqty(coascqty);
		dao.update(coaVO);

		return coaVO;
	}

	public void deleteCoa(String coano) {
		dao.delete(coano);
	}

	public CoaVO getOneCoa(String coano) {
		return dao.findByPrimaryKey(coano);
	}

	public CoaVO getOneCoaByMail(String coamail) {
		return dao.findByMail(coamail);
	}

	public CoaVO getCoaByMailNPsw(String coamail, String coapsw) {
		return dao.findByMailNPsw(coamail, coapsw);
	}

	public List<CoaVO> getAll() {
		return dao.getAll();
	}
}
