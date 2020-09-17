package com.sale_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Sale_listJDBCDAO implements Sale_listDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "EA101G5";
	String passwd = "EA101G5";

	private static final String INSERT_STMT = "INSERT INTO Sale_list(sapro_no,prodno,sapro_price)VALUES(?,?,?)";
	final String UPDATE = "UPDATE sale_list set sapro_price=? where sapro_no=? and prodno=?";
	private static final String DELETE = "DELETE FROM sale_list where sapro_no=? and prodno=?";
	private static final String UPDATE_SAPRO_PRICE ="Update sale_project set sapro_price=? where sapro_no=? and prodno=?";
	private static final String GET_ALL_STMT = "SELECT * from sale_list order by sapro_no";
	private static final String GET_SAPRONO ="SELECT * from sale_list where SAPRO_NO=?";
	/*更改Table SALE_LIST*/
	private static final String GET_SALE_PRICE = "SELECT * FROM SALE_LIST where  prodno=?";
	/*更改Table product*/
	private static final String UPDATE_PRICE ="Update product set prodprice=? where prodno=?";
	@Override
	public void insert(Sale_listVO sale_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sale_listVO.getSapro_no());
			pstmt.setString(2, sale_listVO.getProdno());
			pstmt.setInt(3, sale_listVO.getSapro_price());

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
	public void update(Sale_listVO sale_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_SAPRO_PRICE);
			pstmt.setInt(1, sale_listVO.getSapro_price());
			pstmt.setString(2, sale_listVO.getSapro_no());
			pstmt.setString(3, sale_listVO.getProdno());

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
	public void delete(String sapro_no, String prodno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sapro_no);
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
	public List<Sale_listVO> findBySaprono(String sapro_no) {
		List<Sale_listVO> list = new ArrayList<Sale_listVO>();
		Sale_listVO slVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url , userid , passwd);
			pstmt = con.prepareStatement(GET_SAPRONO);
			pstmt.setString(1, sapro_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				slVO = new Sale_listVO();
				slVO.setSapro_no(rs.getString("sapro_no"));
				slVO.setProdno(rs.getString("prodno"));
				slVO.setSapro_price(rs.getInt("sapro_price"));
				list.add(slVO);
			}
		}catch (ClassNotFoundException e) {
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

	@Override
	public List<Sale_listVO> getAll() {
		List<Sale_listVO> list = new ArrayList<Sale_listVO>();
		Sale_listVO slVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				slVO = new Sale_listVO();
				slVO.setSapro_no(rs.getString("sapro_no"));
				slVO.setProdno(rs.getString("prodno"));
				slVO.setSapro_price(rs.getInt("sapro_price"));
				list.add(slVO);
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
	public void insert2(Sale_listVO sale_listVO, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, sale_listVO.getSapro_no());
			pstmt.setString(2, sale_listVO.getProdno());
			pstmt.setInt(3, sale_listVO.getSapro_price());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-從sale_list");
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
		Sale_listJDBCDAO dao = new Sale_listJDBCDAO();
//皜祈岫�憓�
//		Sale_listVO slVO1=new Sale_listVO();
//		slVO1.setSapro_no("SA010");
//		slVO1.setProdno("P002");
//		slVO1.setSapro_price(200);
//		dao.insert(slVO1);

//皜祈岫靽格

//		Sale_listVO slVO2=new Sale_listVO();
//		slVO2.setSapro_price(100);
//		slVO2.setSapro_no("SA010");
//		slVO2.setProdno("P002");
//		dao.update(slVO2);

//皜祈岫�� 
//		dao.delete("SA010", "P002");
		
		
//皜祈岫�銝�蝑�
//		List<Sale_listVO> list = dao.findBySaprono("SA001");
//		for(Sale_listVO slVO3 : list) {
//			System.out.print(slVO3.getSapro_no()+" ");
//			System.out.print(slVO3.getProdno()+" ");
//			System.out.print(slVO3.getSapro_price());
//			System.out.println();
//		}
		

//測試查詢全部
		List<Sale_listVO> list2 = dao.getAll();
		for (Sale_listVO slVO4 : list2) {
			System.out.print(slVO4.getSapro_no() + " ");
			System.out.print(slVO4.getProdno() + " ");
			System.out.print(slVO4.getSapro_price() + " ");
			System.out.println();

		}
	}


}
