package com.lesson_order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.deposit.model.DepositService;

public class Lesson_orderJDBCDAO implements Lesson_orderDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "fitmate";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Lesson_order (LORD_NO, STUNO, LESSNO, LORD_SC, LORD_TIME) VALUES ( to_char(sysdate,'yyyymmdd')||'-LO'||LPAD(to_char(SEQ_LESSON_ORDER.nextval), 3, '0'), ?, ?, ? ,?)";

	private static final String GET_LESSNO_STMT = "SELECT * FROM LESSON WHERE LESSNO = ?";

	private static final String UPDATE_LESSON_LESSCUR = "UPDATE LESSON SET LESSCUR = ?, LESSSTA=? WHERE LESSNO = ?";

	private static final String DELETE = "DELETE FROM Lesson_order WHERE lord_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Lesson_order";
	private static final String GET_Lessno_STMT = "SELECT * FROM Lesson_order WHERE LESSNO = ? ";
	private static final String GET_Stuno_STMT = "SELECT * FROM Lesson_order WHERE STUNO = ?";

	private static final String GET_one_STMT = "SELECT * FROM LESSON_ORDER WHERE LORD_NO = ?";

	private static final String UP_LORD_SC = "UPDATE LESSON_ORDER SET LORD_SC = ? WHERE LORD_NO = ?";

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
	public void insert(Lesson_orderVO lesson_orderVO, int stupoint) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			con.setAutoCommit(false);

			String cols[] = { "lord_no" };

			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, lesson_orderVO.getStuno());
			pstmt.setString(2, lesson_orderVO.getLessno());
			pstmt.setInt(3, lesson_orderVO.getLord_sc());

			pstmt.setTimestamp(4, lesson_orderVO.getLord_time());

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();

			String next_lord_no = null;
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_lord_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_lord_no);
			} else {
				System.out.println("未取得自增主鍵值");
			}

			lesson_orderVO.setLord_no(next_lord_no);

			pstmt = con.prepareStatement(GET_LESSNO_STMT);
			pstmt.setString(1, lesson_orderVO.getLessno());

			rs = pstmt.executeQuery();

			String sta = null;
			int lessur_cont = 0;
			String lessno = null;

			while (rs.next()) {

				if (rs.getInt("lesscur") + 1 >= rs.getInt("lessmin"))
					sta = "已成團";
				else
					sta = "未成團";

				lessur_cont = rs.getInt("lesscur");

				lessur_cont++;

				lessno = rs.getString("lessno");

				System.out.println(lessur_cont + lessno + sta);
				System.out.println("sta=" + sta);
				System.out.println("lessmin=" + rs.getInt("lessmin"));
				System.out.println("lesscur=" + lessur_cont);
			}

			pstmt = con.prepareStatement(UPDATE_LESSON_LESSCUR);

			pstmt.setInt(1, lessur_cont);
			pstmt.setString(2, sta);
			pstmt.setString(3, lessno);

			pstmt.executeUpdate();

			DepositService DepositSvc = new DepositService();

			DepositSvc.alterStuPoint(lesson_orderVO.getStuno(), stupoint);

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {

			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back");
					System.err.println("你已經購買過此商品了");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
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

	@Override
	public void delete(String lord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, lord_no);
			pstmt.executeUpdate();

			// Handle any driver errors

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

	public List<Lesson_orderVO> getfindByLessno(String lessno) {

		List<Lesson_orderVO> list = new ArrayList<Lesson_orderVO>();
		Lesson_orderVO lesson_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_Lessno_STMT);
			pstmt.setString(1, lessno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lesson_orderVO = new Lesson_orderVO();
				lesson_orderVO.setStuno(rs.getString("stuno"));
				lesson_orderVO.setLessno(rs.getString("lessno"));
				lesson_orderVO.setLord_sc(rs.getInt("lord_sc"));
				list.add(lesson_orderVO);
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

		return list;

	}

	@Override
	public List<Lesson_orderVO> getfindByStuno(String stuno) {
		List<Lesson_orderVO> list = new ArrayList<Lesson_orderVO>();
		Lesson_orderVO lesson_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_Stuno_STMT);
			pstmt.setString(1, stuno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lesson_orderVO = new Lesson_orderVO();
				lesson_orderVO.setLord_no(rs.getString("lord_no"));
				lesson_orderVO.setStuno(rs.getString("stuno"));
				lesson_orderVO.setLessno(rs.getString("lessno"));
				lesson_orderVO.setLord_time(rs.getTimestamp("lord_time"));
				lesson_orderVO.setLord_sc(rs.getInt("lord_sc"));

				list.add(lesson_orderVO);
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

		return list;

	}

	@Override
	public List<Lesson_orderVO> getAll() {

		List<Lesson_orderVO> list = new ArrayList<Lesson_orderVO>();
		Lesson_orderVO lesson_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lesson_orderVO = new Lesson_orderVO();
				lesson_orderVO.setLord_no(rs.getString("Lord_no"));
				lesson_orderVO.setLessno(rs.getString("stuno"));
				lesson_orderVO.setStuno(rs.getString("lessno"));
				lesson_orderVO.setLord_sc(rs.getInt("Lord_sc"));
				list.add(lesson_orderVO);
			}
			// Handle any driver errors

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
	public Lesson_orderVO getfindByPrimaryKey(String Lord_no) {
		Lesson_orderVO lesson_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_one_STMT);

			pstmt.setString(1, Lord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lesson_orderVO = new Lesson_orderVO();
				lesson_orderVO.setLord_no(rs.getString("lord_no"));
				lesson_orderVO.setLessno(rs.getString("lessno"));
				lesson_orderVO.setStuno(rs.getString("stuno"));
				lesson_orderVO.setLord_time(rs.getTimestamp("lord_time"));
				lesson_orderVO.setLord_sc(rs.getInt("lord_sc"));

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
		return lesson_orderVO;
	}

	@Override
	public Lesson_orderVO up_lesson_order_lord_sc(String lord_no, Integer lord_sc) {
		Lesson_orderVO lesson_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UP_LORD_SC);

			pstmt.setInt(1, lord_sc);
			pstmt.setString(2, lord_no);

			pstmt.executeUpdate();

//
//			while (rs.next()) {
//				lesson_orderVO = new Lesson_orderVO();
//				lesson_orderVO.setLord_no(rs.getString("lord_no"));
//				lesson_orderVO.setLessno(rs.getString("lessno"));
//				lesson_orderVO.setStuno(rs.getString("stuno"));
//				lesson_orderVO.setLord_time(rs.getTimestamp("lord_time"));
//				lesson_orderVO.setLord_sc(rs.getInt("lord_sc"));
//	
//			}

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
		return lesson_orderVO;

	}

	public static void main(String[] args) {
		Lesson_orderJDBCDAO dao = new Lesson_orderJDBCDAO();
//
////		// 新增OK
//		Lesson_orderVO Lesson_orderVO1 = new Lesson_orderVO();
//
////		Lesson_orderVO1.setStuno("S007");
////		Lesson_orderVO1.setLessno("L003");
////		Lesson_orderVO1.setLord_sc(1);//new Integer(0)
////		Lesson_orderVO1.setLord_time( new Timestamp(System.currentTimeMillis()));
////		dao.insert(Lesson_orderVO1);
////		
//		
		// 刪除OK
//		dao.delete("21");

		// 查詢一個課程有多少學生OK
//		List<Lesson_orderVO> list = dao.getfindByLessno("L003");
//		for (Lesson_orderVO aLesson_orderVO1 : list) {
//		System.out.print(aLesson_orderVO1.getStuno() + ",");

		// System.out.println("-------");
//		}
//		System.out.println("---------------------");
//		// 查詢一個學生有多少課程OK
//		List<Lesson_orderVO> list1 = dao.getfindByStuno("S001");
//		for (Lesson_orderVO aLesson_orderVO2 : list1) {
//		System.out.print(aLesson_orderVO2.getLessno() + ",");
//
//			System.out.println();
//		}
//		
//		

//
		// 查詢
//		List<Lesson_orderVO> list = dao.getAll();
//		for (Lesson_orderVO aLesson_orderVO : list) {
//			System.out.print(aLesson_orderVO.getLord_no() + ",");
//			System.out.print(aLesson_orderVO.getStuno() + ",");
//			System.out.print(aLesson_orderVO.getLessno() + ",");
//			System.out.print(aLesson_orderVO.getLord_sc() + ",");
//			System.out.print(aLesson_orderVO.getLord_time() + ",");
//			System.out.println();
//		}

//		String str = "5";
//		Integer a = Integer.valueOf(str);
//		Lesson_orderVO	lesson_orderVO = dao.up_lesson_order_lord_sc("20200619-LO010",a);

	}

}
