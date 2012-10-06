package com.eoesou.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.eoesou.tools.StreamTools;

public class AppService {
	public void downloadApp(String appUrl) throws Exception {
		URL url = new URL(appUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String appName = appUrl.substring(appUrl.lastIndexOf("/"));
                save2SdCard(is,appName);
        }
	}
	
	public String getCatygories(String url) throws Exception {
		Log.i("@@@", "获取所有分类的url=" + Constants.getCatygoriesUrl());
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(Constants.getCatygoriesUrl());
		HttpResponse response = client.execute(httpGet);
		String result = null;
		if (response.getStatusLine().getStatusCode()==200) {
			result = EntityUtils.toString(response.getEntity());
          Log.i("@@@", result);
		}
//		URL url1 = new URL(Constants.getCatygoriesUrl());
//        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
//        conn.setConnectTimeout(30000);
//        conn.setReadTimeout(30000);
//        conn.setRequestMethod("GET");
//        String result = null;
//        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                InputStream is = conn.getInputStream();
//                result = new String(StreamTools.getByteArrayFromInputSteam(is));
//                Log.i("@@@", result);
//        }
        return result;
	}


	private void save2SdCard(InputStream is,String appName) throws IOException {
		File appFile = new File("/mnt/sdcard/eoesoso",appName);
		if (!appFile.exists()) {
			appFile.getParentFile().mkdirs();
			appFile.createNewFile();
			OutputStream os = new FileOutputStream(appFile);
			os.write(StreamTools.getByteArrayFromInputSteam(is));
			os.flush();
			os.close();
		}
	}
}
