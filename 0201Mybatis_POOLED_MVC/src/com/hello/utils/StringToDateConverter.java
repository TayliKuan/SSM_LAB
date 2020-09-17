package com.hello.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//小心不要加import錯
import org.springframework.core.convert.converter.Converter;

//自訂義 把String轉Date 原本只接受2020/1/1 想要2020-1-1也行
public class StringToDateConverter implements Converter<String,Date>{

	@Override
	public Date convert(String source) {
		//判斷不為空
		if(source == null) {
			throw new RuntimeException("請輸入日期");
		}
		//
		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		try {
			//把字串轉為日期
			return df.parse(source);
		} catch (Exception e) {
			throw new RuntimeException("日期轉換有誤");
		}
	}



}
