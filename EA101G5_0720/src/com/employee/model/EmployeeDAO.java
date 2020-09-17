package com.employee.model;

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
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployeeDAO implements EmployeeDAO_interface{
	
	//建立連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		//新增員工(含圖片)
		private static final String INSERT_STMT = "INSERT INTO EMPLOYEE VALUES ('E'||LPAD(to_char(EMPNO_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?)";
		//列出所有員工
		private static final String GET_ALL_STMT = "SELECT EMPNO,ENAME,EACC,EPSW,EMAIL,EDATE,ESTA FROM EMPLOYEE ORDER BY EMPNO";
		//列出單一員工(基本不用)
		private static final String GET_ONE_STMT = "SELECT EMPNO,ENAME,EACC,EPSW,EMAIL,EDATE,EPIC,ESTA FROM EMPLOYEE WHERE EMPNO = ?";
		//刪除員工(基本不用)
		private static final String DELETE = "DELETE FROM EMPLOYEE WHERE EMPNO = ? ";
		//修改員工資料(主要權限更動)
		private static final String UPDATE_STA = "UPDATE EMPLOYEE SET ENAME = ? , EACC = ? , EPSW = ? ,EMAIL = ? , EDATE = ?,EPIC = ?, ESTA = ?  WHERE EMPNO = ? ";
		//忘記密碼查詢
		private static final String FORGET_PSW = "SELECT EPSW , ENAME , EMAIL FROM EMPLOYEE WHERE EACC = ? AND EMAIL = ? ";
		//登入驗證(給帳號讓servlet比對密碼)(同步處理權限&對應功能開放)
		private static final String LOGIN = "SELECT EMPNO , ENAME , ESTA , EPSW FROM EMPLOYEE WHERE EACC = ? ";
		
		@Override
		public void insertEmp(EmployeeVO empVO) {
			
			Connection con = null ;
			PreparedStatement pstmt = null ;
			
				try {		
					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT);
					
					pstmt.setString(1, empVO.getEname());
					pstmt.setString(2, empVO.getEacc());
					pstmt.setString(3, empVO.getEpsw());
					pstmt.setString(4, empVO.getEmail());
					pstmt.setDate(5, empVO.getEdate());
					pstmt.setBytes(6, empVO.getEpic());
					pstmt.setString(7, empVO.getEsta());
					
					pstmt.executeUpdate();
				}  catch (SQLException se) {
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
		public void modifyEmpSta(EmployeeVO empVO) {
			
			Connection con = null ;
			PreparedStatement pstmt = null ;
				
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STA);
				
				pstmt.setString(1, empVO.getEname());
				pstmt.setString(2, empVO.getEacc());
				pstmt.setString(3, empVO.getEpsw());
				pstmt.setString(4, empVO.getEmail());
				pstmt.setDate(5, empVO.getEdate());
				pstmt.setBytes(6, empVO.getEpic());
				pstmt.setString(7, empVO.getEsta());
				pstmt.setString(8, empVO.getEmpno());
				
				pstmt.executeUpdate();
			}  catch (SQLException se) {
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
		public void deleteEmp(String empno) {
			
			Connection con = null ;
			PreparedStatement pstmt = null ;
				
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, empno);
				pstmt.executeUpdate();
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
		public EmployeeVO selectByEmpno(String empno) {
			
			EmployeeVO empVO = null ;
			Connection con = null ;
			PreparedStatement pstmt = null ;
			ResultSet rs = null ;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);			
				pstmt.setString(1, empno);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					empVO = new EmployeeVO();
					empVO.setEmpno(rs.getString("empno"));
					empVO.setEname(rs.getString("ename"));
					empVO.setEacc(rs.getString("eacc"));
					empVO.setEpsw(rs.getString("epsw"));
					empVO.setEmail(rs.getString("email"));
					empVO.setEdate(rs.getDate("edate"));
					empVO.setEpic(rs.getBytes("epic"));
					empVO.setEsta(rs.getString("esta"));
				}
			}  catch (SQLException se) {
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
			return empVO;
		}
		@Override
		public List<EmployeeVO> selectAllEmp() {
			
			List<EmployeeVO>emplist = new ArrayList<EmployeeVO>() ;	//宣告list存放所有EmployeeVo物件
			EmployeeVO empVO = null ;
			Connection con = null ;
			PreparedStatement pstmt = null ;
			ResultSet rs = null ;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					empVO = new EmployeeVO();
					empVO.setEmpno(rs.getString("empno"));
					empVO.setEname(rs.getString("ename"));
					empVO.setEacc(rs.getString("eacc"));
					empVO.setEpsw(rs.getString("epsw"));
					empVO.setEmail(rs.getString("email"));
					empVO.setEdate(rs.getDate("edate"));
					empVO.setEsta(rs.getString("esta"));
					emplist.add(empVO);
				}
			}  catch (SQLException se) {
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
			return emplist;
		}
		
		@Override
		public EmployeeVO forgetPsw (String eacc , String email) {
			
			Connection con = null ;
			PreparedStatement pstmt = null ;
			ResultSet rs = null ;
			EmployeeVO empVO = null ;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(FORGET_PSW);			
				pstmt.setString(1, eacc);
				pstmt.setString(2, email);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					empVO = new EmployeeVO();
					empVO.setEpsw(rs.getString("epsw"));
					empVO.setEname(rs.getString("ename"));
					empVO.setEmail(rs.getString("email"));
				}
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
			return empVO; //有epsw ename email的VO		
		}
		
		//登入驗證
		@Override
		public EmployeeVO selectByEacc(String eacc) {
			EmployeeVO empVO = null ;
			Connection con = null ;
			PreparedStatement pstmt = null ;
			ResultSet rs = null ;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(LOGIN);
				pstmt.setString(1, eacc);
				rs = pstmt.executeQuery();
				
				//loginSuccess顯示名字,同步抓取權限判斷
				while(rs.next()) {
					empVO = new EmployeeVO();
					empVO.setEmpno(rs.getString("empno"));
					empVO.setEpsw(rs.getString("epsw"));
					empVO.setEname(rs.getString("ename"));
					empVO.setEsta(rs.getString("esta"));
				}
				
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
			return empVO;
		}
		
		public static byte[ ] setEmployeePicture(String path) throws IOException{
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[ ] buffer = new byte [8192] ;
			int i ;
			while((i = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			fis.close();
			return baos.toByteArray();
		}
		
		public String getRandomString() {
			String str="abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
			Random random=new Random();
			StringBuffer sf=new StringBuffer();
			for(int i = 0 ; i <10 ; i++) {
				int number=random.nextInt(62);
				sf.append(str.charAt(number));
			}
			return sf.toString();
		}
		
	}

