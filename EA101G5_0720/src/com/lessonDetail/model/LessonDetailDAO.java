package com.lessonDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lesson.model.LessonVO;
import com.lessonTime.model.LessonTimeVO;

public class LessonDetailDAO implements LessonDetailDAO_interface {
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

	private static final String INSERT = "INSERT INTO LESSON_DETAIL VALUES (?,?)";
	private static final String GETONE_LessonAllTimes = "SELECT * FROM LESSON_DETAIL JOIN LESSON_TIME ON LESSON_DETAIL.LTIME_NO=LESSON_TIME.LTIME_NO	WHERE LESSNO=?";
	private static final String GETALL_LessonAllTimes = "SELECT * FROM LESSON_DETAIL";

	@Override
	public void insert(LessonVO LessonVO, List<LessonTimeVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println(list.size());
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			for (LessonTimeVO LessonTimeVO : list) {
				pstmt.setString(1, LessonVO.getLessno());
				System.out.println(LessonVO.getLessno());
				pstmt.setString(2, LessonTimeVO.getLtime_no());
				System.out.println(LessonTimeVO.getLtime_no());
				pstmt.executeUpdate();
			}

			
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
	public List<LessonDetailVO> findAllTimesBylessno(String lessno) {
		LessonDetailVO LessonDetailVO = null;
		List<LessonDetailVO> list = new ArrayList<LessonDetailVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETONE_LessonAllTimes);

			pstmt.setString(1, lessno);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				LessonDetailVO = new LessonDetailVO();
				LessonDetailVO.setLessno(rs.getString("lessno"));
				LessonDetailVO.setLtime_no(rs.getString("ltime_no"));
				LessonDetailVO.setLtime_date(rs.getDate("ltime_date"));
				LessonDetailVO.setLtime_ss(rs.getInt("ltime_ss"));
				list.add(LessonDetailVO);
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
	public List<LessonDetailVO> getAll() {
		List<LessonDetailVO> list = new ArrayList<LessonDetailVO>();
		LessonDetailVO LessonDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL_LessonAllTimes);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LessonDetailVO = new LessonDetailVO();
				LessonDetailVO.setLessno(rs.getString("lessno"));
				LessonDetailVO.setLtime_no(rs.getString("ltime_no"));
				list.add(LessonDetailVO); // Store the row in the list
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
