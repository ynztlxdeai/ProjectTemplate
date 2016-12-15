package com.luoxiang.weibo.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.Toast;

import java.io.File;

/**
 * packageName:	    com.luoxiang.weibo.utils
 * className:	    UIUtil
 * author:	        Luoxiang
 * time:	        2016/9/7	15:09
 * desc:	        全局UI更新工具类
 *
 */

public class UIUtil {

    /**
     * 全局的上下文
     */
    public static Context mBaseContext;

    /**
     * 全局的handler
     */
    public static Handler mHandler;


    /**
     * 全局的初始化
     * @param application
     */
    public static void init(Application application) {
        mBaseContext = application;
        mHandler = new Handler();
    }
    private static Toast toast;

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                                   content,
                                   Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void post(Runnable task) {
        mHandler.post(task);
    }

    public static void postDelayed(Runnable task, long delayedTime) {
        mHandler.postDelayed(task, delayedTime);
    }

    public static void removeCallbacks(Runnable task) {
        mHandler.removeCallbacks(task);
    }

    public static Resources getResources() {
        return mBaseContext.getResources();
    }

    public static File getCacheFile() {
        return mBaseContext.getCacheDir();
    }

    public static String getPackageName() {
        return mBaseContext.getPackageName();
    }

    public static String getString(int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }

    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }
}
