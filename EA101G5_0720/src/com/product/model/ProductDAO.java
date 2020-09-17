package com.product.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class ProductDAO implements ProductDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Product(prodno,pclass_id,prodname,prodprice,prodqty,prodpic,proddesc,prodsta)VALUES('P'||LPAD(to_char(PRODUCT_seq.NEXTVAL), 3, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Product set pclass_id=?,prodname=?,prodprice=?,prodqty=?,prodpic=?,proddesc=?,prodsta=? where prodno=?";
	private static final String DELETE = "DELETE FROM Product where prodno=?";
	private static final String GET_ONE_STMT = "SELECT prodno,pclass_id,prodname,prodprice,prodqty,prodpic,proddesc,prodsta from product where prodno=?";
	private static final String GET_ALL_STMT = "SELECT * from product order by prodno desc";
	private static final String ON_SHELVES = "SELECT * from product where prodsta='上架中' and prodqty > 0";
	private static final String GET_ONE_CLASS_STMT = "SELECT * from product where pclass_id=? and prodsta='上架中'  and prodqty>0 order by prodno desc";
	private static final String UPDATE_PRICE="UPDATE Product set prodprice=? where prodno=?";

	// 新增
	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, productVO.getPclass_id());
			pstmt.setString(2, productVO.getProdname());
			pstmt.setInt(3, productVO.getProdprice());
			pstmt.setInt(4, productVO.getProdqty());
			pstmt.setBytes(5, productVO.getProdpic());
			pstmt.setString(6, productVO.getProddesc());
			pstmt.setString(7, productVO.getProdsta());

			pstmt.executeUpdate();
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

//修改
	@Override
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, productVO.getPclass_id());
			pstmt.setString(2, productVO.getProdname());
			pstmt.setInt(3, productVO.getProdprice());
			pstmt.setInt(4, productVO.getProdqty());
			pstmt.setBytes(5, productVO.getProdpic());
			pstmt.setString(6, productVO.getProddesc());
			pstmt.setString(7, productVO.getProdsta());
			pstmt.setString(8, productVO.getProdno());

			pstmt.executeUpdate();

		} catch (SQLException se) {
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

//刪除
	@Override
	public void delete(String prodno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prodno);
			pstmt.executeUpdate();

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

//查詢單筆
	@Override
	public ProductVO findByPrimaryKey(String prodno) {
		ProductVO prVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prodno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prVO = new ProductVO();
				prVO.setProdno(rs.getString("prodno"));
				prVO.setPclass_id(rs.getString("pclass_id"));
				prVO.setProdname(rs.getString("prodname"));
				prVO.setProdprice(rs.getInt("prodprice"));
				prVO.setProdqty(rs.getInt("prodqty"));
				prVO.setProddesc(rs.getString("proddesc"));
				prVO.setProdsta(rs.getString("prodsta"));
				prVO.setProdpic(rs.getBytes("prodpic"));
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
		return prVO;
	}

//查全部
	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO prVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prVO = new ProductVO();
				prVO.setProdno(rs.getString("prodno"));
				prVO.setPclass_id(rs.getString("pclass_id"));
				prVO.setProdname(rs.getString("prodname"));
				prVO.setProdprice(rs.getInt("prodprice"));
				prVO.setProdqty(rs.getInt("prodqty"));
				prVO.setProdpic(rs.getBytes("prodpic"));
				prVO.setProddesc(rs.getString("proddesc"));
				prVO.setProdsta(rs.getString("prodsta"));
				list.add(prVO); // Store the row in the list
			}
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

//查詢上架中且數量不為零的商品
	public List<ProductVO> getOnShelves() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO prVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ON_SHELVES);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prVO = new ProductVO();
				prVO.setProdno(rs.getString("prodno"));
				prVO.setPclass_id(rs.getString("pclass_id"));
				prVO.setProdname(rs.getString("prodname"));
				prVO.setProdprice(rs.getInt("prodprice"));
				prVO.setProdqty(rs.getInt("prodqty"));
				prVO.setProdpic(rs.getBytes("prodpic"));
				prVO.setProddesc(rs.getString("proddesc"));
				prVO.setProdsta(rs.getString("prodsta"));
				list.add(prVO); // Store the row in the list
			}
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
	public List<ProductVO> findyByPclass(String pclass_id) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO prVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CLASS_STMT);
			pstmt.setString(1, pclass_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prVO = new ProductVO();
				prVO.setProdno(rs.getString("prodno"));
				prVO.setPclass_id(rs.getString("pclass_id"));
				prVO.setProdname(rs.getString("prodname"));
				prVO.setProdprice(rs.getInt("prodprice"));
				prVO.setProdqty(rs.getInt("prodqty"));
				prVO.setProdpic(rs.getBytes("prodpic"));
				prVO.setProddesc(rs.getString("proddesc"));
				prVO.setProdsta(rs.getString("prodsta"));
				list.add(prVO); // Store the row in the list
			}
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

//新增圖片的方法
	public static byte[] getPicByteArray(String path) throws IOException {
		File pic = new File(path);
		FileInputStream fis = new FileInputStream(pic);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] eachBuffer = new byte[4096];// 一次送多少出去
		int currentBytes;// 當前送多少bytes出去 (ex:總共有200 bytes，一次送150
							// bytes，所以會送三次，每次currentBytes分別為150，150，50)
		while ((currentBytes = fis.read(eachBuffer)) != -1) {
			baos.write(eachBuffer, 0, currentBytes);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();// 回傳管子內建的byte陣列，取得裝有位元資料的byte陣列 陣列
	}


	@Override
	public void updateByPrice(ProductVO productVO, Connection con) {
		PreparedStatement pstmt =null;
		try {
			pstmt=con.prepareStatement(UPDATE_PRICE);
			pstmt.setInt(1, productVO.getProdprice());
			pstmt.setString(2, productVO.getProdno());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-從product");
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
