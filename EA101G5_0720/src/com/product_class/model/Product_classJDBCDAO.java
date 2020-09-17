package com.product_class.model;

import java.sql.*;
import java.util.*;

public class Product_classJDBCDAO implements Product_classDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
//	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "EA101G5";
	String passwd = "EA101G5";

	private static final String INSERT_STMT = "INSERT INTO product_class(pclass_id,pclass_name)VALUES('PC'||LPAD(to_char(PRODUCT_CLASSseq.NEXTVAL), 3, '0'),?)";
	private static final String UPDATE = "UPDATE PRODUCT_CLASS set pclass_name=? where pclass_id=?";
	private static final String DELETE = "DELETE FROM PRODUCT_CLASS where pclass_id=?";
	private static final String GET_ONE_STMT = "SELECT pclass_id,pclass_name from product_class where pclass_id=?";
	private static final String GET_ALL_STMT = "SELECT pclass_id,pclass_name from product_class order by pclass_id";

	@Override
	public void insert(Product_classVO product_classVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_classVO.getPclass_name());
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
	public void update(Product_classVO product_classVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, product_classVO.getPclass_name());
			pstmt.setString(2, product_classVO.getPclass_id());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void delete(String pclass_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pclass_id);
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
	public Product_classVO findByPrimaryKey(String pclass_id) {
		Product_classVO pcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pclass_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcVO = new Product_classVO();
				pcVO.setPclass_id(rs.getString("pclass_id"));
				pcVO.setPclass_name(rs.getString("pclass_name"));
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
		return pcVO;
	}

	@Override
	public List<Product_classVO> getAll() {
		List<Product_classVO> list = new ArrayList<Product_classVO>();
		Product_classVO pcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcVO = new Product_classVO();
				pcVO.setPclass_id(rs.getString("pclass_id"));
				pcVO.setPclass_name(rs.getString("pclass_name"));
				list.add(pcVO); // Store the row in the list
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

	public static void main(String[] args) {
		Product_classJDBCDAO dao = new Product_classJDBCDAO();

//		Product_classVO pcVO1=new Product_classVO();
//		pcVO1.setPclass_name("���蕭謅�蕭���部豰蕭蹓蕭嚙踝蕭");
//		dao.insert(pcVO1);

//		Product_classVO pcVO2=new Product_classVO();
//		pcVO2.setPclass_id("PC001");
//		pcVO2.setPclass_name("���蕭���蕭隡嚙踝蕭謆喉蕭");
//		dao.update(pcVO2);
//		
//		dao.delete("PC006");

//		Product_classVO pcVO2=dao.findByPrimaryKey("PC001");
//		System.out.println(pcVO2.getPclass_id());
//		System.out.println(pcVO2.getPclass_name());

//		List<Product_classVO> list = dao.getAll();
//		for (Product_classVO pcVO3 : list) {
//			System.out.print(pcVO3.getPclass_id() + ",");
//			System.out.print(pcVO3.getPclass_name() + ",");
//			System.out.println();
//		}
	}
}
