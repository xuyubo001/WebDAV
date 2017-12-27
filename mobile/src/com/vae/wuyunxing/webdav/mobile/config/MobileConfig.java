package com.vae.wuyunxing.webdav.mobile.config;

import android.content.Context;
import android.content.res.AssetManager;

import com.vae.wuyunxing.webdav.library.log.MKLog;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MobileConfig {

	public static final String DEBUG                  = "DEBUG";

	private static final Properties mProps = new Properties();
	private static MobileConfig sInstance;

	public static void initialize(Context context) {
		InputStream in = null;
		try {
			AssetManager assetManager = context.getAssets();
			in = assetManager.open("mobile-configs.properties");
			mProps.load(in);
		} catch (IOException e) {
			MKLog.e(MobileConfig.class, e, "Connot open: %s", "mobile-configs.properties");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ignored) {
				}
			}
		}
	}

	public synchronized static MobileConfig getInstance() {
		if (sInstance == null) {
			sInstance = new MobileConfig();
		}
		return sInstance;
	}

	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public boolean getBoolean(String key, boolean defVal) {
		return Boolean.parseBoolean(mProps.getProperty(key, Boolean.toString(defVal)));
	}

	public int getInteger(String key) {
		return getInteger(key, 0);
	}

	public int getInteger(String key, int defVal) {
		return Integer.parseInt(mProps.getProperty(key, Integer.toString(defVal)));
	}

	public String getString(String key) {
		return getString(key, "");
	}

	public String getString(String key, String defVal) {
		return mProps.getProperty(key, defVal);
	}
}
