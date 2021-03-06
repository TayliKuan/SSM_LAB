package com.information.model;

import java.sql.*;
import java.util.*;

public class InformationJDBCDAO implements InformationDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G5";
	String passwd = "123456";
	
	//新增消息
	public static final String INSERT_STMT = "INSERT INTO INFORMATION VALUES ('IN'||LPAD(to_char(INNO_SEQ.NEXTVAL),3,'0'),?,?,?,?)";
	//列出所有消息(依日期排序)
	private static final String GET_ALL_STMT_BYDATE = "SELECT * FROM INFORMATION ORDER BY INDATE DESC";			
	//列出單一消息(基本不用)
	private static final String GET_ONE_STMT = "SELECT * FROM INFORMATION WHERE INNO = ?";
	//刪除消息
	private static final String DELETE = "DELETE FROM INFORMATION WHERE INNO = ? ";
	//更新消息(配合排程)
	private static final String UPDATE = "UPDATE INFORMATION SET INDATE = ? , INTYPE = ? ,INTITLE = ? , INDESC = ? WHERE INNO = ? ";
	
	@Override
	public void insertInfo(InformationVO InfVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
			
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setDate(1, InfVO.getIndate());
			pstmt.setString(2, InfVO.getIntype());
			pstmt.setString(3, InfVO.getIntitle());
			pstmt.setString(4, InfVO.getIndesc());
			
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
	public void modifyInfo(InformationVO InfVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setDate(1, InfVO.getIndate());
			pstmt.setString(2, InfVO.getIntype());
			pstmt.setString(3, InfVO.getIntitle());
			pstmt.setString(4, InfVO.getIndesc());
			pstmt.setString(5, InfVO.getInno());
			
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
	public void deleteInfo(String inno) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, inno);
			
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
	public InformationVO selectByInno(String inno) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		InformationVO infVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				infVO = new InformationVO();
				infVO.setInno(rs.getString("Inno"));
				infVO.setIndate(rs.getDate("Indate"));
				infVO.setIntitle(rs.getString("Intitle"));
				infVO.setIndesc(rs.getString("Indesc"));	
				infVO.setIntype(rs.getString("intype"));
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
		return null;
	}
	
	@Override
	public List<InformationVO> selectAllInfoByDate(){
		
		List<InformationVO>inflist = new ArrayList<InformationVO>();
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		InformationVO infVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_BYDATE);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				infVO = new InformationVO();
				infVO.setInno(rs.getString("Inno"));
				infVO.setIndate(rs.getDate("Indate"));
				infVO.setIntitle(rs.getString("Intitle"));
				infVO.setIndesc(rs.getString("Indesc"));
				infVO.setIntype(rs.getString("intype"));
				inflist.add(infVO);
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
		return inflist;
	}

	//JDBC TEST OK
	public static void main(String[] args) {
		
		InformationJDBCDAO dao = new InformationJDBCDAO();
		
		List<InformationVO>inflist = dao.selectAllInfoByDate();
		for(InformationVO inf : inflist) {
			System.out.print(inf.getInno()+",");
			System.out.print(inf.getIndate()+",");
			System.out.print(inf.getIntype()+",");
			System.out.print(inf.getIntitle()+",");
			System.out.print(inf.getIndesc());
			System.out.println();
		}
	}
}
