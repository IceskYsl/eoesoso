package com.eoesou.tools;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class EoeUtility {

	private static final int MAX_NONCE = 0 + 40;

	public static final int AUTHLEVEL_BASE = 0;
	public static final int AUTHLEVEL_PUBLIC = AUTHLEVEL_BASE;
	public static final int AUTHLEVEL_CLIENT = AUTHLEVEL_BASE + 1;
	public static final int AUTHLEVEL_USER = AUTHLEVEL_BASE + 2;
	public static final int AUTHLEVEL_AUTO = AUTHLEVEL_BASE + 3;
	public static final int AUTHLEVEL_MAX = AUTHLEVEL_AUTO;

	private static char sHexDigits[] = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};



	public static String sUniquelyCode;
	private static boolean sInitialized = false;
	private static int sScreenHeight;
	private static int sScreenWidth;
	private static int sDensityDpi;
	private static String sProductBrand;
	private static String sProductId;
	private static String sProductModel;
	private static String sProductName;
	private static String sSDKVersion;
	private static String imsi;
	private static int sVersionCode;
	// add by gaotong
	public static boolean sCheckUpdate = false;
	// add by gaotong check network status
	public static boolean isHasNetWork = false;
    // add by gaotong is or not check isExist shortcut icon
	public static boolean isHasCheckShortcut = false;
	

	private EoeUtility() {
	}

	public static final String getUniquely(Context context) {
		if (TextUtils.isEmpty(sUniquelyCode)) {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			sUniquelyCode = tm.getDeviceId();
			if (TextUtils.isEmpty(sUniquelyCode)) {
				WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
				sUniquelyCode = wm.getConnectionInfo().getMacAddress();
			}
		}
		
		//used to get mac address on Android TV(tv will use ethernet card)
		try {
			if(sUniquelyCode == null) {
				sUniquelyCode = getRequiredUniquelyCode(getLocalMacAddress());
			}
		} catch(Exception e) {

		}
		return sUniquelyCode;
	}





	public static String hexString(byte[] source) {
		if (source == null || source.length <= 0) {
			return "";
		}

		final int size = source.length;
		final char str[] = new char[size * 2];
		int index = 0;
		byte b;
		for (int i = 0; i < size; i++) {
			b = source[i];
			str[index++] = sHexDigits[b >>> 4 & 0xf];
			str[index++] = sHexDigits[b & 0xf];
		}
		return new String(str);
	}

	
//	private static void getKey(Context context, Properties pro) {
//		try {
//			ZipFile file = new ZipFile(((ContextWrapper) context).getPackageResourcePath());
//			ZipEntry entry = file.getEntry(EXTRA_FILE_PATH);
//			
//			if (entry != null) {
//				InputStream is = file.getInputStream(entry);
//				pro.load(is);
//			}
//		} catch (IOException e) {
//			//ignore
//		}
//	}
	private static String getLocalMacAddress() {
		String macAddr = null;
		char[] buf = new char[1024];
		InputStreamReader isr = null;

		try {
			Process pp = Runtime.getRuntime().exec("busybox ifconfig eth0");
			isr = new InputStreamReader(pp.getInputStream());
			isr.read(buf);
			macAddr = new String(buf);
			int start = macAddr.indexOf("HWaddr") + 7;
			int end = start + 18;
			macAddr = macAddr.substring(start, end);
		} catch (Exception e) {
			macAddr = "Read Exception";
		}
		return macAddr;
	}

	private static String getRequiredUniquelyCode(String macAddress) {
		return macAddress.replaceAll(":", "");
	}
}
