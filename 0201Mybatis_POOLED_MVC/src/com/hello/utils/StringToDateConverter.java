package com.hello.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//�p�ߤ��n�[import��
import org.springframework.core.convert.converter.Converter;

//�ۭq�q ��String��Date �쥻�u����2020/1/1 �Q�n2020-1-1�]��
public class StringToDateConverter implements Converter<String,Date>{

	@Override
	public Date convert(String source) {
		//�P�_������
		if(source == null) {
			throw new RuntimeException("�п�J���");
		}
		//
		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		try {
			//��r���ର���
			return df.parse(source);
		} catch (Exception e) {
			throw new RuntimeException("����ഫ���~");
		}
	}



}
