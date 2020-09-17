package com.reportt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ReporttDAO implements ReporttDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}

			private static final String INSERT_STMT = 
					"INSERT INTO REPORTT(REPNO,ARTNO,REPDESC,REPDATE,REPSTA) "
					+ "VALUES ('REP'||LPAD(TO_CHAR(REPORTT_SEQ.NEXTVAL),3,'0'),?,?,?,'未處理')";
			
			private static final String GET_ALL_STMT = 
					"SELECT REPNO,ARTNO,REPDESC,REPDATE,STUNO,COANO,REPSTA "
					+ "FROM REPORTT ORDER BY REPNO";
			
			private static final String GET_ONE_STMT = 
					"SELECT REPNO,ARTNO,REPDESC,REPDATE,STUNO,COANO,REPSTA "
					+ "FROM REPORTT WHERE REPNO = ?";
			
			private static final String DELETE = 
					"DELETE FROM REPORTT WHERE REPNO = ?";
			
			private static final String UPDATE = 
					"UPDATE REPORTT SET ARTNO= ?,REPDESC= ?,REPDATE= ?,STUNO= ?,COANO= ?,REPSTA= ? WHERE REPNO = ?";
			
			private static final String UPDATESTA = 
					"UPDATE REPORTT SET REPSTA= ? WHERE REPNO = ?";
			@Override
			public void insert(ReporttVO reporttVO) {
				Connection con = null;
				PreparedStatement pstmt = null;
				System.out.println("33333333333");
				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT);
					
					pstmt.setString(1, reporttVO.getArtNo());
					pstmt.setString(2, reporttVO.getRepDesc());
					pstmt.setString(3, reporttVO.getRepDate());
					
					pstmt.executeUpdate();
					System.out.println("44444444");
					
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

			@Override
			public void update(ReporttVO reporttVO) {
				System.out.println("update   update33333333333");
				Connection con = null;
				PreparedStatement pstmt = null;

				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATESTA);
					
					pstmt.setString(1, reporttVO.getRepSta());
					pstmt.setString(2, reporttVO.getRepNo());
					pstmt.executeUpdate();
					
				System.out.println("update   update44444444");
				
					// Handle any SQL errors
				} catch (SQLException se) {
					
					System.out.println("error......");
					throw new RuntimeException("A database error occured. "
							+ se.getMessage());
					// Clean up JDBC resources
				} finally {
					System.out.println("finally......");
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
			public void delete(String repno) {
				
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(DELETE);
					
					pstmt.setString(1,repno);
					
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

			@Override
			public ReporttVO findByPrimaryKey(String repno) {

				ReporttVO reporttVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_STMT);
					
					pstmt.setString(1, repno);
					
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						reporttVO= new ReporttVO();
						reporttVO.setRepNo(rs.getString("repno"));
						reporttVO.setArtNo(rs.getString("artno"));
						reporttVO.setRepDesc(rs.getString("repdesc"));
						reporttVO.setRepDate(rs.getString("repdate"));
						reporttVO.setStuNo(rs.getString("stuno"));
						reporttVO.setCoaNo(rs.getString("coano"));
						reporttVO.setRepSta(rs.getString("repsta"));
					}

				
					// Handle any SQL errors
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured. "
							+ se.getMessage());
					// Clean up JDBC resources
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
				return reporttVO;
			}

			@Override
			public List<ReporttVO> getAll() {
				List<ReporttVO> list = new ArrayList<ReporttVO>();
				ReporttVO reporttVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						reporttVO= new ReporttVO();
						reporttVO.setRepNo(rs.getString("repno"));
						reporttVO.setArtNo(rs.getString("artno"));
						reporttVO.setRepDesc(rs.getString("repdesc"));
						reporttVO.setRepDate(rs.getString("repdate"));
						reporttVO.setStuNo(rs.getString("stuno"));
						reporttVO.setCoaNo(rs.getString("coano"));
						reporttVO.setRepSta(rs.getString("repsta"));
						
						list.add(reporttVO); // Store the row in the vector
					}

					// Handle any SQL errors
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured. "
							+ se.getMessage());
					// Clean up JDBC resources
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
				
}