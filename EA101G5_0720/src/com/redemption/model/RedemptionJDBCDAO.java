package com.redemption.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RedemptionJDBCDAO implements RedemptionDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G5";
	String passwd = "123456";
	
	//點數兌換
	private static final String INSERT_STMT = "INSERT INTO REDEMPTION VALUES (to_char(sysdate,'yyyymmdd')||'-RED'||LPAD(to_char(DEPOSIT_SEQ.nextval), 3, '0'),?,CURRENT_TIMESTAMP,?,'未處理')";
	//查單一教練所有兌換紀錄
	private static final String SELECT_ALL_BYCOANO = "SELECT * FROM REDEMPTION WHERE COANO = ?";
	//查教練所有兌換紀錄(for後台)
	private static final String SELECT_ALL = "SELECT * FROM REDEMPTION ";
	//更改兌換狀態
	private static final String UPDATE_STA = "UPDATE REDEMPTION SET REDSTA = '已審核' WHERE REDNO = ?";
	//更改教練點數
	private static final String UPDATE_COAPOINT = "UPDATE COACH SET COAPOINT = ? WHERE COANO = ?";
	//審核後寄信
	private static final String SENDMAIL = "SELECT COANO , REDDATE FROM REDEMPTION WHERE REDNO = ?";
	
	@Override
	public List<RedemptionVO> selectAllRed(String coano) {
		
		RedemptionVO redVO = null ;
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<RedemptionVO> redlist = new ArrayList<RedemptionVO>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(SELECT_ALL_BYCOANO);
			pstmt.setString(1, coano);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				redVO = new RedemptionVO();
				redVO.setRedno(rs.getString("redno"));
				redVO.setCoano(rs.getString("coano"));
				redVO.setReddate(rs.getTimestamp("reddate"));
				redVO.setRedprice(rs.getInt("redprice"));
				redVO.setRedsta(rs.getString("redsta"));
				redlist.add(redVO);
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
		return redlist;
	}

	@Override
	public void insertRed(RedemptionVO redVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, redVO.getCoano());
			pstmt.setInt(2, redVO.getRedprice());
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
	public List<RedemptionVO> selectAll() {
		
		RedemptionVO redVO = null ;
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<RedemptionVO> redlist = new ArrayList<RedemptionVO>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				redVO = new RedemptionVO();
				redVO.setRedno(rs.getString("redno"));
				redVO.setCoano(rs.getString("coano"));
				redVO.setReddate(rs.getTimestamp("reddate"));
				redVO.setRedprice(rs.getInt("redprice"));
				redVO.setRedsta(rs.getString("redsta"));
				redlist.add(redVO);
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
		return redlist;
	}
	
	@Override
	public void updateSta(String redno) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_STA);
			pstmt.setString(1, redno);
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
	public void updatePoint(String coano, Integer coapoint) {
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_COAPOINT);
			pstmt.setString(1, coano);
			pstmt.setInt(2, coapoint);
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
	public RedemptionVO getCoaByRedno(String redno) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		RedemptionVO redVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(SENDMAIL);
			pstmt.setString(1, redno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				redVO = new RedemptionVO();
				redVO.setCoano(rs.getString("coano"));
				redVO.setReddate(rs.getTimestamp("reddate"));
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
		
		return redVO ;
		
	}
	//JDBC TEST OK
		public static void main(String[] args) {
			RedemptionJDBCDAO dao = new RedemptionJDBCDAO();
			RedemptionVO redVO = new RedemptionVO();
//			redVO.setCoano("C002");
//			redVO.setRedprice(2000);
//			dao.insertRed(redVO);
//			System.out.println("新增成功");
//			
//			dao.updateSta("20200629-RED014");
//			System.out.println("修改成功");
//			
//			List<RedemptionVO>redlist = dao.selectAll();
//			for(RedemptionVO red : redlist) {
//				System.out.println(red.getRedno());
//				System.out.println(red.getCoano());
//				System.out.println(red.getReddate());
//				System.out.println(red.getRedprice());
//				System.out.println(red.getRedsta());
//			}
//			dao.updateSta("20200701-RED012");
			
			redVO = dao.getCoaByRedno("20200701-RED003");
			System.out.println(redVO.getCoano());
			System.out.println(redVO.getReddate());
			System.out.println("success");
		}

		@Override
		public void updateLessonSta(String lessno) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateActSta(String actno) {
			// TODO Auto-generated method stub
			
		}
	

}

