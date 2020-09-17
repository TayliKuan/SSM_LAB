package com.reportt.model;

import java.sql.*;
import java.util.*;

public class ReporttJDBCDAO implements ReporttDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST";
	String passwd = "123456";
	

	private static final String INSERT_STMT = 
			"INSERT INTO REPORTT(REPNO,ARTNO,REPDESC,REPDATE,STUNO,COANO,REPSTA) "
			+ "VALUES ('REP'||LPAD(TO_CHAR(REPORTT_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?)";
	
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

		@Override
		public void insert(ReporttVO reporttVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, reporttVO.getArtNo());
				pstmt.setString(2, reporttVO.getRepDesc());
				pstmt.setString(3, reporttVO.getRepDate());
				pstmt.setString(4, reporttVO.getStuNo());
				pstmt.setString(5, reporttVO.getCoaNo());
				pstmt.setString(6, reporttVO.getRepSta());
				

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, reporttVO.getArtNo());
				pstmt.setString(2, reporttVO.getRepDesc());
				pstmt.setString(3, reporttVO.getRepDate());
				pstmt.setString(4, reporttVO.getStuNo());
				pstmt.setString(5, reporttVO.getCoaNo());
				pstmt.setString(6, reporttVO.getRepSta());
				pstmt.setString(7, reporttVO.getRepNo());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public void delete(String repno) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1,repno);
				
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

		public static void main(String[] args) {
			ReporttJDBCDAO dao = new ReporttJDBCDAO();

			 //新增
//			ReporttVO reporttVO = new ReporttVO();
//			reporttVO.setArtNo("ART005");
//			reporttVO.setRepDesc("檢舉內容");
//			reporttVO.setRepDate("2030-01-01");
//			reporttVO.setStuNo("S005");
//			reporttVO.setCoaNo("C005");
//			reporttVO.setRepSta("檢舉通過");
//			
//			dao.insert(reporttVO);
//			System.out.println("新增成功");
//					

//			 // 修改
//			ReporttVO reporttVO2 = new ReporttVO();
//			reporttVO2.setArtNo("ART007");
//			reporttVO2.setRepDesc("3333333333修改內容");
//			reporttVO2.setRepDate("2020-01-01");
//			reporttVO2.setStuNo("S007");
//			reporttVO2.setCoaNo("C007");
//			reporttVO2.setRepSta("檢舉未通過");
//			reporttVO2.setRepNo("REP017");
//			dao.update(reporttVO2);
//			
//			System.out.println("修改成功");
					
			//RepNo,ArtNo,RepDesc,RepDate,StuNo,CoaNo,RepSta
			//repno,artno,repdesc,repdate,stuno,coano,repsta	

			 // 刪除
//			dao.delete("REP019");
//			System.out.println("DONE2");

			// 查詢
//			ReporttVO reporttVO3 = dao.findByPrimaryKey("REP001");
//			System.out.print(reporttVO3.getRepNo() + ",");
//			System.out.print(reporttVO3.getArtNo() + ",");
//			System.out.print(reporttVO3.getRepDesc()+ ",");
//			System.out.print(reporttVO3.getRepDate()+ ",");
//			System.out.print(reporttVO3.getStuNo()+ ",");
//			System.out.print(reporttVO3.getCoaNo()+ ",");
//			System.out.println(reporttVO3.getRepSta()+ ",");
//			System.out.println("----------------------------");
	
//			// 顯示列表
//			List<ReporttVO> list = dao.getAll();
//			for (ReporttVO reporttVO4 : list) {
//				System.out.print(reporttVO4.getRepNo() + ",");
//				System.out.print(reporttVO4.getArtNo() + ",");
//				System.out.print(reporttVO4.getRepDesc()+ ",");
//				System.out.print(reporttVO4.getRepDate()+ ",");
//				System.out.print(reporttVO4.getStuNo()+ ",");
//				System.out.print(reporttVO4.getCoaNo()+ ",");
//				System.out.println(reporttVO4.getRepSta()+ ",");
//				System.out.println("----------------------------");
//				System.out.println();
//			}
			
			
		}
	}	