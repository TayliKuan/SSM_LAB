package com.article.model;

import java.util.List;



public interface ArticleDAO_interface{
	
	 public int add(ArticleVO articleVo);
     public void update(ArticleVO articleVo);
     public List<ArticleVO> getAll();
     public List<ArticleVO> getAllDisplay();
     //修改狀態
     public void delete(ArticleVO articleVO);

     public List<ArticleVO> getByStuno(String stuno);//查詢某學員全部文章
     
     public List<ArticleVO> getByCoano(String coano);//查詢某教練全部文章
     
     public ArticleVO getOneArtno(String artno);
     

}
