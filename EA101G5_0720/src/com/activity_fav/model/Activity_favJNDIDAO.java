package com.activity_fav.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Activity_favJNDIDAO implements Activity_favDAO_interface {

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

	private static final String INSERT_PSTMT = "INSERT INTO　ACTIVITY_FAV (actno,stuno)" + "VALUES (?,?)";
	private static final String GET_ALL_PSTMT = "SELECT * FROM ACTIVITY_FAV order by actno desc";
	private static final String GET_STUDENT_PSTMT = "SELECT stuno FROM ACTIVITY_FAV WHERE actno=?";
	private static final String GET_ACTIVITY_PSTMT = "SELECT actno FROM ACTIVITY_FAV WHERE stuno=?";
	private static final String DELETE = "DELETE FROM ACTIVITY_FAV  WHERE actno=? and stuno=?";
	private static final String UPDATE = "UPDATE ACTIVITY_FAV set actno=? where stuno = ?";

	// 新增
	@Override
	public void insert(Activity_favVO activity_favVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PSTMT);
			
			pstmt.setString(1, activity_favVO.getActno());
			pstmt.setString(2, activity_favVO.getStuno());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
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
	// 修改
	@Override
	public void update(Activity_favVO activity_favVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, activity_favVO.getActno());
			pstmt.setString(2, activity_favVO.getStuno());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			
			se.printStackTrace();
		}
		
	}
	
	// 刪除
	@Override
	public void delete(String actno, String stuno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, actno);
			pstmt.setString(2, stuno);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
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
	
	// 活動單一查詢找學員
	@Override
	public List<Activity_favVO> findStudentByPrimaryKey(String actno) {
		List<Activity_favVO> list = new ArrayList<Activity_favVO>();
		
		Activity_favVO activity_favVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STUDENT_PSTMT);
			pstmt.setString(1, actno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				activity_favVO = new Activity_favVO();
				activity_favVO.setStuno(rs.getString("stuno"));
				list.add(activity_favVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
	
	//學員單一查詢找活動追蹤
	@Override
	public List<Activity_favVO> findActivityByPrimaryKey(String stuno) {
		List<Activity_favVO> list = new ArrayList<Activity_favVO>();
		
		Activity_favVO activity_favVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACTIVITY_PSTMT);
			pstmt.setString(1, stuno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				activity_favVO = new Activity_favVO();
				activity_favVO.setActno(rs.getString("actno"));
				list.add(activity_favVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
	
	// 全部查詢
	@Override
	public List<Activity_favVO> getAll() {
		List<Activity_favVO> list = new ArrayList<Activity_favVO>();

		Activity_favVO activity_favVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activity_favVO = new Activity_favVO();
				activity_favVO.setActno(rs.getString("actno"));
				activity_favVO.setStuno(rs.getString("stuno"));
				list.add(activity_favVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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