package com.luoxiang.weibo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
	private static SPUtil mInstance;
	private static SharedPreferences mSharedPreferences;
	private static SharedPreferences.Editor mEditor;
	private static final String SP_NAME = "config";
	private static final String IS_LOGIN = "IS_LOGIN";

	private SPUtil() {
	}

	public static SPUtil getInstance(Context context){
		if (mInstance == null){
			synchronized (SPUtil.class){
				if (mInstance == null){
					mInstance = new SPUtil();
					mSharedPreferences = context.getSharedPreferences(SP_NAME ,
																	  Activity.MODE_PRIVATE);
					mEditor = mSharedPreferences.edit();
				}
			}
		}
		return mInstance;
	}

	public boolean isLogin(){
		return mSharedPreferences.getBoolean(IS_LOGIN, false);
	}

}
