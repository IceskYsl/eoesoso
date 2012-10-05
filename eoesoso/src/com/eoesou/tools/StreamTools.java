package com.eoesou.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {
	public static byte[] getByteArrayFromInputSteam(InputStream is) throws IOException {
		int len = 0;
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len=is.read(buffer))!=-1) {
			bos.write(buffer, 0, len);
		}
		is.close();
		bos.close();
		return bos.toByteArray();
	}
}
