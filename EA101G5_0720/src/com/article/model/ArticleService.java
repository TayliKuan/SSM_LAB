package com.article.model;

import java.util.List;

import com.article.model.ArticleDAO_interface;
import com.article.model.ArticleVO;
import com.article.model.ArticleDAO;

public class ArticleService {
	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleDAO();
//		dao = new ArticleJDBCDAO();
	}
	
	
	
	public ArticleVO add( String arttitle, 
			String artcon,String artdate, String stuno,String coano,byte[] artpic,String artsta) {

		ArticleVO articleVO = new ArticleVO();
//		articleVO.setArtNo(artNo);
		articleVO.setArtTitle(arttitle);
		articleVO.setArtCon(artcon);
		articleVO.setArtDate(artdate);
		articleVO.setStuNo(stuno);
		articleVO.setCoaNo(coano);
		articleVO.setArtPic(artpic);
		articleVO.setArtSta(artsta);
		dao.add(articleVO);

		return articleVO;
	}
	
	public ArticleVO update(String artno, String arttitle, String artcon,String artdate,
		String stuno,String coano,byte[] artpic,String artsta) {
		
		ArticleVO articleVO = new ArticleVO();
		
		articleVO.setArtNo(artno);
		articleVO.setArtTitle(arttitle);
		articleVO.setArtCon(artcon);
		articleVO.setArtDate(artdate);
		articleVO.setStuNo(stuno);
		articleVO.setCoaNo(coano);
		articleVO.setArtPic(artpic);
		articleVO.setArtSta(artsta);
		
		dao.update(articleVO);
		return articleVO;
	}
	
	public List<ArticleVO> getByStuno(String stuno){
		return dao.getByStuno(stuno);
	}
	
	public List<ArticleVO> getByCoano(String coano){
		return dao.getByCoano(coano);
		
	}
	public ArticleVO getOneArtno(String artno) {
		return dao.getOneArtno(artno);
	}
	
	public List<ArticleVO> getAll() {
		
		return dao.getAll();
	}
	
	public List<ArticleVO> getAllDisplay() {
		
		return dao.getAllDisplay();
	}

	public void deleteArticle(String artno) {
		ArticleVO articleVO = new ArticleVO();
		articleVO.setArtNo(artno);
		dao.delete(articleVO);
	}
	
	
}