package com.coach.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

public class CoaDAO implements CoaDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO coach (coano,coaname,coapsw,coamail,coatel,coaacc,coapic,coasex,coaintro) VALUES ('C'||LPAD(to_char(coach_seq.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE coach set coaname=?, coapsw=?, coamail=?, coatel=?, coaacc=?, coapoint=?, coasta=?, coapic=?, coasex=?, coaintro=?, coasctotal=?, coascqty=? where coano=?";
	private static final String DELETE = "DELETE FROM coach where coano = ?";
	private static final String GET_ONE_STMT = "SELECT coano,coaname,coapsw,coamail,coatel,coaacc,coapoint,coasta,coapic,coasex,coaintro,coasctotal,coascqty FROM coach where coano = ?";
	private static final String GET_ONE_BY_MAIL_PSW_STMT = "SELECT coano,coaname,coapsw,coamail,coatel,coaacc,coapoint,coasta,coapic,coasex,coaintro,coasctotal,coascqty FROM coach where coamail = ? AND coapsw=? ";
	private static final String GET_ONE_BY_MAIL_STMT = "SELECT coano,coaname,coapsw,coamail,coatel,coaacc,coapoint,coasta,coapic,coasex,coaintro,coasctotal,coascqty FROM coach where coamail =? ";
	private static final String GET_ALL_STMT = "SELECT coano,coaname,coapsw,coamail,coatel,coaacc,coapoint,coasta,coapic,coasex,coaintro,coasctotal,coascqty FROM coach order by coano";

	@Override
	public String insert(CoaVO coaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			String[] returnColumn = { "coano" };
			pstmt = con.prepareStatement(INSERT_STMT, returnColumn);
			pstmt.setString(1, coaVO.getCoaname());
			pstmt.setString(2, coaVO.getCoapsw());
			pstmt.setString(3, coaVO.getCoamail());
			pstmt.setString(4, coaVO.getCoatel());
			pstmt.setString(5, coaVO.getCoaacc());
			pstmt.setBytes(6, coaVO.getCoapic());
			pstmt.setString(7, coaVO.getCoasex());
			pstmt.setString(8, coaVO.getCoaintro());
			pstmt.executeUpdate();

			String pk = null;
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			while (generatedKeys.next()) {
				pk = generatedKeys.getString(1);
			}
			return pk;
		}
		// Handle any SQL errors
		catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		// Clean up JDBC resources
		finally {
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
	public void update(CoaVO coaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, coaVO.getCoaname());
			pstmt.setString(2, coaVO.getCoapsw());
			pstmt.setString(3, coaVO.getCoamail());
			pstmt.setString(4, coaVO.getCoatel());
			pstmt.setString(5, coaVO.getCoaacc());
			pstmt.setInt(6, coaVO.getCoapoint());
			pstmt.setString(7, coaVO.getCoasta());
			pstmt.setBytes(8, coaVO.getCoapic());
			pstmt.setString(9, coaVO.getCoasex());
			pstmt.setString(10, coaVO.getCoaintro());
			pstmt.setInt(11, coaVO.getCoasctotal());
			pstmt.setInt(12, coaVO.getCoascqty());
			pstmt.setString(13, coaVO.getCoano());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
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
	public void delete(String coano) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coano);

			pstmt.executeUpdate();

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
	public CoaVO findByPrimaryKey(String coano) {

		CoaVO coaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, coano);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				coaVO = new CoaVO();
				coaVO.setCoano(rs.getString("coano"));
				coaVO.setCoaname(rs.getString("coaname"));
				coaVO.setCoapsw(rs.getString("coapsw"));
				coaVO.setCoamail(rs.getString("coamail"));
				coaVO.setCoatel(rs.getString("coatel"));
				coaVO.setCoaacc(rs.getString("coaacc"));
				coaVO.setCoapoint(rs.getInt("coapoint"));
				coaVO.setCoasta(rs.getString("coasta"));
				coaVO.setCoapic(rs.getBytes("coapic"));
				coaVO.setCoasex(rs.getString("coasex"));
				coaVO.setCoaintro(rs.getString("coaintro"));
				coaVO.setCoasctotal(rs.getInt("coasctotal"));
				coaVO.setCoascqty(rs.getInt("coascqty"));

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
		return coaVO;
	}

	@Override
	public CoaVO findByMailNPsw(String coamail, String coapsw) {

		CoaVO coaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_MAIL_PSW_STMT);

			pstmt.setString(1, coamail);
			pstmt.setString(2, coapsw);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				coaVO = new CoaVO();
				coaVO.setCoano(rs.getString("coano"));
				coaVO.setCoaname(rs.getString("coaname"));
				coaVO.setCoapsw(rs.getString("coapsw"));
				coaVO.setCoamail(rs.getString("coamail"));
				coaVO.setCoatel(rs.getString("coatel"));
				coaVO.setCoaacc(rs.getString("coaacc"));
				coaVO.setCoapoint(rs.getInt("coapoint"));
				coaVO.setCoasta(rs.getString("coasta"));
				coaVO.setCoapic(rs.getBytes("coapic"));
				coaVO.setCoasex(rs.getString("coasex"));
				coaVO.setCoaintro(rs.getString("coaintro"));
				coaVO.setCoasctotal(rs.getInt("coasctotal"));
				coaVO.setCoascqty(rs.getInt("coascqty"));

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
		return coaVO;
	}

	@Override
	public CoaVO findByMail(String coamail) {

		CoaVO coaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_MAIL_STMT);

			pstmt.setString(1, coamail);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				coaVO = new CoaVO();
				coaVO.setCoano(rs.getString("coano"));
				coaVO.setCoaname(rs.getString("coaname"));
				coaVO.setCoapsw(rs.getString("coapsw"));
				coaVO.setCoamail(rs.getString("coamail"));
				coaVO.setCoatel(rs.getString("coatel"));
				coaVO.setCoaacc(rs.getString("coaacc"));
				coaVO.setCoapoint(rs.getInt("coapoint"));
				coaVO.setCoasta(rs.getString("coasta"));
				coaVO.setCoapic(rs.getBytes("coapic"));
				coaVO.setCoasex(rs.getString("coasex"));
				coaVO.setCoaintro(rs.getString("coaintro"));
				coaVO.setCoasctotal(rs.getInt("coasctotal"));
				coaVO.setCoascqty(rs.getInt("coascqty"));

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
		return coaVO;
	}

	@Override
	public List<CoaVO> getAll() {
		List<CoaVO> list = new ArrayList<CoaVO>();
		CoaVO coaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				coaVO = new CoaVO();
				coaVO.setCoano(rs.getString("coano"));
				coaVO.setCoaname(rs.getString("coaname"));
				coaVO.setCoapsw(rs.getString("coapsw"));
				coaVO.setCoamail(rs.getString("coamail"));
				coaVO.setCoatel(rs.getString("coatel"));
				coaVO.setCoaacc(rs.getString("coaacc"));
				coaVO.setCoapoint(rs.getInt("coapoint"));
				coaVO.setCoasta(rs.getString("coasta"));
				coaVO.setCoapic(rs.getBytes("coapic"));
				coaVO.setCoasex(rs.getString("coasex"));
				coaVO.setCoaintro(rs.getString("coaintro"));
				coaVO.setCoasctotal(rs.getInt("coasctotal"));
				coaVO.setCoascqty(rs.getInt("coascqty"));
				list.add(coaVO); // Store the row in the list
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
		return list;
	}

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
		return baos.toByteArray();// 回傳管子內建的byte陣列，取得裝有位元資料的byte陣列
	}

}
