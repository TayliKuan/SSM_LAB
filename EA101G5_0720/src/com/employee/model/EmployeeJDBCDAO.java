package com.employee.model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class EmployeeJDBCDAO implements EmployeeDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101G5";
	String passwd = "123456";
	
	//新增員工(含圖片)
	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE VALUES ('E'||LPAD(to_char(EMPNO_SEQ.NEXTVAL),3,'0'),?,?,?,?,?)";
	//列出所有員工(密碼未亂數設定)
	private static final String GET_ALL_STMT = "SELECT EMPNO,ENAME,EACC,EPSW,ESTA FROM EMPLOYEE ORDER BY EMPNO";
	//列出單一員工(基本不用)
	private static final String GET_ONE_STMT = "SELECT * FROM EMPLOYEE WHERE EMPNO = ? ";
	//刪除員工(基本不用)
	private static final String DELETE = "DELETE FROM EMPLOYEE WHERE EMPNO = ? ";
	//修改員工資料(主要權限更動)
	private static final String UPDATE_STA = "UPDATE EMPLOYEE SET ENAME = ? , EACC = ? , EPSW = ? , ESTA = ?  WHERE EMPNO = ? ";
	//忘記密碼查詢
	private static final String FORGET_PSW = "SELECT EPSW , ENAME , EMAIL FROM EMPLOYEE WHERE EACC = ? AND EMAIL = ? ";
	//登入驗證(給帳號讓servlet比對密碼)
	private static final String LOGIN = "SELECT EMPNO , EPSW , ENAME FROM EMPLOYEE WHERE EACC = ? ";

	@Override
	public void insertEmp(EmployeeVO empVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		
			try {		
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, empVO.getEname());
				pstmt.setString(2, empVO.getEacc());
				pstmt.setString(3, empVO.getEpsw());
				pstmt.setString(4, empVO.getEmail());
				pstmt.setDate(5, empVO.getEdate());
				pstmt.setBytes(6, empVO.getEpic());
				pstmt.setString(7, empVO.getEsta());
				
				pstmt.executeUpdate();
			}  catch (ClassNotFoundException e) {
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
	
	public void modifyEmpSta(EmployeeVO empVO) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
			
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_STA);
			
			pstmt.setString(1, empVO.getEname());
			pstmt.setString(2, empVO.getEacc());
			pstmt.setString(3, empVO.getEpsw());
			pstmt.setString(4, empVO.getEsta());
			pstmt.setString(5, empVO.getEmpno());
			
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
	public void deleteEmp(String empno) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
			
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, empno);
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
	public EmployeeVO selectByEmpno(String empno) {
		
		EmployeeVO empVO = null ;
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				empVO = new EmployeeVO();
				empVO.setEmpno(rs.getString("empno"));
				empVO.setEname(rs.getString("ename"));
				empVO.setEacc(rs.getString("eacc"));
				empVO.setEpsw(rs.getString("epsw"));
				//圖片陣列處理
			
				empVO.setEsta(rs.getString("esta"));			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				empVO = new EmployeeVO();
				empVO.setEmpno(rs.getString("empno"));
				empVO.setEname(rs.getString("ename"));
				empVO.setEacc(rs.getString("eacc"));
				empVO.setEpsw(rs.getString("epsw"));
				empVO.setEsta(rs.getString("esta"));
				emplist.add(empVO);
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
		return emplist;
	}
	
	//PIC存入DB function
	
	public static byte[] setEmployeePicture(String path) throws IOException{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte [8192] ;	//自訂緩衝
		int i ;
		while((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}
	
	@Override
	public EmployeeVO forgetPsw (String eacc , String email) {
		
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		EmployeeVO empVO = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
		return empVO;		
	}
	
	//登入驗證
			@Override
			public EmployeeVO selectByEacc(String eacc) {
				EmployeeVO empVO = null ;
				Connection con = null ;
				PreparedStatement pstmt = null ;
				ResultSet rs = null ;
				
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url,userid,passwd);
					pstmt = con.prepareStatement(LOGIN);
					pstmt.setString(1, eacc);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						empVO = new EmployeeVO();
						empVO.setEmpno(rs.getString("empno"));
						empVO.setEpsw(rs.getString("epsw"));
						empVO.setEname(rs.getString("ename"));
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
				return empVO;
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
	
	//JDBC TEST OK
	public static void main(String[] args) {
		
		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();
		
		EmployeeVO empVO = new EmployeeVO();
		empVO = dao.forgetPsw("AGURI", "qwer25872682@gmail.com");
		System.out.print(empVO.getEmail());
		System.out.println(empVO.getEname());
		System.out.println(empVO.getEpsw());
//		empVO = dao.selectByEacc("EMI");
//		System.out.println(empVO.getEpsw());
		
//		empVO = dao.forgetPsw("EMI", "nitta01@gmail.com");
//		System.out.println(empVO.getEmail());
		
//		empVO.setEname("大西亞玖璃");
//		empVO.setEacc("AGURI");
//		empVO.setEpsw("123456");
//		try {
//		  byte [] pic = setEmployeePicture("WebContent/images/E010.jpg") ;
//		  empVO.setEpic(pic);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		empVO.setEsta("一般管理員");
//		dao.insertEmp(empVO);
//		
//		System.out.println("新增成功");
		
//		List<EmployeeVO>list = dao.selectAllEmp();
//		for(EmployeeVO emp : list) {
//			System.out.print(emp.getEmpno()+",");
//			System.out.print(emp.getEname()+",");
//			System.out.print(emp.getEacc()+",");
//			System.out.print(emp.getEpsw()+",");
//			System.out.print(emp.getEpic()+",");
//			System.out.print(emp.getEsta());
//			System.out.println();
//		}
		
//		empVO = dao.selectByEmpno("E001");
//		System.out.print(empVO.getEmpno());
//		System.out.print(empVO.getEname());
//		System.out.print(empVO.getEacc());
//		System.out.print(empVO.getEpsw());
//		System.out.print(empVO.getEpic());
//		System.out.print(empVO.getEsta());
		
//		empVO.setEmpno("E009");
//		empVO.setEname("DAVID");
//		empVO.setEacc("test");
//		empVO.setEpsw("test");
//		empVO.setEsta("系統管理員");
//		dao.modifyEmpSta(empVO);
//		System.out.println("更新成功");
	}
}
