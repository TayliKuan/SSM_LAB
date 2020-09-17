package com.deposit.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DepositDAO implements DepositDAO_interface{
	
		//建立連線池
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		//新增點數儲值紀錄
		private static final String INSERT_STMT = "INSERT INTO DEPOSIT VALUES (to_char(sysdate,'yyyymmdd')||'-DEP'||LPAD(to_char(DEPOSIT_SEQ.nextval), 3, '0'),?,CURRENT_TIMESTAMP,?)";
		//查單一學員所有儲值紀錄
		private static final String SELECT_ALL_BYDEPNO = "SELECT * FROM DEPOSIT WHERE STUNO = ?";
		//學員點數增加
		private static final String ADD_STUPOINT = "UPDATE STUDENT SET STUPOINT = ? WHERE STUNO = ?";
		
		@Override
		public void insertDep(DepositVO depVO) {
			
			Connection con = null ;
			PreparedStatement pstmt = null ;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, depVO.getStuno());
				//pstmt.setTimestamp(2, depVO.getDepdate());
				pstmt.setInt(2, depVO.getDepprice());
				pstmt.executeUpdate();
				
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
		public List<DepositVO> selectAll(String stuno) {
			
			List<DepositVO> deplist = new ArrayList<DepositVO>();
			Connection con = null ;
			PreparedStatement pstmt = null ;
			ResultSet rs = null ;
			DepositVO depVO = null ;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(SELECT_ALL_BYDEPNO);
				pstmt.setString(1, stuno);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					depVO = new DepositVO();
					depVO.setDepno(rs.getString("depno"));
					depVO.setStuno(rs.getString("stuno"));
					depVO.setDepdate(rs.getTimestamp("depdate"));
					depVO.setDepprice(rs.getInt("depprice"));
					deplist.add(depVO);
				}
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
			return deplist;
		}

		@Override
		public void alterStuPoint(String stuno, Integer newpoint) {
			
			Connection con = null ;
			PreparedStatement pstmt = null ;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(ADD_STUPOINT);
				pstmt.setString(2,stuno);
				pstmt.setInt(1, newpoint);
				pstmt.executeUpdate();
				
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
