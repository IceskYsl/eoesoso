package com.eoesou.net;

import com.eoesou.tools.SignUtils;


public class Constants {
	public static String getCatygoriesUrl() {
		String data = SignUtils.format();
		return "http://api.eoemarket.com/gateway/categories/parent_id/0/format/xml/timestamp/"+data+"/app_key/rHlu6A0stzzmf1euGcBJA/nonce/eoesoso/v/1.0/sign/"+SignUtils.getSign("categories",data);
	}
}
