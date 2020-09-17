package com.product_order_list.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.product_class.model.Product_classVO;

public class Product_order_listJDBCDAO implements Product_order_listDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "EA101G5";
	String passwd = "EA101G5";

	private static final String INSERT_STMT 
	= "INSERT INTO product_order_list(prodno,pordno,pord_listqty,pord_listprice)VALUES(?,?,?,?)";
	private static final String UPDATE = "UPDATE product_order_list set pord_listqty=?,pord_listprice=? where prodno=? and pordno=?";
	private static final String DELETE = "DELETE FROM product_order_list where prodno=? and pordno=?";
	private static final String GET_PORDNO_STMT = "SELECT prodno,pordno,pord_listqty,pord_listprice from product_order_list where pordno=?";
	private static final String GET_ALL_STMT = "SELECT * from product_order_list order by pordno";

	@Override
	public void insert(Product_order_listVO product_order_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_order_listVO.getProdno());
			pstmt.setString(2, product_order_listVO.getPordno());
			pstmt.setInt(3, product_order_listVO.getPord_listqty());
			pstmt.setInt(4, product_order_listVO.getPord_listprice());

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
	public void update(Product_order_listVO product_order_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, product_order_listVO.getPord_listqty());
			pstmt.setInt(2, product_order_listVO.getPord_listprice());
			pstmt.setString(3, product_order_listVO.getProdno());
			pstmt.setString(4, product_order_listVO.getPordno());

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
	public void delete(String prodno, String pordno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prodno);
			pstmt.setString(2, pordno);

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

	public List<Product_order_listVO> findByPordno(String pordno) {
		
		List<Product_order_listVO> list = new ArrayList<Product_order_listVO>();
		Product_order_listVO polVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PORDNO_STMT);
			pstmt.setString(1, pordno);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				polVO = new Product_order_listVO();
				polVO.setProdno(rs.getString("prodno"));
				polVO.setPordno(rs.getString("pordno"));
				polVO.setPord_listqty(rs.getInt("pord_listqty"));
				polVO.setPord_listprice(rs.getInt("pord_listprice"));
				list.add(polVO);
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

	public List<Product_order_listVO> getAll() {
		List<Product_order_listVO> list = new ArrayList<Product_order_listVO>();
		Product_order_listVO polVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				polVO = new Product_order_listVO();
				polVO.setProdno(rs.getString("prodno"));
				polVO.setPordno(rs.getString("pordno"));
				polVO.setPord_listqty(rs.getInt("Pord_listqty"));
				polVO.setPord_listprice(rs.getInt("pord_listprice"));
				list.add(polVO);
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
	public void insert2(Product_order_listVO product_order_listVO, Connection con) {
		PreparedStatement pstmt =null;
		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			
		
			pstmt.setString(1, product_order_listVO.getProdno());
			pstmt.setString(2, product_order_listVO.getPordno());
			pstmt.setInt(3, product_order_listVO.getPord_listqty());
			pstmt.setInt(4, product_order_listVO.getPord_listprice());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3��身摰���xception�����atch��憛
					System.err.print("Transaction is being ");
					System.err.println("rolled back-�product_order_list");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}
	
	public static void main(String[] args) {
		Product_order_listJDBCDAO dao = new Product_order_listJDBCDAO();

//皜祈岫�憓�		
//		Product_order_listVO polVO1 = new Product_order_listVO();
//		polVO1.setProdno("P021");
//		polVO1.setPordno("20200101-PO001");
//		polVO1.setPord_listqty(1);
//		polVO1.setPord_listprice(99);
//		
//		dao.insert(polVO1);

//皜祈岫靽格
//		Product_order_listVO polVO2 = new Product_order_listVO();
//		polVO2.setPord_listqty(1);
//		polVO2.setPord_listprice(10000);
//		polVO2.setProdno("P021");
//		polVO2.setPordno("20200101-PO001");
//
//		dao.update(polVO2);

//皜祈岫��
//		dao.delete("P021","20200101-PO001");

		// 皜祈岫���
//		List<Product_order_listVO> list = dao.getAll();
//		for (Product_order_listVO polVO3 : list) {
//			System.out.print(polVO3.getProdno() + " ");
//			System.out.print(polVO3.getPordno() + " ");
//			System.out.print(polVO3.getPord_listqty() + " ");
//			System.out.print(polVO3.getPord_listprice() + " ");
//			System.out.println();

//		Product_order_listVO polVO5= dao.findByProdno("P005");
//		List<Product_order_listVO> list2 =dao.findByProdno("P005");
//		for(Product_order_listVO polVO5 :list2) {
//			System.out.print(polVO5.getProdno() + " ");
//			System.out.print(polVO5.getPordno() + " ");
//			System.out.print(polVO5.getPord_listqty() + " ");
//			System.out.print(polVO5.getPord_listprice() + " ");
//			System.out.println();
//		}
		
//皜祈岫敺��楊��閰Ｗ���
		List<Product_order_listVO> list2 = dao.findByPordno("20200330-PO001");
		for (Product_order_listVO polVO6 : list2) {
			System.out.print(polVO6.getProdno() + ",");
			System.out.print(polVO6.getPordno() + ",");
			System.out.print(polVO6.getPord_listqty() + ",");
			System.out.print(polVO6.getPord_listprice() + ",");
			System.out.println();
		}
		
		

	}


}