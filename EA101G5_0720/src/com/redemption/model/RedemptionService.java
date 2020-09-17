package com.redemption.model;

import java.util.List;

public class RedemptionService {
	
	private RedemptionDAO_interface reddao ;
	
	public RedemptionService() {
		reddao = new RedemptionDAO();
	}
	
	public RedemptionVO addRed (String coano , Integer redprice) {
		RedemptionVO redVO = new RedemptionVO();
		redVO.setCoano(coano);
		redVO.setRedprice(redprice);
		reddao.insertRed(redVO);
		return redVO ;
	}
	
	public List<RedemptionVO> showAllRed(String coano){
		return reddao.selectAllRed(coano);
	}
	
	public List<RedemptionVO> showAll(){
		return reddao.selectAll();
	}
	
	public void alterRed (String redno) {
		reddao.updateSta(redno);
	}
	
	public void alterCoaPoint (String coano , Integer newpoint) {
		reddao.updatePoint(coano, newpoint);
	}
	
	public RedemptionVO getCoaByRed (String redno) {
		return reddao.getCoaByRedno(redno); //含有時間&coano的VO
	}
	
	public void changeLessonSta (String lessno) {
		reddao.updateLessonSta(lessno);
	}
	
	public void changeActSta (String actno) {
		reddao.updateActSta(actno);
	}
}
