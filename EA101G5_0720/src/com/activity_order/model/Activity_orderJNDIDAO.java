package com.activity_order.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import com.student.model.StuVO;
public class Activity_orderJNDIDAO implements Acitivity_orderDAO_interface {

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

	private static final String INSERT_ORDER_PSTMT = "INSERT INTO　ACTIVITY_ORDER (aord_no,actno,stuno,aord_sc,aord_time)"
			+ "VALUES (to_char(sysdate,'yyyymmdd')||'-AO'||LPAD(to_char(ACTIVITY_ORDER_seq.NEXTVAL), 3, '0'), ?, ?, ?,?)";
	private static final String GET_ALL_PSTMT = "SELECT * FROM ACTIVITY_ORDER order by aord_no desc";
	private static final String GET_ONE_PSTMT = "SELECT * FROM ACTIVITY_ORDER where aord_no = ? order by aord_no desc";
	private static final String DELETE = "DELETE　FROM ACTIVITY_ORDER where aord_no = ?";
	private static final String UPDATE = "UPDATE ACTIVITY_ORDER set actno=?, stuno=?,aord_sc=? where aord_no = ?";
	/*Table activity_order*/
	private static final String GET_STUDENT_ORDER_PSTMT = "SELECT * FROM ACTIVITY_ORDER WHERE ACTNO=?";
	private static final String GET_ACTIVITY_PSTMT = "SELECT * FROM ACTIVITY_ORDER WHERE STUNO=?";
	private static final String UPDATE_AORD_SC = "UPDATE ACTIVITY_ORDER SET AORD_SC = ? WHERE AORD_NO = ?";
	/*Table activity*/
	private static final String GET_STUDENT_PSTMT = "SELECT * FROM ACTIVITY WHERE ACTNO=?";
	private static final String UPADTE_ACTIVITY_ACTCUR = "UPDATE ACTIVITY SET ACTCUR = ? , ACTSTA=? WHERE ACTNO = ?";
//	private static final String UPDATE_ACTPRICE_FOR_SALE = "UPDATE ACTIVITY SET ACTPRICE =? where ACTNO=?";
	/*Table student*/
	private static final String REDUCE_STUPOINT = " UPDATE STUDENT SET STUPOINT = ? WHERE STUNO = ?";
	// 新增(自增主鍵值綁定)
	@SuppressWarnings("resource")
	@Override
	public void insert(Activity_orderVO activity_orderVO,int stupoint) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			String cols[] = { "aord_no" };

			pstmt = con.prepareStatement(INSERT_ORDER_PSTMT, cols);
			pstmt.setString(1, activity_orderVO.getActno());
			pstmt.setString(2, activity_orderVO.getStuno());
			pstmt.setInt(3, 0);
			pstmt.setTimestamp(4, activity_orderVO.getAord_time());
			
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();

