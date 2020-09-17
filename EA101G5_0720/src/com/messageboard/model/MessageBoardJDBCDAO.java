//package com.messageboard.model;
//
//import java.sql.*;
//import java.util.*;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.messageboard.model.MessageBoardVO;
//
//public class MessageBoardJDBCDAO implements MessageBoardDAO_interface{
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String INSERT_STMT = 
//			"INSERT INTO MESSAGEBOARD (ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE) "
//			+ " values ('ASK'||LPAD(to_char(MESSAGEBOARD_seq.NEXTVAL), 3, '0'),?,?,?,?,?)";
//	
//	private static final String GET_ALL_STMT = 
//			"SELECT ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE FROM MESSAGEBOARD";
//	
//	private static final String GET_ONE_STMT = 
//			"SELECT ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE "
//					+ "FROM MESSAGEBOARD WHERE ASKNONE = ? ";
//	
//			
//	private static final String DELETE = 
//			"DELETE FROM MESSAGEBOARD WHERE ASKNONE = ?";
//	
//	private static final String UPDATE = 
//			"UPDATE MESSAGEBOARD SET ARTNO=?,ASKDESC=?,MESREP=?,REPDESC=?,MESDATE=? "
//					+ "WHERE ASKNONE = ?";
//	//列出該篇日誌的留言
//	private static final String LIST_ALL_BYARTNO =
//			"SELECT ASKNONE,ARTNO,ASKDESC,MESREP,REPDESC,MESDATE "
//			+ "FROM MESSAGEBOARD WHERE ARTNO =?  ORDER BY ASKNONE";
//	
////	@Override
////	public int insert(MessageBoardVO messageBoardVO) {
////		int updateCount = 0;
////		Connection con = null;
////		PreparedStatement pstmt = null;
////		
////		try {
////			con = ds.getConnection();
////			pstmt = con.prepareStatement(INSERT_STMT);
////			pstmt.setString(1, messageBoardVO.getArtNo());
////			pstmt.setString(2, messageBoardVO.getAskDesc());
////			pstmt.setString(3, messageBoardVO.getMesRep());
////			pstmt.setString(4, messageBoardVO.getRepDesc());
////			pstmt.setString(5, messageBoardVO.getMesDate());
////			
////			pstmt.executeUpdate();
////			
////			// Handle any SQL errors
////		} catch (SQLException se) {
////			throw new RuntimeException("A database error occured. "
////					+ se.getMessage());
////			// Clean up JDBC resources
////		} finally {
////			if (pstmt != null) {
////				try {
////					pstmt.close();
////				} catch (SQLException se) {
////					se.printStackTrace(System.err);
////				}
////			}
////			if (con != null) {
////				try {
////					con.close();
////				} catch (Exception e) {
////					e.printStackTrace(System.err);
////				}
////			}
////		}
////		return updateCount;
////	}
//
//	@Override
//	public void update(MessageBoardVO messageBoardVO) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//			
//			pstmt.setString(1, messageBoardVO.getArtNo());
//			pstmt.setString(2, messageBoardVO.getAskDesc());
//			pstmt.setString(3, messageBoardVO.getMesRep());
//			pstmt.setString(4, messageBoardVO.getRepDesc());
//			pstmt.setString(5, messageBoardVO.getMesDate());
//			pstmt.setString(6, messageBoardVO.getAskNone());
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
//		}
//
//	}
//
////	@Override
////	public void delete(String asknone) {
////		Connection con = null;
////		PreparedStatement pstmt = null;
////
////		try {
////
////			con = ds.getConnection();
////			pstmt = con.prepareStatement(DELETE);
////			
////			pstmt.setString(1, asknone);
////			
////		} catch (SQLException se) {
////			throw new RuntimeException("A database error occured. "
////					+ se.getMessage());
////			// Clean up JDBC resources
////		} finally {
////			if (pstmt != null) {
////				try {
////					pstmt.close();
////				} catch (SQLException se) {
////					se.printStackTrace(System.err);
////				}
////			}
////			if (con != null) {
////				try {
////					con.close();
////				} catch (Exception e) {
////					e.printStackTrace(System.err);
////				}
////			}
////			
////		}
////	}
////
////	@Override
////	public MessageBoardVO findByPrimaryKey(String askNone) {
////
////		MessageBoardVO messageBoardVO = null;
////		Connection con = null;
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////
////		try {
////
////			con = ds.getConnection();
////			pstmt = con.prepareStatement(GET_ONE_STMT);
////
////			pstmt.setString(1, askNone);
////
////			rs = pstmt.executeQuery();
////			while (rs.next()) {
////				messageBoardVO = new MessageBoardVO();
////				messageBoardVO.setAskNone(rs.getString("asknone"));
////				messageBoardVO.setArtNo(rs.getString("artno"));
////				messageBoardVO.setAskDesc(rs.getString("askdesc"));
////				messageBoardVO.setMesRep(rs.getString("mesrep"));
////				messageBoardVO.setRepDesc(rs.getString("repdesc"));
////				messageBoardVO.setMesDate(rs.getString("mesdate"));
////			}
////
////		} catch (SQLException se) {
////			throw new RuntimeException("A database error occured. "
////					+ se.getMessage());
////			// Clean up JDBC resources
////		} finally {
////			if (rs != null) {
////				try {
////					rs.close();
////				} catch (SQLException se) {
////					se.printStackTrace(System.err);
////				}
////			}
////			if (pstmt != null) {
////				try {
////					pstmt.close();
////				} catch (SQLException se) {
////					se.printStackTrace(System.err);
////				}
////			}
////			if (con != null) {
////				try {
////					con.close();
////				} catch (Exception e) {
////					e.printStackTrace(System.err);
////				}
////			}
////		}
////		return messageBoardVO;
////	}
//
//	@Override
//	public List<MessageBoardVO> getAll() {
//		List<MessageBoardVO> list = new ArrayList<MessageBoardVO>();
//		MessageBoardVO messageBoardVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				messageBoardVO = new MessageBoardVO();
//				messageBoardVO.setAskNone(rs.getString("asknone"));
//				messageBoardVO.setArtNo(rs.getString("artno"));
//				messageBoardVO.setAskDesc(rs.getString("askdesc"));
//				messageBoardVO.setMesRep(rs.getString("mesrep"));
//				messageBoardVO.setRepDesc(rs.getString("repdesc"));
//				messageBoardVO.setMesDate(rs.getString("mesdate"));
//				list.add(messageBoardVO); // Store the row in the list
//			}
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//		return list;
//	}
//
////	@Override
////	 public List<MessageBoardVO> getMsgByArtNo(String artno) {
////		List<MessageBoardVO> set = new ArrayList<>();
////		MessageBoardVO messageBoardVO = null;
////	
////		Connection con = null;
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////	
////		try {
////	
////			con = ds.getConnection();
////			pstmt = con.prepareStatement(LIST_ALL_BYARTNO);
////			pstmt.setString(1,artno);
////			rs = pstmt.executeQuery();
////	
////			while (rs.next()) {
////				messageBoardVO = new MessageBoardVO();
////				messageBoardVO.setAskNone(rs.getString("asknone"));
////				messageBoardVO.setArtNo(rs.getString("artno"));
////				messageBoardVO.setAskDesc(rs.getString("askdesc"));
////				messageBoardVO.setMesRep(rs.getString("mesrep"));
////				messageBoardVO.setRepDesc(rs.getString("repdesc"));
////				messageBoardVO.setMesDate(rs.getString("mesdate"));
////				set.add(messageBoardVO); // Store the row in the vector
////			}
////	
////		} catch (SQLException se) {
////			throw new RuntimeException("A database error occured. "
////					+ se.getMessage());
////		} finally {
////			if (rs != null) {
////				try {
////					rs.close();
////				} catch (SQLException se) {
////					se.printStackTrace(System.err);
////				}
////			}
////			if (pstmt != null) {
////				try {
////					pstmt.close();
////				} catch (SQLException se) {
////					se.printStackTrace(System.err);
////				}
////			}
////			if (con != null) {
////				try {
////					con.close();
////				} catch (Exception e) {
////					e.printStackTrace(System.err);
////				}
////			}
////		}
////		return set;
////	}
//
//}