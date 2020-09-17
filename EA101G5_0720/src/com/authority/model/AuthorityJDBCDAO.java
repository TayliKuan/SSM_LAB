package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorityJDBCDAO implements AuthorityDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G5";
	String passwd = "123456";
	
	private static final String SELECTSYS_STMT = "SELECT ESTA , FUNCNO FROM EMPLOYEE E JOIN AUTHORITY A ON E.EMPNO = A.EMPNO WHERE ESTA = '系統管理員' ";
	private static final String SELECTNOR_STMT = "SELECT ESTA , FUNCNO FROM EMPLOYEE E JOIN AUTHORITY A ON E.EMPNO = A.EMPNO WHERE ESTA = '一般管理員' ";
	private static final String UPGRADESYS_STMT = "INSERT INTO AUTHORITY VALUES (?,'F007')";
	private static final String UPGRADENOR_STMT = "INSERT INTO AUTHORITY VALUES (?,?)";

	@Override
	public List<AuthorityVO> selectAllFuncSys() {
		
		List<AuthorityVO>authlist = new ArrayList<AuthorityVO>();
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		AuthorityVO authVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(SELECTSYS_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				authVO = new AuthorityVO();
				authVO.setEmpno(rs.getString("empno"));
				authVO.setFunno(rs.getString("funcno"));
				authlist.add(authVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(SELECTNOR_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				authVO = new AuthorityVO();
				authVO.setEmpno(rs.getString("empno"));
				authVO.setFunno(rs.getString("funcno"));
				authlist.add(authVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return authlist ;
	}

	@Override
	public void changeToSys(AuthorityVO authVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPGRADESYS_STMT);
			
			pstmt.setString(1, authVO.getEmpno());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void addNorAuth(AuthorityVO authVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPGRADENOR_STMT);
			
			pstmt.setString(1, authVO.getEmpno());
			pstmt.setString(2, authVO.getFunno());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

	//JDBC TEST OK
	public static void main(String[] args) {
		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
		
		AuthorityVO authVO = new AuthorityVO();
//		authVO.setEmpno("E002");
//		dao.changeToSys(authVO);
//		
//		System.out.println("更改為系統管理員成功");
		
		//很臭的新增權限法(包裝成list輸入?)
		
		authVO.setEmpno("E003");
		authVO.setFunno("F001");
		dao.addNorAuth(authVO);	
		authVO.setEmpno("E003");
		authVO.setFunno("F002");
		dao.addNorAuth(authVO);
		authVO.setEmpno("E003");
		authVO.setFunno("F003");
		dao.addNorAuth(authVO);
		authVO.setEmpno("E003");
		authVO.setFunno("F004");
		dao.addNorAuth(authVO);
		authVO.setEmpno("E003");
		authVO.setFunno("F005");
		dao.addNorAuth(authVO);
		authVO.setEmpno("E003");
		authVO.setFunno("F006");
		dao.addNorAuth(authVO);
		
		System.out.println("新增權限成功");
	}

}
