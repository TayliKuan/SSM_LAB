package com.activity.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.expertise.model.ExpVO;

import java.sql.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ActivityJNDIDAO implements ActivityDAO_interface {

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

	private static final String INSERT_PSTMT = "INSERT INTO　ACTIVITY (actno,actname,actloc,actdate,actss,actstart,actend,acttype,actprice,actmin,actmax,actcur,actdesc,actsta,actpic,stuno,coano)"
			+ "VALUES ('A'||LPAD(to_char(ACTIVITY_seq.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_PSTMT = "SELECT actno,actname,actloc,to_char(actdate,'yyyy-mm-dd')actdate,actss,to_char(actstart,'yyyy-mm-dd')actstart,to_char(actend,'yyyy-mm-dd')actend,acttype,actprice,actmin,actmax,actcur,actdesc,actsta,actpic,stuno,coano FROM activity order by actno";
	private static final String GET_ONE_PSTMT = "SELECT actno,actname,actloc,to_char(actdate,'yyyy-mm-dd')actdate,actss,to_char(actstart,'yyyy-mm-dd')actstart,to_char(actend,'yyyy-mm-dd')actend,acttype,actprice,actmin,actmax,actcur,actdesc,actsta,actpic,stuno,coano FROM activity where actno = ?";
	private static final String DELETE = "DELETE　FROM activity where actno = ?";
	private static final String UPDATE = "UPDATE activity set actname=?,actloc=?,actdate=?,actss=?,actstart=?,actend=?,acttype=?,actprice=?,actmin=?,actmax=?,actdesc=?,actsta=?,actpic=?,stuno=?,coano=? where actno = ?";
	private static final String GET_TYPE_PSTMT = "SELECT * FROM expertise where expno=?";
	private static final String GET_CoachAllActivity_PSTMT = "SELECT actno,actname,actloc,to_char(actdate,'yyyy-mm-dd')actdate,actss,acttype,actprice,actcur,actsta,stuno,coano FROM activity where coano=?";
	private static final String RESERVATION = "SELECT * FROM ACTIVITY WHERE COANO = ?order by actno desc";
	private static final String UPDATE_STATUS_REGISTRATION = "UPDATE ACTIVITY SET ACTSTA = '預約未上架'  WHERE COANO=? and ACTNO = ?";/* 教練預約更改活動狀態 */
	private static final String UPDATE_STATUS_LISTING = "UPDATE ACTIVITY SET ACTSTA = '上架待成團'  WHERE ACTNO = ?";/*主揪上架活動開放報名*/
	private static final String UPDATE_STATUS_DISCONTINUED = "UPDATE ACTIVITY SET ACTSTA = '下架'  WHERE ACTNO = ?";/* 主揪下架活動或成團人數不足系統自動下架 */
	private static final String GET_ALL_FOR_HOST_PSTMT = "SELECT actno,actname,actloc,to_char(actdate,'yyyy-mm-dd')actdate,actss,to_char(actstart,'yyyy-mm-dd')actstart,to_char(actend,'yyyy-mm-dd')actend,acttype,actprice,actmin,actmax,actcur,actdesc,actsta,actpic,stuno,coano FROM activity where stuno = ?order by actno";
	
	private static final String GET_STUDENT_PSTMT = "SELECT * FROM ACTIVITY WHERE ACTNO=?";
	private static final String UPADTE_ACTIVITY_ACTCUR = "UPDATE ACTIVITY SET ACTCUR = ? , ACTSTA=? WHERE ACTNO = ?";
	private static final String CHECKTIME="SELECT ACTDATE,ACTSS FROM ACTIVITY WHERE COANO=?";
	private static final String GET_ACTTYPE_STMT="SELECT * FROM　ACTIVITY WHERE ACTTYPE=?";
	private static final String UPDATE_ACTPRICE_FOR_SALE = "UPDATE ACTIVITY SET ACTPRICE =? where ACTNO=?";
	/*Table activity order*/
	private static final String INSERT_ORDER_PSTMT = "INSERT INTO　ACTIVITY_ORDER (aord_no,actno,stuno,aord_sc,aord_time)"
			+ "VALUES (to_char(sysdate,'yyyymmdd')||'-AO'||LPAD(to_char(ACTIVITY_ORDER_seq.NEXTVAL), 3, '0'), ?, ?,?,CURRENT_TIMESTAMP)";

	
	/* 基本--------------------------------------- */
	
//  活動新增
	@Override
	public void insert(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PSTMT);

			pstmt.setString(1, activityVO.getActname());
			pstmt.setString(2, activityVO.getActloc());
			pstmt.setDate(3, activityVO.getActdate());
			pstmt.setString(4, activityVO.getActss());
			pstmt.setDate(5, activityVO.getActstart());
			pstmt.setDate(6, activityVO.getActend());
			pstmt.setString(7, activityVO.getActtype());
			pstmt.setDouble(8, activityVO.getActprice());
			pstmt.setInt(9, activityVO.getActmin());
			pstmt.setInt(10, activityVO.getActmax());
			pstmt.setInt(11, activityVO.getActcur());
			pstmt.setString(12, activityVO.getActdesc());
			pstmt.setString(13, activityVO.getActsta());
			pstmt.setBytes(14, activityVO.getActpic());
			pstmt.setString(15, activityVO.getStuno());
			pstmt.setString(16, activityVO.getCoano());

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

//  活動修改
	@Override
	public void update(ActivityVO activityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, activityVO.getActname());
			pstmt.setString(2, activityVO.getActloc());
			pstmt.setDate(3, activityVO.getActdate());
			pstmt.setString(4, activityVO.getActss());
			pstmt.setDate(5, activityVO.getActstart());
			pstmt.setDate(6, activityVO.getActend());
			pstmt.setString(7, activityVO.getActtype());
			pstmt.setDouble(8, activityVO.getActprice());
			pstmt.setInt(9, activityVO.getActmin());
			pstmt.setInt(10, activityVO.getActmax());
			pstmt.setString(11, activityVO.getActdesc());
			pstmt.setString(12, activityVO.getActsta());
			pstmt.setBytes(13, activityVO.getActpic());
			pstmt.setString(14, activityVO.getStuno());
			pstmt.setString(15, activityVO.getCoano());
			pstmt.setString(16, activityVO.getActno());

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

//  活動刪除-->暫不使用
	@Override
	public void delete(String actno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, actno);

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

//  單一查詢	
	@Override
	public ActivityVO findByPrimaryKey(String actno) {

		ActivityVO activityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PSTMT);

			pstmt.setString(1, actno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActno(rs.getString("actno"));
				activityVO.setActname(rs.getString("actname"));
				activityVO.setActloc(rs.getString("actloc"));
				activityVO.setActdate(rs.getDate("actdate"));
				activityVO.setActss(rs.getString("actss"));
				activityVO.setActstart(rs.getDate("actstart"));
				activityVO.setActend(rs.getDate("actend"));
				activityVO.setActtype(rs.getString("acttype"));
				activityVO.setActprice(rs.getInt("actprice"));
				activityVO.setActmin(rs.getInt("actmin"));
				activityVO.setActmax(rs.getInt("actmax"));
				activityVO.setActcur(rs.getInt("actcur"));
				activityVO.setActdesc(rs.getString("actdesc"));
				activityVO.setActsta(rs.getString("actsta"));
				activityVO.setActpic(rs.getBytes("actpic"));
				activityVO.setStuno(rs.getString("stuno"));
				activityVO.setCoano(rs.getString("coano"));
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
		return activityVO;
	}

//  全部查詢	
	@Override
	public List<ActivityVO> getAll() {
		List<ActivityVO> list = new ArrayList<ActivityVO>();

		ActivityVO activityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActno(rs.getString("actno"));
				activityVO.setActname(rs.getString("actname"));
				activityVO.setActloc(rs.getString("actloc"));
				activityVO.setActdate(rs.getDate("actdate"));
				activityVO.setActss(rs.getString("actss"));
				activityVO.setActstart(rs.getDate("actstart"));
				activityVO.setActend(rs.getDate("actend"));
				activityVO.setActtype(rs.getString("acttype"));
				activityVO.setActprice(rs.getInt("actprice"));
				activityVO.setActmin(rs.getInt("actmin"));
				activityVO.setActmax(rs.getInt("actmax"));
				activityVO.setActcur(rs.getInt("actcur"));
				activityVO.setActdesc(rs.getString("actdesc"));
				activityVO.setActsta(rs.getString("actsta"));
				activityVO.setActpic(rs.getBytes("actpic"));
				activityVO.setStuno(rs.getString("stuno"));
				activityVO.setCoano(rs.getString("coano"));
				list.add(activityVO);
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

	/* 變化--------------------------------------- */
//全部查詢 主揪由學員編號找到發起的全部活動
	@Override
	public List<ActivityVO> getAllForHost(String stuno) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FOR_HOST_PSTMT);
			pstmt.setString(1, stuno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActno(rs.getString("actno"));
				activityVO.setActname(rs.getString("actname"));
				activityVO.setActloc(rs.getString("actloc"));
				activityVO.setActdate(rs.getDate("actdate"));
				activityVO.setActss(rs.getString("actss"));
				activityVO.setActstart(rs.getDate("actstart"));
				activityVO.setActend(rs.getDate("actend"));
				activityVO.setActtype(rs.getString("acttype"));
				activityVO.setActprice(rs.getInt("actprice"));
				activityVO.setActmin(rs.getInt("actmin"));
				activityVO.setActmax(rs.getInt("actmax"));
				activityVO.setActcur(rs.getInt("actcur"));
				activityVO.setActdesc(rs.getString("actdesc"));
				activityVO.setActsta(rs.getString("actsta"));
				activityVO.setActpic(rs.getBytes("actpic"));
				activityVO.setStuno(rs.getString("stuno"));
				activityVO.setCoano(rs.getString("coano"));
				list.add(activityVO);
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

	
//全部查詢 由教練編號找到全部活動放入課表(轉JSON格式)
	@Override
	public JSONArray getAllToCoachTable(String coano) {
		JSONArray allActivityArray = new JSONArray();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CoachAllActivity_PSTMT);
			pstmt.setString(1, coano);
			rs = pstmt.executeQuery();

			ResultSetMetaData metadata = (ResultSetMetaData) rs.getMetaData();
			int columncount = metadata.getColumnCount();
			while (rs.next()) {
				JSONObject oneActivity = new JSONObject();
				for (int i = 1; i <= columncount; i++) {

					oneActivity.put("actno", rs.getString("actno"));
					oneActivity.put("actname", rs.getString("actname"));
					oneActivity.put("actloc", rs.getString("actloc"));
					oneActivity.put("actdate", rs.getDate("actdate"));
					oneActivity.put("actss", rs.getString("actss"));
					oneActivity.put("acttype", rs.getString("acttype"));
					oneActivity.put("actprice", rs.getString("actprice"));
					oneActivity.put("actcur", rs.getString("actcur"));
//					oneActivity.put("actdesc", rs.getString("actdesc"));
					oneActivity.put("actsta", rs.getString("actsta"));
					oneActivity.put("stuno", rs.getString("stuno"));
					oneActivity.put("coano", rs.getString("coano"));
					allActivityArray.put(oneActivity);

				}
			}

		} catch (JSONException je) {
			je.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();

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
		return allActivityArray;

	}

 //單一查詢 由專長編號找到專長說明
	@Override
	public ExpVO findByExpertise(String expno) {
		ExpVO expVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TYPE_PSTMT);
			pstmt.setString(1, expno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				expVO = new ExpVO();
				expVO.setExpno(rs.getString("expno"));
				expVO.setExpdesc(rs.getString("expdesc"));
			}

			expVO.getExpdesc();
			System.out.println(expVO.getExpdesc());
		} catch (SQLException se) {
			se.printStackTrace();
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

		return expVO;
	}
