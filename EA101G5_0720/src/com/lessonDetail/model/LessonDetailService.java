package com.lessonDetail.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.lesson.model.LessonDAO_interface;
import com.lesson.model.LessonVO;
import com.lessonTime.model.LessonTimeVO;

public class LessonDetailService {
	private LessonDetailDAO_interface dao;
	
	public LessonDetailService() {
		dao = new LessonDetailDAO();//JNDI
	}

	/*新增時段明細 由新增時段呼叫 同步進行*/
	public LessonDetailVO addLessonDetail(String lessno,String ltime_no, String ltime_ss,java.sql.Date ltime_date) {
		LessonDetailVO LessonDetailVO = new LessonDetailVO();
		
		LessonVO LessonVO=new LessonVO();
		LessonVO.setLessno(lessno);
		
		List<LessonTimeVO> list = new ArrayList<LessonTimeVO>();

		LessonTimeVO LessonTimeVO = new LessonTimeVO();
		LessonTimeVO.setLtime_no(ltime_no);
		LessonTimeVO.setLtime_ss(ltime_ss);
		LessonTimeVO.setLtime_date(ltime_date);
		list.add(LessonTimeVO);
		dao.insert(LessonVO, list);

		return LessonDetailVO;
		
	}

	public List<LessonDetailVO> findAllTimesBylessno(String lessno) {
		return dao.findAllTimesBylessno(lessno);
	}

	public List<LessonDetailVO> getAllLessonDetail() {
		return dao.getAll();
	}
}
