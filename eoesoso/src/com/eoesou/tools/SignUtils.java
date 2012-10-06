package com.eoesou.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUtils {
	public static String getSign(String methodName,String data){
		return MD5.encode(methodName+"rHlu6A0stzzmf1euGcBJA" + "eoesoso" +data + "1.0" + "oWFNmSXTtLDigBU92nI6JWUkUmvvC1O3fndTviY");
	}
	public static String format() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	public static void main(String[] args) {
		System.out.println(format());
	}
}
