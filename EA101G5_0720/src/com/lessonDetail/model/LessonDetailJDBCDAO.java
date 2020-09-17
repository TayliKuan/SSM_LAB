package com.lessonDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lesson.model.LessonVO;
import com.lessonTime.model.LessonTimeVO;

public class LessonDetailJDBCDAO implements LessonDetailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G5";
	String passwd = "123456";

	private static final String INSERT = "INSERT INTO LESSON_DETAIL VALUES (?,?)";
	private static final String GETONE_LessonAllTimes = "SELECT * FROM LESSON_DETAIL JOIN LESSON_TIME ON LESSON_DETAIL.LTIME_NO=LESSON_TIME.LTIME_NO	WHERE LESSNO=?";
	private static final String GETALL_LessonAllTimes = "SELECT * FROM LESSON_DETAIL";

	@Override
	public void insert(LessonVO LessonVO, List<LessonTimeVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println(list.size());
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			for (LessonTimeVO LessonTimeVO : list) {
				pstmt.setString(1, LessonVO.getLessno());
				System.out.println(LessonVO.getLessno());
				pstmt.setString(2, LessonTimeVO.getLtime_no());
				System.out.println(LessonTimeVO.getLtime_no());
				pstmt.executeUpdate();
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETALL_LessonAllTimes);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LessonDetailVO = new LessonDetailVO();
				LessonDetailVO.setLessno(rs.getString("lessno"));
				LessonDetailVO.setLtime_no(rs.getString("ltime_no"));
				list.add(LessonDetailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {
		LessonDetailJDBCDAO dao = new LessonDetailJDBCDAO();

		// 先新增一筆 時間 才可以放入
		LessonVO LessonVO = new LessonVO();
		LessonVO.setLessno("L010");
		
		List<LessonTimeVO> list = new ArrayList<LessonTimeVO>();
		LessonTimeVO LessonTimeVO = new LessonTimeVO();
		LessonTimeVO.setLtime_no("LT036");
		LessonTimeVO LessonTimeVO1 = new LessonTimeVO();
		LessonTimeVO1.setLtime_no("LT037");
		LessonTimeVO LessonTimeVO2 = new LessonTimeVO();
		LessonTimeVO2.setLtime_no("LT038");
		
		list.add(LessonTimeVO);
		list.add(LessonTimeVO1);
		list.add(LessonTimeVO2);
		
		
		dao.insert(LessonVO, list);
		System.out.println("新增成功");

//		List<LessonDetailVO> list = dao.getAll();
//		for (LessonDetailVO aDT : list) {
//			System.out.print(aDT.getLessno() + ",");
//			System.out.print(aDT.getLtime_no() + ",");
//			System.out.println();
//		}
//		List<LessonDetailVO> testFind = dao.findAllTimesBylessno("L001");
//		for (LessonDetailVO aDT : testFind) {
//			System.out.println(aDT.getLessno() + ",");
//			System.out.println(aDT.getLtime_no() + ",");
//			System.out.println(aDT.getLtime_date() + ",");
//			System.out.println(aDT.getLtime_ss() + ",");
//		}
	}

}
