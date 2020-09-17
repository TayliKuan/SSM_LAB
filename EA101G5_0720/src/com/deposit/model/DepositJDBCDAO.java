package com.deposit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositJDBCDAO implements DepositDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G5";
	String passwd = "123456";
	
	//點數儲值
	private static final String INSERT_STMT = "INSERT INTO DEPOSIT VALUES (to_char(sysdate,'yyyymmdd')||'-DEP'||LPAD(to_char(DEPOSIT_SEQ.nextval), 3, '0'),?,CURRENT_TIMESTAMP,?)";
	//查單一學員所有儲值紀錄
	private static final String SELECT_ALL_BYDEPNO = "SELECT * FROM DEPOSIT WHERE STUNO = ?";
	
	@Override
	public void insertDep(DepositVO depVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, depVO.getStuno());
			//pstmt.setTimestamp(2, depVO.getDepdate());
			pstmt.setInt(2, depVO.getDepprice());
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
	public List<DepositVO> selectAll(String stuno) {
		
		List<DepositVO> deplist = new ArrayList<DepositVO>();
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		DepositVO depVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
		return deplist;
	}

	//JDBC測試OK
	public static void main(String[] args) {
		
		DepositJDBCDAO dao = new DepositJDBCDAO();
//		DepositVO depVO = new DepositVO();
//		depVO.setStuno("S003");
//		depVO.setDepprice(2000);
//		dao.insertDep(depVO);
		
		List<DepositVO> list = dao.selectAll("S003");
		for(DepositVO dep : list) {
			System.out.println(dep.getDepno());
			System.out.println(dep.getStuno());
			System.out.println(dep.getDepdate());
			System.out.println(dep.getDepprice());
		}
		
	}

	@Override
	public void alterStuPoint(String stuno, Integer newpoint) {
		// TODO Auto-generated method stub
		
	}
	
}
