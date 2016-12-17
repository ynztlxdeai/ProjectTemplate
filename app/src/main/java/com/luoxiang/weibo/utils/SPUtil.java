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

	public void putInt(String key , int value){
		mEditor.putInt(key , value);
		mEditor.commit();
	}

	public int getInt(String key , int defValue){
		return mSharedPreferences.getInt(key , defValue);
	}

	public void putString(String key , String value){
		mEditor.putString(key , value);
		mEditor.commit();
	}

	public String  getString(String key , String defValue){
		return mSharedPreferences.getString(key , defValue);
	}

	public void putBoolean(String key , boolean value){
		mEditor.putBoolean(key , value);
		mEditor.commit();
	}

	public boolean  getString(String key , boolean defValue){
		return mSharedPreferences.getBoolean(key , defValue);
	}

	public boolean isLogin(){
		return mSharedPreferences.getBoolean(IS_LOGIN, false);
	}

}
