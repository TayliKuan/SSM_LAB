package com.lessonTime.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lesson.model.LessonService;
import com.lesson.model.LessonVO;
import com.lessonDetail.model.LessonDetailJDBCDAO;
import com.lessonDetail.model.LessonDetailVO;

public class LessonTimeDAO implements LessonTimeDAO_inrterface {
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

	private static final String INSERT = "INSERT INTO LESSON_TIME VALUES ('LT'||LPAD(to_char(LTIME_NO_seq.NEXTVAL), 3, '0'),?,?)";
	private static final String UPDATE = "UPDATE LESSON_TIME set LTIME_DATE =?, LTIME_SS=? where LTIME_NO =? ";
	private static final String DELETE_LessonDetail = "DELETE FROM LESSON_DETAIL where LTIME_NO =?";
	private static final String DELETE_LessonTime = "DELETE FROM LESSON_TIME where LTIME_NO =?";
	private static final String GET_ONE = "SELECT lesson_detail.ltime_no,ltime_date,ltime_ss FROM lesson_detail JOIN lesson_time ON lesson_time.ltime_no=lesson_detail.ltime_no WHERE lessno=?";
	private static final String GET_ALL = "SELECT * FROM LESSON_TIME";
	private static final String GET_CoachAlltimes = "SELECT LTIME_DATE,LTIME_SS FROM LESSON JOIN LESSON_DETAIL ON LESSON_DETAIL.LESSNO=LESSON.LESSNO JOIN LESSON_TIME ON LESSON_TIME.LTIME_NO=LESSON_DETAIL.LTIME_NO WHERE COANO=?";
	private static final String INSERT_DTEAIL = "INSERT INTO LESSON_DETAIL VALUES (?,?)";
	private static final String GET_ONE_TIME = "SELECT LTIME_DATE,LTIME_SS  FROM LESSON_TIME JOIN LESSON_DETAIL ON LESSON_DETAIL.LTIME_NO=LESSON_TIME.LTIME_NO JOIN LESSON ON LESSON_DETAIL.LESSNO=LESSON.LESSNO WHERE LESSON.LESSNO= ?";
	private static final String GET_TIMENO = "SELECT lesson_detail.ltime_no FROM lesson_detail JOIN lesson_time ON lesson_time.ltime_no=lesson_detail.ltime_no WHERE lessno=?";
	//	private static final String GET_TIMES = "SELECT LESSTIMES FROM LESSON WHERE LESSNO=?";
//	private static final String UPDATE_TIMES = "UPDATE LESSON SET LESSTIMES=?WHERE LESSNO=?";
	@Override
	public void insert(LessonTimeVO LessonTimeVO, String lessno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String next_time = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			// 1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);// 開始交易

			String cols[] = { "ltime_no" };

			pstmt = null;
			pstmt = con.prepareStatement(INSERT, cols);
			pstmt.setDate(1, LessonTimeVO.getLtime_date());
			pstmt.setString(2, LessonTimeVO.getLtime_ss());
			pstmt.executeUpdate();

			// 取得對應的自增主鍵值
			rs = pstmt.getGeneratedKeys();// 拿出pstmt = con.prepareStatement(INSERT_ORDER, cols);剛剛新增的訂單編號
			if (rs.next()) {
				next_time = rs.getString(1);
				System.out.println("自增主鍵值 = " + next_time + "(剛新增成功的訂單編號)");

			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			pstmt = null;
			pstmt = con.prepareStatement(INSERT_DTEAIL);
			pstmt.setString(1, lessno);
			pstmt.setString(2, next_time);
			pstmt.executeUpdate();
			pstmt.clearParameters();

			// 2.設定於pstmt.executeUpdate()之後
			// 這連線還要回來這裡操作 進行交易 下面的方法還不能關
			con.commit();// 所以在交易之前 有任何問題 都是rollback
			con.setAutoCommit(true);

			
			// Handle any SQL errors
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
	}

	@Override
	public void update(LessonTimeVO LessonTimeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, LessonTimeVO.getLtime_date());
			pstmt.setString(2, LessonTimeVO.getLtime_ss());
			pstmt.setString(3, LessonTimeVO.getLtime_no());

			pstmt.executeUpdate();

			
			// Handle any SQL errors
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
	}

	@Override
	public void delete(String lessno) {
		

		Connection con = null;
		PreparedStatement pstmt = null;
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		try {
			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			//先拿到時段的編號
			pstmt = con.prepareStatement(GET_TIMENO);
			pstmt.setString(1, lessno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("ltime_no"));
			}
			
			// 先刪除明細 看有多少堂 就跑回圈都刪掉
			for(int i = 0 ; i < list.size(); i++) {
			pstmt = con.prepareStatement(DELETE_LessonDetail);
			String no = list.get(i);
			System.out.println("no="+no);
			
			pstmt.setString(1, no);
			pstmt.executeUpdate();
			
			// 再刪除時段
			pstmt = con.prepareStatement(DELETE_LessonTime);
			pstmt.setString(1, no);
			pstmt.executeUpdate();
			}
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<LessonTimeVO> findByPrimaryKey(String lessno) {
		List<LessonTimeVO> list = new ArrayList<LessonTimeVO>();
		LessonTimeVO LessonTimeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, lessno);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				LessonTimeVO = new LessonTimeVO();
				LessonTimeVO.setLtime_no(rs.getString("ltime_no"));
				LessonTimeVO.setLtime_date(rs.getDate("ltime_date"));
				LessonTimeVO.setLtime_ss(rs.getString("ltime_ss"));
				list.add(LessonTimeVO);
			}

			
			// Handle any SQL errors
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
		return list;
	}

	@Override
	public List<LessonTimeVO> getAll() {
		List<LessonTimeVO> list = new ArrayList<LessonTimeVO>();
		LessonTimeVO LessonTimeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LessonTimeVO = new LessonTimeVO();
				LessonTimeVO.setLtime_no(rs.getString("ltime_no"));
				LessonTimeVO.setLtime_date(rs.getDate("ltime_date"));
				LessonTimeVO.setLtime_ss(rs.getString("ltime_ss"));
				list.add(LessonTimeVO); // Store the row in the list
			}

			
			// Handle any SQL errors
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

	@Override
	public JSONArray getCoachAllLesson(String coano) {
		JSONArray allLessonTimeArray = new JSONArray();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CoachAlltimes);

			pstmt.setString(1, coano);
			rs = pstmt.executeQuery();

			ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			// 教練一堂課程=一個JSONObject 再把全部塞進JSONArray
			while (rs.next()) {
				JSONObject oneTime = new JSONObject();

				for (int i = 1; i <= columnCount; i++) {
					try {
						oneTime.put("ltime_date", rs.getDate("ltime_date"));
						oneTime.put("ltime_ss", rs.getString("ltime_ss"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				allLessonTimeArray.put(oneTime);
			}
			
			// Handle any SQL errors
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

		return allLessonTimeArray;
	}

	@Override
	public List<String> getOneTime(String lessno) {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_TIME);
			pstmt.setString(1, lessno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Date ltime_date = rs.getDate("ltime_date");
				String date = ltime_date.toString();
				String ltime_ss = rs.getString("ltime_ss");
				String all = date+ltime_ss;
				list.add(all);
			}

			
			// Handle any SQL errors
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

}
