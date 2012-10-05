package com.eoesou.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
