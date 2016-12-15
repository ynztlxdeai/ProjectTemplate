package com.luoxiang.weibo.base;

import android.app.Application;

import com.luoxiang.weibo.utils.UIUtil;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.base
 * className:	        APP
 * author:	            Luoxiang
 * time:	            2016/9/7	15:07
 * desc:	            全局配置类
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UIUtil.init(this);
    }
}