//	全部查詢 教練由教練編號找到預約待確認的活動
	@Override
	public List<ActivityVO> getAllReservation(String coano) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(RESERVATION);
			pstmt.setString(1, coano);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActno(rs.getString("actno"));
				activityVO.setActname(rs.getString("actname"));
				activityVO.setActloc(rs.getString("actloc"));
				activityVO.setActdate(rs.getDate("actdate"));
				activityVO.setActss(rs.getString("actss"));
				activityVO.setActstart(rs.getDate("actstart"));
				activityVO.setActend(rs.getDate("actend"));
				activityVO.setActtype(rs.getString("acttype"));
				activityVO.setActprice(rs.getInt("actprice"));
				activityVO.setActmin(rs.getInt("actmin"));
				activityVO.setActmax(rs.getInt("actmax"));
				activityVO.setActcur(rs.getInt("actcur"));
				activityVO.setActdesc(rs.getString("actdesc"));
				activityVO.setActsta(rs.getString("actsta"));
				activityVO.setActpic(rs.getBytes("actpic"));
				activityVO.setStuno(rs.getString("stuno"));
				activityVO.setCoano(rs.getString("coano"));
				list.add(activityVO);
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
	
// 預約確認  教練確認預約-->更新活動狀態為預約未上架
	public void update_sta_bycoach(String coano,String actno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
	
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS_REGISTRATION);
			pstmt.setString(1, coano);
			pstmt.setString(2, actno);
			pstmt.executeUpdate(); 
				
		} catch (SQLException se) {
			se.printStackTrace();
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
// 上架  主揪確認活動已預約完成開始上架-->更新活動狀態為上架待成團並同時報名
	@SuppressWarnings("resource")
	@Override
	public void update_sta_byhost(String stuno,String actno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FOR_HOST_PSTMT);
			pstmt.setString(1, stuno);
			rs = pstmt.executeQuery(); 
			
			pstmt = con.prepareStatement(UPDATE_STATUS_LISTING);
			pstmt.setString(1, actno);
			pstmt.executeUpdate();

			pstmt = con.prepareStatement(INSERT_ORDER_PSTMT);
			pstmt.setString(1, actno);
			pstmt.setString(2, stuno);
			pstmt.setInt(3, 0);

			pstmt.executeUpdate();

			pstmt = con.prepareStatement(GET_STUDENT_PSTMT);
			pstmt.setString(1, actno);
			rs =  pstmt.executeQuery();
			
			String actsta = null;
			int actcur_count = 0;
			
			while (rs.next()) {
				if (rs.getInt("actcur") >= rs.getInt("actmin")) {
					System.out.println(rs.getInt("actcur"));
					System.out.println(rs.getInt("actmin"));
					actsta = "上架已成團";
				} else {
					actsta = "上架待成團";
				}
				actcur_count = rs.getInt("actcur");
				actcur_count++;
				actno = rs.getString("actno");
				System.out.println(actcur_count);

			}
			
			pstmt = con.prepareStatement(UPADTE_ACTIVITY_ACTCUR);
			pstmt.setInt(1, actcur_count);
			pstmt.setString(2, actsta);
			pstmt.setString(3, actno);

			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

			
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being");
					System.err.println("rollback");
					con.rollback();
				} catch (SQLException sexp) {
					throw new RuntimeException("rollback error occured. " + sexp.getMessage());
				}
			}
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
	
