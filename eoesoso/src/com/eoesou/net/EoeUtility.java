
package com.eoemobile.netmarket.utility;

import com.eoemobile.netmarket.MarketDefine;
import com.eoemobile.netmarket.MarketMethod;
import com.eoemobile.netmarket.VersionDefine;
import com.eoemobile.netmarket.cache.NetworkHelpers;
import com.eoemobile.netmarket.data.Accounts;

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
	private static final String API_KEY;
	private static final String API_SECRET;

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

	private static final String LABEL_APK_KEY = "api_key";
	private static final String LABEL_NONCE = "nonce";
	private static final String LABEL_TIMESTAMP = "timestamp";
	private static final String LABEL_API_SIG = "api_sig";

	private static final String LABEL_USER_ID = "user_id";
	private static final String LABEL_TOKEN = "token";

	private static final String LABEL_HEIGHT = "h";
	private static final String LABEL_WIDE = "w";
	private static final String LABEL_DPI = "dpi";
	private static final String LABEL_SDK = "sdk";
	private static final String LABEL_BRAND = "brand";
	private static final String LABEL_PRODUCT_ID = "product_id";
	private static final String LABEL_PRODUCT_MODEL = "model";
	private static final String LABEL_PRODUCT_NAME = "product";
	private static final String LABEL_LOCALE = "locale";
	private static final String LABEL_VERSION_CODE = "version_code";
	private static final String LABEL_UNIQUELY_CODE = "uniquely_code";
	private static final String LABEL_IMSI = "imsi";
	private static final String CONFIG_FILENAME = "market.cfg";
	private static final String LABEL_BUINESS = "business";
	private static final String LABEL_NETWORK = "network";
	private static final String LABEL_OPERATOR = "operator";
	private static final String LABEL_SOURCE = "source";
	
	private static final String LABEL_PACKAGENAME = "packagename";
	private static final String LABEL_APPID = "item_id";
	
	private final static String EXTRA_FILE_PATH = "META-INF/market.cfg";

	private static final SecureRandom sRandom = new SecureRandom();
	private static final StringBuilder sStringBuilder = new StringBuilder();
	private static final Properties sProperties = new Properties();

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
	
	static {
		switch (MarketDefine.VERSION_VENDER_TYPE) {
			case VersionDefine.VENDER_TYPE_EOEMARKET:
				API_KEY = "MC45ODAzMTgwMCAxMzM1ND";
				API_SECRET = "TUM0NU9EQXpNVGd3TUNBeE16TTFORE1DNDVPREF6TV";
				break;
			case VersionDefine.VENDER_TYPE_TIANYU_5901:
				API_KEY = "htFTK6KgS2pVlcTZ0GUww";
				API_SECRET = "2SsIgE5X6YVOsRiUNka6t9xQFlnY9d3tkxqoRDgo8hc";
				break;
			case VersionDefine.VENDER_TYPE_TIANYU_5902:
				API_KEY = "ZpbOe2oU48XFgVjMfh8g";
				API_SECRET = "IcpZQZ8swWDGgB0DEqVjMcv0xINxzhMQ3A0TuOOaw";
				break;
			case VersionDefine.VENDER_TYPE_TIANYU_5905:
				API_KEY = "4CZ6E2DRtXykmkg0bYnbWw";
				API_SECRET = "FyzDSZXyntKwoOG1n2szK8i2M05erDaBJNKJOQS4do";
				break;
			case VersionDefine.VENDER_TYPE_TIANYU_5906:
				API_KEY = "AfSunNQJMXZ36CpR3cCw";
				API_SECRET = "7CKzNEdGqrJimYCgKZhgd3MN22WrpIzkCyKf0Ag0";
				break;
			case VersionDefine.VENDER_TYPE_TIANYU_5908:
				API_KEY = "Q3frkr1inzxXwuxat9tCwQ";
				API_SECRET = "PPd6Ez2rgw9VdqJff72t6uPzgzDCHuy4oeBZ0QabP8";
				break;
			case VersionDefine.VENDER_TYPE_TIANYU_7801:
				API_KEY = "jOUK8lfbCNjo864t4abOQ";
				API_SECRET = "18SrHo1ZbLB7TCCv4mBjWPMEspMqokJXnVIkyZZLZk";
				break;
			case VersionDefine.VENDER_TYPE_TIANYU_7802:
				API_KEY = "yxb1ZUW3zyA59en1VLLqSA";
				API_SECRET = "ZZkbFX65oD0rBY3VrpAxx0SASj2C0xAOObn2VsiLeBw";
				break;
			case VersionDefine.VENDER_TYPE_TIANYU_W616:
				API_KEY = "kH6IGcH5pYFhmMPbye8Vqw";
				API_SECRET = "KD2NE0p8bEWswZT4BQfOpmdnj0Wou8PirmK3Mh6g";
				break;
			case VersionDefine.VENDER_TYPE_ALIYUN:
				API_KEY = "DytWcBuzAbhwaqqJk28YA";
				API_SECRET = "xutejuw9tvYs8a1tacQ4Gxyr9nv3rcRzKHDSael3xI";
				break;
			default:
				API_KEY = "rna7O9yfUoV22CdA1SgUw";
				API_SECRET = "LsnSbdtCbnfhpr2c6miJvYg89lT0IHtUVcfhzQhk";
				break;
		}

		sRandom.setSeed(sRandom.generateSeed(MAX_NONCE / 2));
	}

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

	public static final String encode(String source) {
		return DES.encodes(API_SECRET, source);
	}

	private static String genAPIsig(long timestamp, String nonce) {
		// api_sig = MD5("api_key"+@api_key+"nonce"+@nonce+"timestamp"+@timestamp)
		String result = null;
		StringBuilder builder = sStringBuilder;
		synchronized (builder) {
			builder.append(LABEL_APK_KEY).append(API_KEY);
			builder.append(LABEL_NONCE).append(nonce);
			builder.append(LABEL_TIMESTAMP).append(timestamp);
			builder.append(API_SECRET);
			result = MD5.encode(builder.toString());
			builder.delete(0, builder.length());
		}
		return result;
	}

	private static String genNonce() {
		byte[] bytes = new byte[MAX_NONCE / 2];
		sRandom.nextBytes(bytes);
		return hexString(bytes);
	}
	
	public static String decodeLoger(Context context,String base,int authLevel,String packageName,String id){
		if (base == null) {
			return null;
		}

		if (authLevel < AUTHLEVEL_BASE) {
			authLevel = AUTHLEVEL_BASE;
		}
		if (authLevel > AUTHLEVEL_MAX) {
			authLevel = AUTHLEVEL_MAX;
		}

		if (authLevel == AUTHLEVEL_AUTO) {
			authLevel = Accounts.getInstance(context).isLogin() ? AUTHLEVEL_USER : AUTHLEVEL_CLIENT;
		}

		Uri baseUri = Uri.parse(base);
		Uri.Builder builder = baseUri.buildUpon();

		long timestamp = System.currentTimeMillis();
		if (authLevel > AUTHLEVEL_PUBLIC) {
			builder = appendOprationInfo(context, builder);
			builder = appendCertification(builder, timestamp);
		}

		if (authLevel > AUTHLEVEL_CLIENT) {
			builder = appendUserInfo(context, builder, timestamp);
		}
		
		builder = appendBusinessChannel(builder);
		builder = appendPackageId(context,builder,packageName,id);
//		builder = appendNetworkWithSubject(context, builder, subject);

		return builder.toString();
	}

	public static String decodeUrl(Context context, String base, int authLevel) {
		return appendEoeUriParamater(context, base, authLevel, "");
	}
	
	public static String decodeUrlWithSubject(Context context, String base, int authLevel, String subject) {
		return appendEoeUriParamater(context, base, authLevel, subject);
	}

	private static String appendEoeUriParamater(Context context, String uri, int authLevel, String subject) {
		if (uri == null) {
			return null;
		}

		if (authLevel < AUTHLEVEL_BASE) {
			authLevel = AUTHLEVEL_BASE;
		}
		if (authLevel > AUTHLEVEL_MAX) {
			authLevel = AUTHLEVEL_MAX;
		}

		if (authLevel == AUTHLEVEL_AUTO) {
			authLevel = Accounts.getInstance(context).isLogin() ? AUTHLEVEL_USER : AUTHLEVEL_CLIENT;
		}

		Uri baseUri = Uri.parse(uri);
		Uri.Builder builder = baseUri.buildUpon();

		long timestamp = System.currentTimeMillis();
		if (authLevel > AUTHLEVEL_PUBLIC) {
			builder = appendOprationInfo(context, builder);
			builder = appendCertification(builder, timestamp);
		}

		if (authLevel > AUTHLEVEL_CLIENT) {
			builder = appendUserInfo(context, builder, timestamp);
		}
		
		builder = appendBusinessChannel(builder);
		builder = appendNetworkWithSubject(context, builder, subject);

		return builder.toString();
	}

	private static Uri.Builder appendUserInfo(Context context, Uri.Builder builder, long timestamp) {
		if (builder == null) {
			return null;
		}

		Accounts account = Accounts.getInstance(context);
		final String userId = account.getLatestUserID();
		final String token = account.getToken(timestamp);

		builder = builder.appendQueryParameter(LABEL_USER_ID, userId);
		builder = builder.appendQueryParameter(LABEL_TOKEN, token);
		return builder;
	}

	private static Uri.Builder appendCertification(Uri.Builder builder, long timestamp) {
		if (builder == null) {
			return null;
		}

		String nonce = genNonce();
		String api_sig = genAPIsig(timestamp, nonce);

		builder = builder.appendQueryParameter(LABEL_APK_KEY, API_KEY);
		builder = builder.appendQueryParameter(LABEL_NONCE, nonce);
		builder = builder.appendQueryParameter(LABEL_TIMESTAMP, String.valueOf(timestamp));
		builder = builder.appendQueryParameter(LABEL_API_SIG, api_sig);

		return builder;
	}

	private static Uri.Builder appendOprationInfo(Context context, Uri.Builder builder) {
		String strKey = null;
		String strValue = null;
		if (builder == null) {
			return null;
		}

		if (!sInitialized) {
			WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			Display d = wm.getDefaultDisplay();
			int h = d.getHeight();
			int w = d.getWidth();
			int o = d.getOrientation();
			sScreenHeight = o == 0 ? h : w;
			sScreenWidth = o == 0 ? w : h;
			
			TelephonyManager tm =(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			imsi=tm.getSubscriberId();

			DisplayMetrics metrics = new DisplayMetrics();
			metrics.setToDefaults();
			sDensityDpi = Math.round((metrics.xdpi + metrics.ydpi) / 2);

			sSDKVersion = android.os.Build.VERSION.SDK;
			sProductBrand = android.os.Build.BRAND;
			sProductId = android.os.Build.ID;
			sProductModel = android.os.Build.MODEL;
			sProductName = android.os.Build.PRODUCT;

			PackageManager packageManager = context.getPackageManager();
			try {
				PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
				sVersionCode = info.versionCode;
			} catch (NameNotFoundException e) {
				sVersionCode = MarketMethod.getVersionCode(context);
			}

			getKey(context, sProperties);
			sInitialized = true;
		}

		String uniquely_code = getUniquely(context);
		Locale locale = context.getResources().getConfiguration().locale;

		builder = builder.appendQueryParameter(LABEL_HEIGHT, String.valueOf(sScreenHeight));
		builder = builder.appendQueryParameter(LABEL_WIDE, String.valueOf(sScreenWidth));
		builder = builder.appendQueryParameter(LABEL_DPI, String.valueOf(sDensityDpi));
		builder = builder.appendQueryParameter(LABEL_SDK, sSDKVersion);
		builder = builder.appendQueryParameter(LABEL_BRAND, sProductBrand);
		builder = builder.appendQueryParameter(LABEL_PRODUCT_ID, sProductId);
		builder = builder.appendQueryParameter(LABEL_PRODUCT_MODEL, sProductModel);
		builder = builder.appendQueryParameter(LABEL_PRODUCT_NAME, sProductName);
		builder = builder.appendQueryParameter(LABEL_LOCALE, String.valueOf(locale));
		builder = builder.appendQueryParameter(LABEL_VERSION_CODE, String.valueOf(sVersionCode));
		builder = builder.appendQueryParameter(LABEL_UNIQUELY_CODE, uniquely_code); 
		builder = builder.appendQueryParameter(LABEL_IMSI, imsi);

		Enumeration<?> enu = sProperties.propertyNames();
		while (enu.hasMoreElements()) {
			strKey = (String) enu.nextElement();
			strValue = sProperties.getProperty(strKey);
			if (strKey != null && strValue != null) {
				builder = builder.appendQueryParameter(strKey, strValue);
			}
		}
		return builder;
	}
	
	private static Uri.Builder appendBusinessChannel(Uri.Builder builder) {
		if (builder == null) return null;
		builder = builder.appendQueryParameter(LABEL_BUINESS, MarketDefine.VERSION_BUSINESS_CHANNEL);
		return builder;
	}
	
	private static Uri.Builder appendNetworkWithSubject(Context context, Uri.Builder builder, String subject) {
		if (context == null || builder == null) return null;
		
		String network = NetworkHelpers.queryPreferedNetworkName(context);
		if (!TextUtils.isEmpty(network)) {
			builder = builder.appendQueryParameter(LABEL_NETWORK, network);
		}
		
		String operator = NetworkHelpers.queryPreferedOperatorName(context);
		if (!TextUtils.isEmpty(operator)) {
			builder = builder.appendQueryParameter(LABEL_OPERATOR, operator);
		}
		
		if (!TextUtils.isEmpty(subject)) {
			builder = builder.appendQueryParameter(LABEL_SOURCE, subject);
		}
		
		return builder;
	}
	
	private static Uri.Builder appendPackageId(Context context,Uri.Builder builder,String packageName,String id){
		
		if (!TextUtils.isEmpty(packageName)) {
			builder = builder.appendQueryParameter(LABEL_PACKAGENAME, packageName);
		}
		if (!TextUtils.isEmpty(id)) {
			builder = builder.appendQueryParameter(LABEL_APPID, id);
		}
		
		return builder;
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

	private static void getKey(Context context, Properties pro) {
		try {
			pro.load(context.getAssets().open(CONFIG_FILENAME));
		} catch (IOException e) {
			//ignore
		}
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
