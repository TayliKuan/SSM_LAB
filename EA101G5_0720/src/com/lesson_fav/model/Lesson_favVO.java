package com.lesson_fav.model;

import java.io.*;


public class Lesson_favVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String stuno;
	private String lessno;
	
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	
	public String getLessno() {
		return lessno;
	}
	public void setLessno(String lessno) {
		this.lessno = lessno;
	}
	
	
}