// 下架  預約失敗或人數不足系統自動更新活動狀態
	public void update_sta_auto(String actno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS_DISCONTINUED);
			pstmt.setString(1, actno);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
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

//連接教練課表
	@Override
	public JSONArray checkTime(String coano) {
		JSONArray allTimes = new JSONArray();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECKTIME);

			pstmt.setString(1, coano);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				JSONObject oneAct = new JSONObject();

				for (int i = 1; i <= 2; i++) {
					try {
						java.sql.Date sqlDate = rs.getDate("actdate");
						String actdate = sqlDate.toString();
						oneAct.put("actdate", actdate);
						oneAct.put("actss", rs.getString("actss"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				allTimes.put(oneAct);
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

		return allTimes;
	}
	
	
//從活動類型找到活動
	@Override
	public List<ActivityVO> findByActType(String acttype) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACTTYPE_STMT);
			pstmt.setString(1, acttype);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActno(rs.getString("actno"));
				activityVO.setActname(rs.getString("actname"));
				activityVO.setActloc(rs.getString("actloc"));
				activityVO.setActdate(rs.getDate("actdate"));
				activityVO.setActss(rs.getString("actss"));
				activityVO.setActstart(rs.getDate("actstart"));
				activityVO.setActend(rs.getDate("actend"));
				activityVO.setActtype(rs.getString("acttype"));
				activityVO.setActprice(rs.getInt("actprice"));
				activityVO.setActmin(rs.getInt("actmin"));
				activityVO.setActmax(rs.getInt("actmax"));
				activityVO.setActcur(rs.getInt("actcur"));
				activityVO.setActdesc(rs.getString("actdesc"));
				activityVO.setActsta(rs.getString("actsta"));
				activityVO.setActpic(rs.getBytes("actpic"));
				activityVO.setStuno(rs.getString("stuno"));
				activityVO.setCoano(rs.getString("coano"));
				list.add(activityVO);
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
//更新學員點數
	@Override
	public void update_actprice(int actprice,String actno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ACTPRICE_FOR_SALE);
			pstmt.setInt(1, actprice);
			pstmt.setString(1, actno);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
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
//  傳圖片進資料庫方法
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

}
