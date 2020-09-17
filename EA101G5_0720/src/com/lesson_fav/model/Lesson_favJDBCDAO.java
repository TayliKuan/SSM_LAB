package com.lesson_fav.model;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import com.emp.model.EmpJDBCDAO;
//import com.emp.model.EmpVO;

import java.sql.*;
//import com.student.model;
//import com.choah.model;

public class Lesson_favJDBCDAO implements Lesson_favDAO_interface{


	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "fitmate";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO Lesson_fav  (STUNO, LESSNO) VALUES ( ?, ?)";
	private static final String DELETE = "DELETE FROM Lesson_fav WHERE STUNO = ? AND LESSNO = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Lesson_fav";
	private static final String GET_Lessno_STMT = "SELECT * FROM Lesson_fav WHERE LESSNO = ? ";
	private static final String GET_Stuno_STMT = "SELECT * FROM Lesson_fav WHERE STUNO = ?";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void insert(Lesson_favVO lesson_favVO) {
		
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, lesson_favVO.getStuno());
			pstmt.setString(2, lesson_favVO.getLessno());		
			pstmt.executeUpdate();

		}catch (SQLException e) {
//			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
//					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
//					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	@Override
	public void delete(String stuno ,String lessno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, stuno);
			pstmt.setString(2, lessno);
			System.out.println("123456789");
			pstmt.executeUpdate();



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
	public List<Lesson_favVO> getfindByLessno( String lessno) {
			
		List<Lesson_favVO> list = new ArrayList<Lesson_favVO>();
		Lesson_favVO lesson_favVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {	
			con = ds.getConnection();
			
		pstmt = con.prepareStatement(GET_Lessno_STMT);
		pstmt.setString(1, lessno);
		rs = pstmt.executeQuery();
	
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {					
				lesson_favVO = new Lesson_favVO();
				lesson_favVO.setStuno(rs.getString("stuno"));
				lesson_favVO.setLessno(rs.getString("lessno"));
				list.add(lesson_favVO);					
		}

		// Handle any SQL errors
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
		
		return list;
	
	}
	
	@Override
	public List<Lesson_favVO> getfindByStuno(String stuno) {
		List<Lesson_favVO> list = new ArrayList<Lesson_favVO>();
		Lesson_favVO lesson_favVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

		pstmt = con.prepareStatement(GET_Stuno_STMT);
		pstmt.setString(1, stuno);
	
		rs = pstmt.executeQuery();
		
		while (rs.next()) {					
				lesson_favVO = new Lesson_favVO();
				lesson_favVO.setStuno(rs.getString("stuno"));
				lesson_favVO.setLessno(rs.getString("lessno"));
				list.add(lesson_favVO);					
		}

		// Handle any SQL errors
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
		
		return list;
	
		
	}
	
	@Override
	public List<Lesson_favVO> getAll() {
		
		List<Lesson_favVO> list = new ArrayList<Lesson_favVO>();
		Lesson_favVO lesson_favVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	
		
		try {	
			con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		
		rs = pstmt.executeQuery();

		while (rs.next()) {	
			lesson_favVO = new Lesson_favVO();
		
			lesson_favVO.setStuno(rs.getString("stuno"));
			lesson_favVO.setLessno(rs.getString("lessno"));
			list.add(lesson_favVO);			
		}

		// Handle any SQL errors
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
		
		return list;
		
	}

	
//	public static void main(String[] args) {
//		Lesson_favJDBCDAO dao = new Lesson_favJDBCDAO();
//
////		// 新增OK
//		Lesson_favVO lesson_favVO1 = new Lesson_favVO();
//		lesson_favVO1.setStuno("S001");
//		lesson_favVO1.setLessno("L009");
//		dao.insert(lesson_favVO1);
////		
//		
////		// 刪除OK
////		dao.delete("S002","L002");
//	
//	
//		
////
//		// 查詢一個課程有多少學生OK
////		List<Lesson_favVO> list = dao.getfindByLessno("L003");
////		for (Lesson_favVO alesson_favVO1 : list) {
////		System.out.print(alesson_favVO1.getStuno() + ",");
////
////			System.out.println();
////		}
////		System.out.println("---------------------");
////		// 查詢一個學生有多少課程OK
////		List<Lesson_favVO> list1 = dao.getfindByStuno("S001");
////		for (Lesson_favVO alesson_favVO2 : list1) {
////		System.out.print(alesson_favVO2.getLessno() + ",");
////		System.out.print(alesson_favVO2.getStuno() + ",");
////
////			System.out.println();
////		}
////		
////		
//		
////
////		// 查詢
////		List<Lesson_favVO> list = dao.getAll();
////		for (Lesson_favVO alesson_favVO : list) {
////			System.out.print(alesson_favVO.getStuno() + ",");
////			System.out.print(alesson_favVO.getLessno() + ",");
////	
////	
////			System.out.println();
////		}
//	}



}
