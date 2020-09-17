package com.student.model;

import java.util.List;

public class StuService {

	private StuDAO_interface dao;

	public StuService() {
		dao = new StuDAO();
	}

	public StuVO addStu(String stuname, String stupsw, String stumail, String stutel, byte[] stupic, String stusex,
			String stuadd, java.sql.Date stubir) {

		StuVO stuVO = new StuVO();

		stuVO.setStuname(stuname);
		stuVO.setStupsw(stupsw);
		stuVO.setStumail(stumail);
		stuVO.setStutel(stutel);
		stuVO.setStupic(stupic);
		stuVO.setStusex(stusex);
		stuVO.setStuadd(stuadd);
		stuVO.setStubir(stubir);

		dao.insert(stuVO);

		return stuVO;
	}

	public StuVO updateStu(String stuno, String stuname, String stupsw, String stumail, String stutel, String stuadd,
			Integer stupoint, String stusta, String stusex, java.sql.Date stubir, byte[] stupic) {

		StuVO stuVO = new StuVO();
		stuVO.setStuno(stuno);
		stuVO.setStuname(stuname);
		stuVO.setStupsw(stupsw);
		stuVO.setStumail(stumail);
		stuVO.setStutel(stutel);
		stuVO.setStuadd(stuadd);
		stuVO.setStupoint(stupoint);
		stuVO.setStusta(stusta);
		stuVO.setStusex(stusex);
		stuVO.setStubir(stubir);
		stuVO.setStupic(stupic);
		dao.update(stuVO);

		return stuVO;
	}

	public void deleteStu(String stuno) {
		dao.delete(stuno);
	}

	public StuVO getOneStu(String stuno) {
		return dao.findByPrimaryKey(stuno);
	}

	public StuVO getOneStuByMail(String stumail) {
		return dao.findByMail(stumail);
	}

	public StuVO getStuByMailNPsw(String stumail, String stupsw) {
		return dao.findByMailNPsw(stumail, stupsw);
	}

	public List<StuVO> getAll() {
		return dao.getAll();
	}
}
