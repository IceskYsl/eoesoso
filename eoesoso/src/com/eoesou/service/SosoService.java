package com.eoesou.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SosoService extends Service{
	public static final String TAG = ">>>SosoService<<<";
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "------> onCreate()");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "------> onBind()");
		return null;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "------> onUnbind()");
		return super.onUnbind(intent);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "------> onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "------> onDestroy()");
	}

}
