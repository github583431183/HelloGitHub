package com.wjz.splashpointchange.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpTools {

	public static void setBoolean(Context context, String key, boolean value) {
		// ͨ�������ĵõ�һ�����������ʵ������
		SharedPreferences sp = context.getSharedPreferences(
				MyContains.CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		// ͨ�������ĵõ�һ�����������ʵ������
		SharedPreferences sp = context.getSharedPreferences(MyContains.CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
}