			String next_aord_no = null;
			if (rs.next()) {
				next_aord_no = rs.getString(1);
			
				System.out.println("自增主鍵值= " + next_aord_no);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			activity_orderVO.setAord_no(next_aord_no);
			
			System.out.println(activity_orderVO.getAord_no());
		

			pstmt = con.prepareStatement(GET_STUDENT_PSTMT);
			pstmt.setString(1, activity_orderVO.getActno());
			rs =  pstmt.executeQuery();
			
			String actsta = null;
			
			int actcur_count = 0;
			String actno = null;
			
			while (rs.next()) {
				if (rs.getInt("actcur") >= rs.getInt("actmin")) {
					System.out.println(rs.getInt("actcur"));
					System.out.println(rs.getInt("actmin"));
					actsta = "上架已成團";
				} else {
					actsta = "上架待成團";
				}
				actcur_count = rs.getInt("actcur");
				actcur_count++;
				actno = rs.getString("actno");
				System.out.println(actcur_count);

			}
			
			pstmt = con.prepareStatement(UPADTE_ACTIVITY_ACTCUR);
			pstmt.setInt(1, actcur_count);
			pstmt.setString(2, actsta);
			pstmt.setString(3, actno);
			
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(REDUCE_STUPOINT);
			pstmt.setInt(1, stupoint);
			System.out.println("in dao stupoint="+stupoint);
			pstmt.setString(2, activity_orderVO.getStuno());
			System.out.println("in dao stuNO="+activity_orderVO.getStuno());
			
			pstmt.executeUpdate();
			
//			pstmt = con.prepareStatement(GET_STUDENT_PSTMT);
//			pstmt.setString(1, activity_orderVO.getActno());
//			rs =  pstmt.executeQuery();
//			
//			int actprice = 0;
//			
//			while (rs.next()) {
//				if (rs.getInt("actcur") >= rs.getInt("actmin")) {
//					actprice= (rs.getInt("actprice")/rs.getInt("actcur")*rs.getInt("actmin"));
//				} else {
//					actprice = (rs.getInt("actprice"));
//				}
//				
//			}
//			pstmt = con.prepareStatement(UPDATE_ACTPRICE_FOR_SALE);
//			pstmt.setInt(1, actprice);
//			pstmt.setString(2, actno);
//			
//			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being");
					System.err.println("rollback");
					con.rollback();
				} catch (SQLException sexp) {
					throw new RuntimeException("rollback error occured. " + sexp.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

	// 修改
	@Override
	public void update(Activity_orderVO activity_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, activity_orderVO.getActno());
			pstmt.setString(2, activity_orderVO.getStuno());
			pstmt.setDouble(3, activity_orderVO.getAord_sc());
			pstmt.setString(4, activity_orderVO.getAord_no());

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

	// 刪除-->暫不使用
	@Override
	public void delete(String aord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, aord_no);
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

	// 查詢單筆由訂單編號查詢
	@Override
	public Activity_orderVO findByPrimaryKey(String aord_no) {

		Activity_orderVO activity_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PSTMT);

			pstmt.setString(1, aord_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activity_orderVO = new Activity_orderVO();
				activity_orderVO.setAord_no(rs.getString("aord_no"));
				activity_orderVO.setActno(rs.getString("actno"));
				activity_orderVO.setStuno(rs.getString("stuno"));
				activity_orderVO.setAord_sc(rs.getInt("aord_sc"));
				activity_orderVO.setAord_time(rs.getTimestamp("aord_time"));
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

		return activity_orderVO;
	}

	// 查詢多筆
	@Override
	public List<Activity_orderVO> getAll() {
		List<Activity_orderVO> list = new ArrayList<Activity_orderVO>();

		Activity_orderVO activity_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activity_orderVO = new Activity_orderVO();
				activity_orderVO.setAord_no(rs.getString("aord_no"));
				activity_orderVO.setActno(rs.getString("actno"));
				activity_orderVO.setStuno(rs.getString("stuno"));
				activity_orderVO.setAord_sc(rs.getInt("aord_sc"));
				activity_orderVO.setAord_time(rs.getTimestamp("aord_time"));
				list.add(activity_orderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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


	/* 單一查詢由活動編號查有多少學員報名 */
	@Override
	public List<Activity_orderVO> findStudentByactno(String actno) {
		List<Activity_orderVO> list = new ArrayList<Activity_orderVO>();
		
		Activity_orderVO activity_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STUDENT_ORDER_PSTMT);
			pstmt.setString(1, actno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activity_orderVO = new Activity_orderVO();
				activity_orderVO.setAord_no(rs.getString("aord_no"));
				activity_orderVO.setActno(rs.getString("actno"));
				activity_orderVO.setStuno(rs.getString("stuno"));
				activity_orderVO.setAord_sc(rs.getInt("aord_sc"));
				activity_orderVO.setAord_time(rs.getTimestamp("aord_time"));
				list.add(activity_orderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	/* 單一查詢由學員編號查報名多少活動 (學員訂單紀錄)*/
	@Override
	public List<Activity_orderVO> findActivityBystuno(String stuno) {
		List<Activity_orderVO> list = new ArrayList<Activity_orderVO>();
		Activity_orderVO activity_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACTIVITY_PSTMT);
			pstmt.setString(1, stuno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activity_orderVO = new Activity_orderVO();
				activity_orderVO.setAord_no(rs.getString("aord_no"));
				activity_orderVO.setActno(rs.getString("actno"));
				activity_orderVO.setStuno(rs.getString("stuno"));
				activity_orderVO.setAord_sc(rs.getInt("aord_sc"));
				activity_orderVO.setAord_time(rs.getTimestamp("aord_time"));
				list.add(activity_orderVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}
	@Override
	public Activity_orderVO update_activity_order_aord_sc(String aord_no, Integer aord_sc) {
		Activity_orderVO activity_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_AORD_SC);
			pstmt.setInt(1, aord_sc);
			pstmt.setString(2, aord_no);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return activity_orderVO;

	}

}
