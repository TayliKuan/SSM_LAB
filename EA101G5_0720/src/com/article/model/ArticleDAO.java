package com.article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.article.model.ArticleVO;


public class ArticleDAO implements ArticleDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		
	}
	
	private static final String ADD = 
			"INSERT INTO ARTICLE (ARTNO,ARTTITLE,ARTCON,ARTDATE,STUNO,COANO,ARTPIC,ARTSTA) "
			+ "VALUES ('ART'||LPAD(TO_CHAR(ARTICLE_SEQ.NEXTVAL), 3, '0'), ?, ?,?,?,?,?,?)";
	
	private static final String UPDATE = 
			"UPDATE ARTICLE SET ARTTITLE=?, ARTCON=?,ARTDATE=?,"
					+ "STUNO=?,COANO=?,ARTPIC=? ,ARTSTA=? WHERE ARTNO = ?";
	
	private static final String GET_ALL_CSU = 
			"SELECT ARTNO,ARTTITLE,ARTCON,ARTDATE,STUNO,ARTPIC,ARTSTA FROM ARTICLE WHERE STUNO= ? ";
	
	private static final String GET_ALL_STUNO = 
			"SELECT ARTNO,ARTTITLE,ARTCON,ARTDATE,STUNO,ARTPIC,ARTSTA FROM ARTICLE WHERE ARTSTA = '文章顯示' AND STUNO= ? ";
	
	private static final String GET_ALL_COANO = 
			"SELECT ARTNO,ARTTITLE,ARTCON,ARTDATE,COANO,ARTPIC,ARTSTA FROM ARTICLE WHERE ARTSTA = '文章顯示' AND COANO= ? ";
	
	private static final String GETONEARTNO = 
			"SELECT ARTNO,ARTTITLE,ARTCON,ARTDATE,STUNO,COANO,ARTPIC,ARTSTA FROM ARTICLE WHERE ARTNO= ? ";
	
	private static final String GET_ALL_STMT = 
			"SELECT ARTNO,ARTTITLE,ARTCON,ARTDATE,STUNO,COANO,ARTPIC,ARTSTA FROM ARTICLE";
	
	private static final String GET_ALL_Display = 
			"SELECT ARTNO,ARTTITLE,ARTCON,ARTDATE,STUNO,COANO,ARTPIC,ARTSTA FROM ARTICLE WHERE ARTSTA = '文章顯示'";
	
	private static final String DELETE = 
			"UPDATE ARTICLE SET ARTSTA = '文章隱藏' WHERE ARTNO = ?";
	
	//最新文章
	private static final String GET_NEW_STMT = 
			"SELECT * FROM ARTICLE WHERE ARTSTA = '文章顯示'  ORDER BY ARTDATE DESC";
	
	@Override//新增
	public int add(ArticleVO articleVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();// 取得連線
			pstmt = con.prepareStatement(ADD);
			
			pstmt.setString(1, articleVO.getArtTitle());
			pstmt.setString(2, articleVO.getArtCon());
			pstmt.setString(3, articleVO.getArtDate());
			pstmt.setString(4, articleVO.getStuNo());
			pstmt.setString(5, articleVO.getCoaNo());
			pstmt.setBytes(6,articleVO.getArtPic());
			pstmt.setString(7, articleVO.getArtSta());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}
	
	@Override//更新
	public void update(ArticleVO articleVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();// 取得連線
			pstmt = con.prepareStatement(UPDATE);
			
//			System.out.println("11111111");
//			pstmt.setString(1, "測試標題");
//			pstmt.setString(2, "測試內容");
//			pstmt.setString(3, "測試時間");
//			pstmt.setString(4, "S022");
//			pstmt.setString(5, "C022");
//			pstmt.setString(6, "文章顯示");
//			pstmt.setString(7, "ART001");
//			pstmt.setBytes (8, null);
			
			System.out.println("title:" + articleVO.getArtTitle());
			System.out.println("getArtCon:" + articleVO.getArtCon());
			System.out.println("getArtDate:" + articleVO.getArtDate());
			System.out.println("getStuNo:" + articleVO.getStuNo());
			
			System.out.println("getCoaNo:" + articleVO.getCoaNo());
			System.out.println("getArtPic:" + articleVO.getArtPic());
			System.out.println("getArtSta:" + articleVO.getArtSta());
			System.out.println("getArtNo:" + articleVO.getArtNo());

			
			pstmt.setString(1, articleVO.getArtTitle());
			pstmt.setString(2, articleVO.getArtCon());
			pstmt.setString(3, articleVO.getArtDate());
			pstmt.setString(4, articleVO.getStuNo());
			
			pstmt.setString(5, articleVO.getCoaNo());
			pstmt.setBytes (6, articleVO.getArtPic());
			pstmt.setString(7, articleVO.getArtSta());
			pstmt.setString(8, articleVO.getArtNo());

			pstmt.executeUpdate();
			System.out.println("222222222");
			// Handle any SQL errors
		} catch (SQLException se) {
			System.out.println("333333333333");
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			System.out.println("44444");
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public ArticleVO getOneArtno(String artno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVO articleVO = null;
		
		try {
			con = ds.getConnection();// 取得連線
			pstmt = con.prepareStatement(GETONEARTNO);
			
			pstmt.setString(1,artno);
			rs = pstmt.executeQuery();
								
				while (rs.next()) {
					articleVO = new ArticleVO();
					articleVO.setArtNo(rs.getString("artno"));
					articleVO.setArtTitle(rs.getString("arttitle"));
					articleVO.setArtCon(rs.getString("artcon"));
					articleVO.setArtDate(rs.getString("artdate"));
					articleVO.setStuNo(rs.getString("stuno"));
					articleVO.setCoaNo(rs.getString("coano"));
					articleVO.setArtPic(rs.getBytes("artpic"));
					articleVO.setArtSta(rs.getString("artsta"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return articleVO;
	}
	
	@Override//查詢全部
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();// 取得連線
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtNo(rs.getString("artno"));
				articleVO.setArtTitle(rs.getString("arttitle"));
				articleVO.setArtCon(rs.getString("artcon"));
				articleVO.setArtDate(rs.getString("artdate"));
				articleVO.setStuNo(rs.getString("stuno"));
				articleVO.setCoaNo(rs.getString("coano"));
				articleVO.setArtPic(rs.getBytes("artpic"));
				articleVO.setArtSta(rs.getString("artsta"));
				list.add(articleVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override//查詢顯示
	public List<ArticleVO> getAllDisplay() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();// 取得連線
			pstmt = con.prepareStatement(GET_ALL_Display);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtNo(rs.getString("artno"));
				articleVO.setArtTitle(rs.getString("arttitle"));
				articleVO.setArtCon(rs.getString("artcon"));
				articleVO.setArtDate(rs.getString("artdate"));
				articleVO.setStuNo(rs.getString("stuno"));
				articleVO.setCoaNo(rs.getString("coano"));
				articleVO.setArtPic(rs.getBytes("artpic"));
				articleVO.setArtSta(rs.getString("artsta"));
				list.add(articleVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	

	@Override
	public List<ArticleVO> getByStuno(String stuno) {
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();// 取得連線
			pstmt = con.prepareStatement(GET_ALL_STUNO);
			pstmt.setString(1,stuno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtNo(rs.getString("artno"));
				articleVO.setArtTitle(rs.getString("arttitle"));
				articleVO.setArtCon(rs.getString("artcon"));
				articleVO.setArtDate(rs.getString("artdate"));
				articleVO.setStuNo(rs.getString("stuno"));
				articleVO.setArtPic(rs.getBytes("artpic"));
				articleVO.setArtSta(rs.getString("artsta"));
				list.add(articleVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	@Override
	public List<ArticleVO> getByCoano(String coano){
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();// 取得連線
			pstmt = con.prepareStatement(GET_ALL_COANO);
			pstmt.setString(1,coano);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtNo(rs.getString("artno"));
				articleVO.setArtTitle(rs.getString("arttitle"));
				articleVO.setArtCon(rs.getString("artcon"));
				articleVO.setArtDate(rs.getString("artdate"));
				articleVO.setCoaNo(rs.getString("coano"));
				articleVO.setArtPic(rs.getBytes("artpic"));
				articleVO.setArtSta(rs.getString("artsta"));
				list.add(articleVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public void delete(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();// 取得連線
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, articleVO.getArtNo());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
		
}

