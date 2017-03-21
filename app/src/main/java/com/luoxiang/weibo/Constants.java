package com.luoxiang.weibo;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo
 * className:	        Constants
 * author:	            Luoxiang
 * time:	            2016/9/8	9:22
 * desc:	            adb shell monkey -p package  -v 10000
 *
 * 下面几条是关于水波纹的
 * android:background="?android:attr/selectableItemBackground"波纹有边界
 * android:background="?android:attr/selectableItemBackgroundBorderless"波纹超出边界
 *
 * android:colorControlHighlight：设置波纹颜色
 * android:colorAccent：设置checkbox等控件的选中颜色
 */
public interface Constants {
    String APP_KEY      = "531356236";		   // 应用的APP_KEY
    String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";// 应用的回调页
    String SCOPE = 							   // 应用申请的高级权限
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
}
