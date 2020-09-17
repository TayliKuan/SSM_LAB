package com.messageboard.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MessageBoardDAO implements MessageBoardDAO_interface {
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

	private static final String ADDPOST = 
			"INSERT INTO MESSAGEBOARD (ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE) "
			+ " values ('ASK'||LPAD(to_char(MESSAGEBOARD_seq.NEXTVAL), 3, '0'),?,?,?,?,?)";
	
	private static final String REPLYPOST = 
			"INSERT INTO MESSAGEBOARD (ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE) "
					+ " values ('ASK'||LPAD(to_char(MESSAGEBOARD_seq.NEXTVAL), 3, '0'),?,?,?,?,?)";
	
	private static final String GET_ALL_STMT = 
			"SELECT ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE FROM MESSAGEBOARD";
	
	private static final String GET_ONE_STMT = 
			"SELECT ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE "
					+ "FROM MESSAGEBOARD WHERE ASKNONE = ? ";
	
			
	private static final String DELETE = 
			"DELETE FROM MESSAGEBOARD WHERE ASKNONE = ?";
	
	private static final String UPDATE = 
			"UPDATE MESSAGEBOARD SET ARTNO=?,ASKDESC=?,MESREP=?,REPDESC=?,MESDATE=? "
					+ "WHERE ASKNONE = ?";
	//列出該篇日誌的留言
	private static final String LIST_ALL_BYARTNO =
			"SELECT ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE "
			+ "FROM MESSAGEBOARD WHERE ARTNO =? ";
	
	@Override
	public void add(MessageBoardVO messageBoardVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			//ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE
			con = ds.getConnection();
			pstmt = con.prepareStatement(ADDPOST);
			pstmt.setString(1, messageBoardVO.getArtNo());
			pstmt.setString(2, messageBoardVO.getAskDesc());
			pstmt.setString(3, messageBoardVO.getMesRep());
			pstmt.setString(4, messageBoardVO.getRepDesc());
			pstmt.setString(5, messageBoardVO.getMesDate());
			
			pstmt.executeUpdate();
			System.out.println("新增中...");
			
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
	}
	
	@Override // 回覆
	public void reply(MessageBoardVO messageBoardVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = ds.getConnection();// 取得連線
			pstmt = conn.prepareStatement(REPLYPOST);
			pstmt.setString(1, messageBoardVO.getArtNo());
			pstmt.setString(2, messageBoardVO.getAskDesc());
			pstmt.setString(3, messageBoardVO.getMesRep());
			pstmt.setString(4, messageBoardVO.getRepDesc());
			pstmt.setString(5, messageBoardVO.getMesDate());

			pstmt.executeUpdate();
			System.out.println("新增中...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	public void update(MessageBoardVO messageBoardVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, messageBoardVO.getAskNone());
			pstmt.setString(2, messageBoardVO.getAskDesc());
			pstmt.setString(3, messageBoardVO.getMesRep());
			pstmt.setString(4, messageBoardVO.getRepDesc());
			pstmt.setString(5, messageBoardVO.getMesDate());
			pstmt.setString(6, messageBoardVO.getAskNone());

			pstmt.executeUpdate();

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

	}

	@Override
	public List<MessageBoardVO> getAll() {
		List<MessageBoardVO> list = new ArrayList<MessageBoardVO>();
		MessageBoardVO messageBoardVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				messageBoardVO = new MessageBoardVO();
				messageBoardVO.setAskNone(rs.getString("asknone"));
				messageBoardVO.setArtNo(rs.getString("artno"));
				messageBoardVO.setAskDesc(rs.getString("askdesc"));
				messageBoardVO.setMesRep(rs.getString("mesrep"));
				messageBoardVO.setRepDesc(rs.getString("repdesc"));
				messageBoardVO.setMesDate(rs.getString("mesdate"));
				list.add(messageBoardVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
//	@Override
//	public void delete(String asknone) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//			
//			pstmt.setString(1, asknone);
//			
//			pstmt.executeUpdate();
//			
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			
//		}
//	}

//	@Override
//	public MessageBoardVO findByPrimaryKey(String askNone) {
//
//		MessageBoardVO messageBoardVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, askNone);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				messageBoardVO = new MessageBoardVO();
//				messageBoardVO.setAskNone(rs.getString("asknone"));
//				messageBoardVO.setArtNo(rs.getString("artno"));
//				messageBoardVO.setAskDesc(rs.getString("askdesc"));
//				messageBoardVO.setMesRep(rs.getString("mesrep"));
//				messageBoardVO.setRepDesc(rs.getString("repdesc"));
//				messageBoardVO.setMesDate(rs.getString("mesdate"));
//			}
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return messageBoardVO;
//	}

	@Override
	public MessageBoardVO getMsgByArtNo(String artno) {
		MessageBoardVO messageBoardVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(LIST_ALL_BYARTNO);
			pstmt.setString(1,artno);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				messageBoardVO = new MessageBoardVO();
				messageBoardVO.setAskNone(rs.getString("asknone"));
				messageBoardVO.setArtNo(rs.getString("artno"));
				messageBoardVO.setAskDesc(rs.getString("askdesc"));
				messageBoardVO.setMesRep(rs.getString("mesrep"));
				messageBoardVO.setRepDesc(rs.getString("repdesc"));
				messageBoardVO.setMesDate(rs.getString("mesdate"));
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return messageBoardVO;
	}
}