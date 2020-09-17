package com.utils;

public class StringUtil {

	public static String genRamdomStr(int length) {
		String a = "23456789qwertyuiopasdfghjkzxcvbnmABCDEFGHIJKLMNPQRSTUVWXYZ";
		char[] rands = new char[length];
		for (int i = 0; i < rands.length; i++) {
			int rand = (int) (Math.random() * a.length());
			rands[i] = a.charAt(rand);
		}
		return String.valueOf(rands);
	}

	public static String genRamdomNumericStr(int length) {
		String a = "0123456789";
		char[] rands = new char[length];
		for (int i = 0; i < rands.length; i++) {
			int rand = (int) (Math.random() * a.length());
			rands[i] = a.charAt(rand);
		}
		return String.valueOf(rands);
	}

}
