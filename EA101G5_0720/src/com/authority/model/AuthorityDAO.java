package com.authority.model;

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

public class AuthorityDAO implements AuthorityDAO_interface{
	
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
			
			private static final String SELECTSYS_STMT = "SELECT ESTA , FUNCNO FROM EMPLOYEE E JOIN AUTHORITY A ON E.EMPNO = A.EMPNO WHERE ESTA = '系統管理員' ";
			private static final String SELECTNOR_STMT = "SELECT ESTA , FUNCNO FROM EMPLOYEE E JOIN AUTHORITY A ON E.EMPNO = A.EMPNO WHERE ESTA = '一般管理員' ";
			private static final String UPGRADESYS_STMT = "INSERT INTO AUTHORITY VALUES (?,'F007')";
			private static final String UPGRADENOR_STMT = "INSERT INTO AUTHORITY VALUES (?,?)";
			private static final String GET_FUNCBYEMPNO = "SELECT FUNCNO FROM AUTHORTY WHERE EMPNO = ?";

			@Override
			public List<AuthorityVO> selectAllFuncSys() {
				
				List<AuthorityVO>authlist = new ArrayList<AuthorityVO>();
				Connection con = null ;
				PreparedStatement pstmt = null ;
				ResultSet rs = null ;
				AuthorityVO authVO = null ;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(SELECTSYS_STMT);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						authVO = new AuthorityVO();
						authVO.setEmpno(rs.getString("empno"));
						authVO.setFunno(rs.getString("funcno"));
						authlist.add(authVO);
					}
				}  catch (SQLException se) {
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
				return authlist ;
			}

			@Override
			public List<AuthorityVO> selectAllFuncNor() {
				List<AuthorityVO>authlist = new ArrayList<AuthorityVO>();
				Connection con = null ;
				PreparedStatement pstmt = null ;
				ResultSet rs = null ;
				AuthorityVO authVO = null ;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(SELECTNOR_STMT);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						authVO = new AuthorityVO();
						authVO.setEmpno(rs.getString("empno"));
						authVO.setFunno(rs.getString("funcno"));
						authlist.add(authVO);
					}
				}  catch (SQLException se) {
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
				return authlist ;
			}

			@Override
			public void changeToSys(AuthorityVO authVO) {
				
				Connection con = null ;
				PreparedStatement pstmt = null ;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(UPGRADESYS_STMT);
					
					pstmt.setString(1, authVO.getEmpno());
					pstmt.executeUpdate();
				}  catch (SQLException se) {
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
			public void addNorAuth(AuthorityVO authVO) {
				
				Connection con = null ;
				PreparedStatement pstmt = null ;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(UPGRADENOR_STMT);
					
					pstmt.setString(1, authVO.getEmpno());
					pstmt.setString(2, authVO.getFunno());
					pstmt.executeUpdate();
				}  catch (SQLException se) {
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
