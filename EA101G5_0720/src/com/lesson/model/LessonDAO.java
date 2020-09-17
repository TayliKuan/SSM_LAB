package com.lesson.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.*;

import com.expertise.model.ExpVO;

public class LessonDAO implements LessonDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO LESSON VALUES ('L'||LPAD(to_char(LESSNO_seq.NEXTVAL), 3, '0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE LESSON SET LESSNAME=?,LESSMAX=?,LESSMIN=?,LESSTYPE=?,LESSLOC=?,LESSPRICE=?,LESSDESC=?,LESSSTART=?,LESSEND=?,LESSSTA=?,LESSTIMES=?,LESSPIC=? WHERE LESSNO=?";
	private static final String GET_TYPE_STMT = "SELECT * FROM LESSON WHERE LESSTYPE=?";
	private static final String Get_ExpByExpno_STMT = "SELECT * FROM EXPERTISE";
	private static final String GET_ALL = "SELECT * FROM LESSON ";
	private static final String GET_CoachAllLesson_STMT = "SELECT * FROM LESSON JOIN LESSON_DETAIL ON LESSON_DETAIL.LESSNO=LESSON.LESSNO JOIN LESSON_TIME ON LESSON_TIME.LTIME_NO=LESSON_DETAIL.LTIME_NO WHERE COANO=?";
	private static final String Get_CoachLesson = "SELECT * FROM LESSON WHERE COANO=?";
	private static final String Get_OneByPK = "SELECT * FROM LESSON WHERE LESSNO=?";
	private static final String UPDATE_OFF = "UPDATE LESSON SET LESSSTA = '下架' WHERE LESSNO = ?";
	private static final String GET_CURRVAL = "SELECT 'L'||LPAD(to_char(LESSNO_seq.CURRVAL), 3, '0') FROM DUAL";
	private static final String CHECKTIME = "SELECT LTIME_DATE,LTIME_SS FROM LESSON JOIN LESSON_DETAIL ON LESSON_DETAIL.LESSNO=LESSON.LESSNO JOIN LESSON_TIME ON LESSON_TIME.LTIME_NO=LESSON_DETAIL.LTIME_NO WHERE COANO=?";
	@Override
	public String insert(LessonVO lessonVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String lessno_seq=null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, lessonVO.getCoano());
			pstmt.setString(2, lessonVO.getLessname());
			pstmt.setInt(3, lessonVO.getLessmax());
			pstmt.setInt(4, lessonVO.getLessmin());

			pstmt.setInt(5, lessonVO.getLesscur());
			pstmt.setString(6, lessonVO.getLesstype());
			pstmt.setString(7, lessonVO.getLessloc());
			pstmt.setInt(8, lessonVO.getLessprice());
			pstmt.setString(9, lessonVO.getLessdesc());

			pstmt.setDate(10, lessonVO.getLessstart());
			pstmt.setDate(11, lessonVO.getLessend());
			pstmt.setString(12, lessonVO.getLesssta());
			pstmt.setInt(13, lessonVO.getLesstimes());
			pstmt.setBytes(14, lessonVO.getLesspic());

			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(GET_CURRVAL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lessno_seq = rs.getString(1);
			}
			
		
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
			return lessno_seq;
	}

	@Override
	public void update(LessonVO lessonVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, lessonVO.getLessname());
			pstmt.setInt(2, lessonVO.getLessmax());
			pstmt.setInt(3, lessonVO.getLessmin());
			pstmt.setString(4, lessonVO.getLesstype());
			pstmt.setString(5, lessonVO.getLessloc());
			pstmt.setInt(6, lessonVO.getLessprice());
			pstmt.setString(7, lessonVO.getLessdesc());
			pstmt.setDate(8, lessonVO.getLessstart());
			pstmt.setDate(9, lessonVO.getLessend());
			pstmt.setString(10, lessonVO.getLesssta());
			pstmt.setInt(11, lessonVO.getLesstimes());
			pstmt.setBytes(12, lessonVO.getLesspic());

			pstmt.setString(13, lessonVO.getLessno());

			pstmt.executeUpdate();

			
		
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
	public List<LessonVO> findLessonByLessonType(String lesstype) {
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO lessonVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TYPE_STMT);

			pstmt.setString(1, lesstype);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lessonVO = new LessonVO();
				lessonVO.setLessno(rs.getString("lessno"));
				lessonVO.setCoano(rs.getString("coano"));
				lessonVO.setLessname(rs.getString("lessname"));
				lessonVO.setLessmax(rs.getInt("lessmax"));
				lessonVO.setLessmin(rs.getInt("lessmin"));

				lessonVO.setLesscur(rs.getInt("lesscur"));
				lessonVO.setLesstype(rs.getString("lesstype"));
				lessonVO.setLessloc(rs.getString("lessloc"));
				lessonVO.setLessprice(rs.getInt("lessprice"));
				lessonVO.setLessdesc(rs.getString("lessdesc"));

				lessonVO.setLessstart(rs.getDate("lessstart"));
				lessonVO.setLessend(rs.getDate("lessend"));
				lessonVO.setLesssta(rs.getString("lesssta"));
				lessonVO.setLesstimes(rs.getInt("lesstimes"));
				lessonVO.setLesspic(rs.getBytes("lesspic"));
				list.add(lessonVO);

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

	@Override
	public List<ExpVO> getAllExpByExpno() {

		List<ExpVO> list = new ArrayList<ExpVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ExpByExpno_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ExpVO expVO = new ExpVO();
				expVO.setExpno(rs.getString("expno"));
				expVO.setExpdesc(rs.getString("expdesc"));
				list.add(expVO);
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

	@Override
	public List<LessonVO> getAll() {
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO lessonVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lessonVO = new LessonVO();

				lessonVO.setLessno(rs.getString("lessno"));
				lessonVO.setCoano(rs.getString("coano"));
				lessonVO.setLessname(rs.getString("lessname"));
				lessonVO.setLessmax(rs.getInt("lessmax"));
				lessonVO.setLessmin(rs.getInt("lessmin"));

				lessonVO.setLesscur(rs.getInt("lesscur"));
				lessonVO.setLesstype(rs.getString("lesstype"));
				lessonVO.setLessloc(rs.getString("lessloc"));
				lessonVO.setLessprice(rs.getInt("lessprice"));
				lessonVO.setLessdesc(rs.getString("lessdesc"));

				lessonVO.setLessstart(rs.getDate("lessstart"));
				lessonVO.setLessend(rs.getDate("lessend"));
				lessonVO.setLesssta(rs.getString("lesssta"));
				lessonVO.setLesstimes(rs.getInt("lesstimes"));
				lessonVO.setLesspic(rs.getBytes("lesspic"));

				list.add(lessonVO); // Store the row in the list
			}

			
			// Handle any SQL errors
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
	public JSONArray getCoachAllLesson(String coano) {
		JSONArray allLessonArray = new JSONArray();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CoachAllLesson_STMT);

			pstmt.setString(1, coano);
			rs = pstmt.executeQuery();

			ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			// 教練一堂課程=一個JSONObject 再把全部塞進JSONArray
			while (rs.next()) {
				JSONObject oneLesson = new JSONObject();

				for (int i = 1; i <= columnCount; i++) {
					try {
						oneLesson.put("coano",rs.getString("coano"));
						oneLesson.put("lessno", rs.getString("lessno"));
						oneLesson.put("lessname", rs.getString("lessname"));
						oneLesson.put("lesscur", rs.getString("lesscur"));
						oneLesson.put("lessprice", rs.getInt("lessprice"));
						oneLesson.put("lesssta", rs.getString("lesssta"));
						oneLesson.put("lesstimes", rs.getInt("lesstimes"));
						oneLesson.put("ltime_date", rs.getDate("ltime_date"));
						oneLesson.put("ltime_ss", rs.getString("ltime_ss"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				allLessonArray.put(oneLesson);
			}
			
			// Handle any SQL errors
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

		return allLessonArray;
	}


	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);// 傳進來的檔案路徑
		// 先把檔案讀進程式裡面
		FileInputStream fis = new FileInputStream(file);// 低階管相接
		ByteArrayOutputStream baos = new ByteArrayOutputStream();// BYTE陣列
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();// 就可以把圖片讀進來的位元資料 放進去BYTE陣列
	}

	@Override
	public List<LessonVO> getCoachLesson(String coano) {
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO lessonVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_CoachLesson);
			pstmt.setString(1, coano);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				lessonVO = new LessonVO();

				lessonVO.setLessno(rs.getString("lessno"));
				lessonVO.setCoano(rs.getString("coano"));
				lessonVO.setLessname(rs.getString("lessname"));
				lessonVO.setLessmax(rs.getInt("lessmax"));
				lessonVO.setLessmin(rs.getInt("lessmin"));

				lessonVO.setLesscur(rs.getInt("lesscur"));
				lessonVO.setLesstype(rs.getString("lesstype"));
				lessonVO.setLessloc(rs.getString("lessloc"));
				lessonVO.setLessprice(rs.getInt("lessprice"));
				lessonVO.setLessdesc(rs.getString("lessdesc"));

				lessonVO.setLessstart(rs.getDate("lessstart"));
				lessonVO.setLessend(rs.getDate("lessend"));
				lessonVO.setLesssta(rs.getString("lesssta"));
				lessonVO.setLesstimes(rs.getInt("lesstimes"));
				lessonVO.setLesspic(rs.getBytes("lesspic"));

				list.add(lessonVO); // Store the row in the list
			}

			
			// Handle any SQL errors
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
	public LessonVO getOneByPK(String lessno) {
		LessonVO lessonVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_OneByPK);

			pstmt.setString(1, lessno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lessonVO = new LessonVO();
				lessonVO.setLessno(rs.getString("lessno"));
				lessonVO.setCoano(rs.getString("coano"));
				lessonVO.setLessname(rs.getString("lessname"));
				lessonVO.setLessmax(rs.getInt("lessmax"));
				lessonVO.setLessmin(rs.getInt("lessmin"));

				lessonVO.setLesscur(rs.getInt("lesscur"));
				lessonVO.setLesstype(rs.getString("lesstype"));
				lessonVO.setLessloc(rs.getString("lessloc"));
				lessonVO.setLessprice(rs.getInt("lessprice"));
				lessonVO.setLessdesc(rs.getString("lessdesc"));

				lessonVO.setLessstart(rs.getDate("lessstart"));
				lessonVO.setLessend(rs.getDate("lessend"));
				lessonVO.setLesssta(rs.getString("lesssta"));
				lessonVO.setLesstimes(rs.getInt("lesstimes"));
				lessonVO.setLesspic(rs.getBytes("lesspic"));
				

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

		return lessonVO;
	}

	@Override
	public void update_off(String lessno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_OFF);

			pstmt.setString(1, lessno);

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
	public JSONArray checkTime(String coano) {
		JSONArray allLessonArray = new JSONArray();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECKTIME);

			pstmt.setString(1, coano);
			rs = pstmt.executeQuery();

			// 教練一堂課程=一個JSONObject 再把全部塞進JSONArray
			while (rs.next()) {
				JSONObject oneLesson = new JSONObject();

				for (int i = 1; i <= 2; i++) {
					try {
						java.sql.Date sqlDate = rs.getDate("ltime_date");
						String ltime_dd = sqlDate.toString();
						oneLesson.put("ltime_date", ltime_dd);
						oneLesson.put("ltime_ss", rs.getString("ltime_ss"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				allLessonArray.put(oneLesson);
			}
		
			// Handle any SQL errors
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

		return allLessonArray;
	}


}
