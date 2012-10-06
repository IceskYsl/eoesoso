package com.eoesou.test;

import com.eoesou.net.AppService;

import android.test.AndroidTestCase;
import android.util.Log;

public class EoeHttpTest extends AndroidTestCase{
	AppService service;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		service = new AppService();
		Log.i("@@@", "初始化成功");
	}
	public void getCatygories() throws Exception {
		Log.i("@@@",service.getCatygories(null));
	}
}
