package com.product_order_list.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_class.model.Product_classVO;

public class Product_order_listDAO implements Product_order_listDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_order_listVO.getProdno());
			pstmt.setString(2, product_order_listVO.getPordno());
			pstmt.setInt(3, product_order_listVO.getPord_listqty());
			pstmt.setInt(4, product_order_listVO.getPord_listprice());

			pstmt.executeUpdate();
		}  catch (SQLException se) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, product_order_listVO.getPord_listqty());
			pstmt.setInt(2, product_order_listVO.getPord_listprice());
			pstmt.setString(3, product_order_listVO.getProdno());
			pstmt.setString(4, product_order_listVO.getPordno());

			pstmt.executeUpdate();
		}  catch (SQLException se) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prodno);
			pstmt.setString(2, pordno);

			pstmt.executeUpdate();
		}  catch (SQLException se) {
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
			con = ds.getConnection();
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

			con = ds.getConnection();
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
		}  catch (SQLException se) {
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

//INSERT INTO product_order_list(prodno,pordno,pord_listqty,pord_listprice)VALUES(?,?,?,?)
			
			System.out.println(product_order_listVO.getProdno());
			System.out.println(product_order_listVO.getPordno());
			System.out.println(product_order_listVO.getPord_listqty());
			System.out.println(product_order_listVO.getPord_listprice());
			pstmt = con.prepareStatement(INSERT_STMT);
			
		
			pstmt.setString(1, product_order_listVO.getProdno());
			pstmt.setString(2, product_order_listVO.getPordno());
			pstmt.setInt(3, product_order_listVO.getPord_listqty());
			pstmt.setInt(4, product_order_listVO.getPord_listprice());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由product_order_list");
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
	

}