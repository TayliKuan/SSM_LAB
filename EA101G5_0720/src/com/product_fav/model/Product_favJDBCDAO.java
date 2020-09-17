package com.product_fav.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Product_favJDBCDAO implements Product_favDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "EA101G5";
	String passwd = "EA101G5";

	private static final String INSERT_STMT = "INSERT INTO product_fav(stuno,prodno)VALUES(?,?)";
	private static final String DELETE = "DELETE FROM PRODUCT_FAV where stuno=? and prodno=?";
	private static final String GET_ALL_STMT = "SELECT stuno,prodno from product_fav order by stuno";
	private static final String GET_STUNO_STMT = "SELECT *  from product_fav where STUNO=?";

	@Override
	public void insert(Product_favVO product_favVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_favVO.getStuno());
			pstmt.setString(2, product_favVO.getProdno());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
	public void delete(String stuno, String prodno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, stuno);
			pstmt.setString(2, prodno);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
	public List<Product_favVO> getAll() {
		List<Product_favVO> list = new ArrayList<Product_favVO>();
		Product_favVO pfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pfVO = new Product_favVO();
				pfVO.setStuno(rs.getString("stuno"));
				pfVO.setProdno(rs.getString("prodno"));
				list.add(pfVO); // Store the row in the list
			}
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
	public List<Product_favVO> findByStuno(String stuno) {
		List<Product_favVO> list = new ArrayList<Product_favVO>();
		Product_favVO prfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STUNO_STMT);
			pstmt.setString(1, stuno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prfVO = new Product_favVO();
				prfVO.setStuno(rs.getString("stuno"));
				prfVO.setProdno(rs.getString("prodno"));
				list.add(prfVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		Product_favJDBCDAO dao = new Product_favJDBCDAO();

//		Product_favVO pfVO1=new Product_favVO();
//		pfVO1.setStuno("S001");
//		pfVO1.setProdno("P032");
//		dao.insert(pfVO1);

//		dao.delete("S001", "P032");

//		List<Product_favVO> list = dao.getAll();
//		for (Product_favVO pfVO2 : list) {
//			System.out.print(pfVO2.getStuno() + ",");
//			System.out.print(pfVO2.getProdno() + ",");
//			System.out.println();
//		}

		List<Product_favVO> list2 = dao.findByStuno("S001");
		for (Product_favVO pfVO3 : list2) {
			System.out.print(pfVO3.getStuno() + ",");
			System.out.print(pfVO3.getProdno() + ",");
			System.out.println();
		}

	}

}
