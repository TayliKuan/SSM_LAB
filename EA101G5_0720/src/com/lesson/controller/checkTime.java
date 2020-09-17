package com.lesson.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import com.activity.model.ActivityService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lesson.model.LessonService;
import com.lessonTime.model.LessonTimeService;


@WebServlet("/checkTime")
public class checkTime extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/*比對新增的時段或修改的時段 是否有重複
	 重覆要提示 請改時段
	 比對 教練課程與揪團課程
	 並排除 修改時 原本已經有的課程
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("json");
		res.setCharacterEncoding("UTF-8");
		
		/*從前端接收*/ 
		String[] jarr = req.getParameterValues("jarr");
		String lessno = req.getParameter("lessno");
//		System.out.println("lessno="+lessno);
		String front = jarr[0];
		JsonParser  parser = new JsonParser();
	    JsonElement elem   = parser.parse( front );
	    JsonArray elemArr = elem.getAsJsonArray();
//	    System.out.println("elemArr="+elemArr);
	    List<String> f = new ArrayList<String>();
	    for(int i=0;i<elemArr.size()-1;i++) {//扣掉最後一個空字串
	    	JsonElement first1 = elemArr.get(i).getAsJsonObject().get("dateAndTime");
			String one = first1.toString().substring(1,13);
			System.out.println("one="+one);
			f.add(one);
	    }
//	    System.out.println("前端LIST");
	    /*前端LIST*/
	    for(int i = 0;i < f.size();i++){
//	    	System.out.println( f.get(i));
	    }
	    
	    LessonTimeService ltsvc = new LessonTimeService();
	    /*後端原本課程時段*/
	    List<String> old = ltsvc.getOneTime(lessno);
	    
	    for(String a:old) {
//	    	System.out.println("後端原本課程時段="+a);
	    }
	    
	    /*---------------------------------------------------------------------------*/
		 /*從後端傳來 課程時間*/
	    LessonService lessonService = new LessonService();
		JSONArray checkTime = lessonService.checkTime("C001");
//		System.out.println(checkTime);
//		System.out.println("checkTime.length()="+checkTime.length());
		List<String> db = new ArrayList<String>();
		
		 try {
			 
			for(int j=0;j<checkTime.length();j++) {
				JSONObject  result = checkTime.getJSONObject(j);
				String ss = result.getString("ltime_ss");
				String date1= result.getString("ltime_date");
				String str = date1+ss;
//				System.out.println("教練課程="+str);
				db.add(str);
			}
			
		/*從後端傳來 揪團時間*/
		ActivityService	actSvc = new ActivityService();
		JSONArray checkActTime = actSvc.checkTime("C001");
		
			for(int j=0;j<checkActTime.length();j++) {
				JSONObject  result = checkActTime.getJSONObject(j);
				String ss = result.getString("actss");
				String date1= result.getString("actdate");
				String str = date1+ss;
//				System.out.println("揪團課程="+str);
				db.add(str);
			}	
			System.out.println("後端LIST");
			 /*後端LIST*/
			for(int j = 0;j < db.size();j++){
//		    	System.out.println(db.get(j));
		    }
			/*後端與原本時段比對*/
			for(int i =0;i<old.size();i++) {
				
				for(int j=0;j<db.size();j++) {
					
					if(old.get(i).equals(db.get(j))) {
//						System.out.println("想移除的="+db.remove(j));
						db.remove(j);
						
						break;
					}
				}
				
			}

			for (String  test: db) {
		        
//		            System.out.println("db移除原時段後="+test);
		       
		    }
			/*---------------------------------------------------------------------------*/
			/*前後LIST比對*/
			
			/*如果有重複 放到這裡面*/
			List<String> result = new ArrayList<String>();
			
			/*比對*/
			for (String  same: f) {
		        if (db.contains(same)) {
		            result.add(same);
		        }
		    }
			/*印出重複項目*/
		    String message =null;
			for(int j = 0;j < result.size();j++){
				message="重複時間 請更改此時段 : "+result.get(j);
		    }
			 Gson gson = new Gson();
			 String msg = gson.toJson(message);
			 if(result.size()>0) {
				 
				 PrintWriter out = res.getWriter();
				 out.println(msg);
				 out.flush();
				 out.close();
				 System.out.println(msg);
			 }else {
			     String Success = "Success!";
			     String ok = gson.toJson(Success);
				 PrintWriter outs = res.getWriter();
				 outs.println(ok);
				 outs.flush();
				 outs.close();
				 System.out.println(ok);

			 }
		 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
