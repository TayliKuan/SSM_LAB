package com.product_order.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.deposit.model.DepositService;
import com.product_order.model.Product_orderVO;
import com.product_order_list.model.Product_order_listDAO;
import com.product_order_list.model.Product_order_listJDBCDAO;
import com.product_order_list.model.Product_order_listVO;

public class Product_orderDAO implements Product_orderDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO product_order(pordno,stuno,pordtotal,porddate,recipient,phonenumber,pordadd,pordsta,fare)VALUES(to_char(sysdate,'yyyymmdd')||'-PO'||LPAD(to_char(PRODUCT_ORDERseq.NEXTVAL), 3, '0'),?,?,CURRENT_TIMESTAMP,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE product_order set stuno=?, pordtotal=?,recipient=?,phonenumber=?,pordadd=?,pordsta=?,fare=? where pordno=?";
	private static final String DELETE = "DELETE FROM product_order where pordno=?";
	private static final String GET_ONE_STMT = "SELECT pordno,stuno,porddate,pordtotal,recipient,phonenumber,pordadd,pordsta,fare from product_order where pordno=? order by pordno desc";
	private static final String GET_ALL_STMT = "SELECT pordno,stuno,porddate,pordtotal,recipient,phonenumber,pordadd,pordsta,fare from product_order order by pordno desc";
	private static final String GET_ALL_BY_STUNO = "SELECT * FROM product_order where stuno=? order by pordno desc";
	
	@Override
	public void insert(Product_orderVO product_orderVO) {

		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
// "INSERT INTO product_order(pordno,stuno,pordtotal,porddate,recipient,phonenumber,pordadd,pordsta,fare)VALUES(to_char(sysdate,'yyyymmdd')||'-PO'||LPAD(to_char(PRODUCT_ORDERseq.NEXTVAL), 3, '0'),?,CURRENT_TIMESTAMP,?,?,?,?,?,?)";

			pstmt.setString(1, product_orderVO.getStuno());
			pstmt.setInt(2, product_orderVO.getPordtotal());
			pstmt.setString(3, product_orderVO.getRecipient());
			pstmt.setString(4, product_orderVO.getPhonenumber());
			pstmt.setString(5, product_orderVO.getPordadd());
			pstmt.setString(6, product_orderVO.getPordsta());
			pstmt.setInt(7, product_orderVO.getFare());

			pstmt.executeUpdate();
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
	public void update(Product_orderVO product_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, product_orderVO.getStuno());
			pstmt.setInt(2, product_orderVO.getPordtotal());
			pstmt.setString(3, product_orderVO.getRecipient());
			pstmt.setString(4, product_orderVO.getPhonenumber());
			pstmt.setString(5, product_orderVO.getPordadd());
			pstmt.setString(6, product_orderVO.getPordsta());
			pstmt.setInt(7, product_orderVO.getFare());
			pstmt.setString(8, product_orderVO.getPordno());

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
	public void delete(String pordno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pordno);
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
	public List<Product_orderVO> getAll() {
		List<Product_orderVO> list = new ArrayList<Product_orderVO>();
		Product_orderVO poVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				poVO = new Product_orderVO();
				poVO.setPordno(rs.getString("pordno"));
				poVO.setStuno(rs.getString("stuno"));
				poVO.setPorddate(rs.getDate("porddate"));
				poVO.setPordtotal(rs.getInt("pordtotal"));
				poVO.setRecipient(rs.getString("recipient"));
				poVO.setPhonenumber(rs.getString("phonenumber"));
				poVO.setPordadd(rs.getString("pordadd"));
				poVO.setPordsta(rs.getString("pordsta"));
				poVO.setFare(rs.getInt("fare"));
				list.add(poVO);
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
	public List<Product_orderVO> getAllByStuno(String stuno) {
		List<Product_orderVO> list = new ArrayList<Product_orderVO>();
		Product_orderVO poVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_STUNO);

			pstmt.setString(1, stuno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				poVO = new Product_orderVO();
				poVO.setPordno(rs.getString("pordno"));
				poVO.setPorddate(rs.getDate("porddate"));
				poVO.setPordtotal(rs.getInt("pordtotal"));
				poVO.setRecipient(rs.getString("recipient"));
				poVO.setPhonenumber(rs.getString("phonenumber"));
				poVO.setPordadd(rs.getString("pordadd"));
				poVO.setPordsta(rs.getString("pordsta"));
				poVO.setFare(rs.getInt("fare"));
				list.add(poVO);
			}
		}  catch (SQLException se) {
			se.printStackTrace();
		}
		return list;
	}

	@Override
	public void insertWithPordList(Product_orderVO product_orderVO, List<Product_order_listVO> list, int stupoint) {
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("insertWithPordList");
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String cols[] = { "PORDNO" };
//"INSERT INTO product_order(pordno,stuno,pordtotal,porddate,recipient,phonenumber,pordadd,pordsta,fare)VALUES(to_char(sysdate,'yyyymmdd')||'-PO'||LPAD(to_char(PRODUCT_ORDERseq.NEXTVAL), 3, '0'),?,CURRENT_TIMESTAMP,?,?,?,?,?)";
			
			
			System.out.println("學員編號:"+product_orderVO.getStuno());
			System.out.println("訂單總價:"+product_orderVO.getPordtotal());	
			System.out.println("訂單狀態"+product_orderVO.getPordsta());	
			System.out.println("收件人姓名:"+product_orderVO.getRecipient());		
			System.out.println("收件人地址:"+product_orderVO.getPordadd());	
			System.out.println("收件人電話:"+product_orderVO.getPhonenumber());	
			System.out.println("運費:"+product_orderVO.getFare());	
			
			
			
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, product_orderVO.getStuno());
			pstmt.setInt(2, product_orderVO.getPordtotal());
			pstmt.setString(3, product_orderVO.getRecipient());
			pstmt.setString(4, product_orderVO.getPhonenumber());
			pstmt.setString(5, product_orderVO.getPordadd());
			pstmt.setString(6, product_orderVO.getPordsta());
			pstmt.setInt(7, product_orderVO.getFare());
			
			pstmt.executeUpdate();

			String next_pordno = null;

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_pordno = rs.getString(1);
				System.out.println("自增主鍵值" + next_pordno + "(剛新增成功的商品編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
//			rs.close();
			Product_order_listDAO dao = new Product_order_listDAO();
//	******		
			for (Product_order_listVO product_order_listVO : list) {
				product_order_listVO.setPordno(next_pordno);
				System.out.println(product_order_listVO.getProdno());
				System.out.println(product_order_listVO.getPordno());
				System.out.println(product_order_listVO.getPord_listqty());
				System.out.println(product_order_listVO.getPord_listprice());
				dao.insert2(product_order_listVO, con);
			}
			
			
			
			DepositService DepositSvc = new DepositService();
			
			DepositSvc.alterStuPoint(product_orderVO.getStuno(), stupoint);
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-order_list" + list.size());
			System.out.println("新增部門編號" + next_pordno + "時，共有訂單詳情" + list.size() + "筆同時被新增");

		}catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being");
					System.err.println("rolled back由product_order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured." + excep.getMessage());
				}
			}
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
	public Product_orderVO findByPrimaryKey(String pordno) {
		Product_orderVO poVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pordno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				poVO = new Product_orderVO();
				poVO.setPordno(rs.getString("pordno"));
				poVO.setStuno(rs.getString("stuno"));
				poVO.setPorddate(rs.getDate("porddate"));
				poVO.setPordtotal(rs.getInt("pordtotal"));
				poVO.setRecipient(rs.getString("recipient"));
				poVO.setPhonenumber(rs.getString("phonenumber"));
				poVO.setPordadd(rs.getString("pordadd"));
				poVO.setPordsta(rs.getString("pordsta"));
				poVO.setFare(rs.getInt("fare"));

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
		return poVO;
	}

}
