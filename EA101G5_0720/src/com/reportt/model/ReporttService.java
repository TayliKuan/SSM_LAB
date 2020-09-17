package com.reportt.model;

import java.util.List;

import com.reportt.model.ReporttDAO;
import com.reportt.model.ReporttDAO_interface;
import com.reportt.model.ReporttVO;

public class ReporttService {
		
	private ReporttDAO_interface dao;

	public ReporttService() {
		dao = new ReporttDAO();
//		dao = new ReporttJDBCDAO();
	}
	
	
	public ReporttVO addReportt( String artno, String repdesc, String repdate) {
//		public ReporttVO addReportt(String repno, String artno, 
//				String repdesc,String repdate, String stuno,String coano,String repsta) {

		System.out.println("22222222");
		ReporttVO reporttVO = new ReporttVO();
		
//		reporttVO.setRepNo(repno);
		reporttVO.setArtNo(artno);
		reporttVO.setRepDesc(repdesc);
		reporttVO.setRepDate(repdate);
//		reporttVO.setCoaNo(coano);
//		reporttVO.setRepSta(repsta);
		dao.insert(reporttVO);
		System.out.println("this      "+artno);
		System.out.println("this      "+repdesc);
		System.out.println("this      "+repdate);
		
		System.out.println("5555555");
		return reporttVO;
	}
	
		public ReporttVO updateReportt(String repno, String artno, String repdesc, 
				String repdate,String stuno, String coano,String repsta) {
			System.out.println("update     update22222222");	
		ReporttVO reporttVO = new ReporttVO();
		
		reporttVO.setRepNo(repno);
		reporttVO.setArtNo(artno);
		reporttVO.setRepDesc(repdesc);
		reporttVO.setRepDate(repdate);
		reporttVO.setStuNo(stuno);
		reporttVO.setCoaNo(coano);
		reporttVO.setRepSta(repsta);
		dao.update(reporttVO);
		System.out.println("update     update55555555555");	
		return reporttVO;
	}

//	public void deleteReportt(String repno) {
//		dao.delete(repno);
//	}

	public ReporttVO getOneReportt(String repno) {
		return dao.findByPrimaryKey(repno);
	}

	public List<ReporttVO> getAll() {
		return dao.getAll();
	}
}