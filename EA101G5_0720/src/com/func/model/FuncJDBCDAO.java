package com.func.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncJDBCDAO implements FuncDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G5";
	String passwd = "123456";
	
	//新增功能
	private static final String INSERT_STMT = "INSERT INTO FUNC VALUES ('F'||LPAD(to_char(FUNCNO_SEQ.NEXTVAL),3,'0'),?,?)";
	//列出所有功能
	private static final String GET_ALL_STMT = "SELECT * FROM FUNC ORDER BY FUNCNO";
	//列出系統管理員能使用的功能(文字)
	private static final String GET_ALLFUNC_SYS = "SELECT ESTA , FUNCNAME FROM EMPLOYEE JOIN AUTHORITY ON AUTHORITY.EMPNO = EMPLOYEE.EMPNO JOIN FUNC ON FUNC.FUNCNO = AUTHORITY.FUNCNO WHERE ESTA = '系統管理員' ORDER BY ESTA";
	//列出管理員能使用的功能(文字)
	private static final String GET_ALLFUNC_AUTH = "SELECT ESTA , FUNCNAME FROM EMPLOYEE JOIN AUTHORITY ON AUTHORITY.EMPNO = EMPLOYEE.EMPNO JOIN FUNC ON FUNC.FUNCNO = AUTHORITY.FUNCNO WHERE ESTA = ? ORDER BY ESTA";
	//列出單一功能(基本不用)
	private static final String GET_ONE_STMT = "SELECT FUNCNO , FUNCNAME , FUNCDESC FROM FUNC WHERE FUNCNO = ?";
	//刪除功能(基本不用)
	private static final String DELETE = "DELETE FROM FUNC WHERE FUNCNO = ? ";
	//修改功能
	private static final String UPDATE = "UPDATE FUNC SET FUNCNAME = ? , FUNCDESC = ? WHERE FUNCNO = ? ";

	@Override
	public void insertFunc(FuncVO FuncVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, FuncVO.getFuncname());
			pstmt.setString(2, FuncVO.getFuncdesc());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void modifyFunc(FuncVO FuncVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, FuncVO.getFuncname());
			pstmt.setString(2, FuncVO.getFuncdesc());
			pstmt.setString(3, FuncVO.getFuncno());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void deleteFunc(String funcno) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, funcno);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public FuncVO selectByFuncno(String funcno) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		FuncVO funcVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFuncno(rs.getString("funcno"));
				funcVO.setFuncname(rs.getString("funcname"));
				funcVO.setFuncdesc(rs.getString("funcdesc"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		
		return null;
	}

	@Override
	public List<FuncVO> selectAllFunc() {
		
		List<FuncVO>funclist = new ArrayList<FuncVO>();
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		FuncVO funcVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFuncno(rs.getString("funcno"));
				funcVO.setFuncname(rs.getString("funcname"));
				funcVO.setFuncdesc(rs.getString("funcdesc"));
				funclist.add(funcVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return funclist;
	}
	
	//未完成方法
	@Override
	public List<FuncVO> selectAllFuncSys(){
		
		List<FuncVO>funclist = new ArrayList<FuncVO>();
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		FuncVO funcVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALLFUNC_SYS );
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFuncname(rs.getString("funcname"));
				funcVO.setEsta(rs.getString("esta"));
				funclist.add(funcVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return funclist ;
	}
	
	@Override
	public List<FuncVO> selectAllFuncAuth(String esta) {
		
		List<FuncVO>funclist = new ArrayList<FuncVO>();
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		FuncVO funcVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALLFUNC_AUTH );
			pstmt.setString(1, esta);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFuncname(rs.getString("funcname"));
				funcVO.setEsta(rs.getString("esta"));
				funclist.add(funcVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return funclist ;
	}

	public static void main(String[] args) {
		
		FuncJDBCDAO dao = new FuncJDBCDAO();
//		List<FuncVO>funclistauth = dao.selectAllFuncSys();
//		for(FuncVO func : funclistauth) {
//			System.out.print(func.getEsta()+",");
//			System.out.print(func.getFuncname());
//			System.out.println();
//		}
		List<FuncVO>funclistauth = dao.selectAllFuncAuth("一般管理員");
		for(FuncVO func : funclistauth) {	
			System.out.print(func.getEsta()+",");
			System.out.println(func.getFuncname());
		}
	}
}
