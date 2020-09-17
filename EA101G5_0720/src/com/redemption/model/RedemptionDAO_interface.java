package com.redemption.model;

import java.util.List;

public interface RedemptionDAO_interface {
	
	public List<RedemptionVO> selectAllRed(String coano);
	public List<RedemptionVO> selectAll();
	public void insertRed(RedemptionVO redVO);
	public void updateSta(String redno);
	public void updatePoint (String coano , Integer coapoint);
	public RedemptionVO getCoaByRedno (String redno);
	public void updateLessonSta (String lessno);
	public void updateActSta (String actno);
}
