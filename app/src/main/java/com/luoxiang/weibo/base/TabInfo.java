package com.luoxiang.weibo.base;

import com.luoxiang.weibo.R;
import com.luoxiang.weibo.fragment.HomeFragment;
import com.luoxiang.weibo.fragment.InfoFragment;
import com.luoxiang.weibo.fragment.MeFragment;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.base
 * className:	        TabInfo
 * author:	            Luoxiang
 * time:	            2016/9/7	13:40
 * desc:	            主页面的fragment对象枚举类
 */
public enum TabInfo {

    HOME("home" , "主页" , R.drawable.tab_icon_new , HomeFragment.class , "主页内容"),
    INFO("info" , "信息" ,R.drawable.tab_icon_explore , InfoFragment.class , "信息内容"),
    ME("me" , "我的" , R.drawable.tab_icon_me , MeFragment.class , "我的内容");

    String tag;
    String title;
    Class clazz;
    String arg;
    int resIcon;

    TabInfo(String tagName, String title, int resIcon,  Class clazz, String arg){
        this.tag = tagName;
        this.title = title;
        this.clazz = clazz;
        this.arg = arg;
        this.resIcon = resIcon;
    }

    public int getResIcon() {
        return resIcon;
    }

    public String getArg() {
        return arg;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }
}
