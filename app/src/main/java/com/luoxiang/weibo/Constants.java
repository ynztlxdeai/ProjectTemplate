package com.luoxiang.weibo;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo
 * className:	        Constants
 * author:	            Luoxiang
 * time:	            2016/9/8	9:22
 * desc:	            adb shell monkey -p package  -v 10000
 */
public interface Constants {
    String APP_KEY      = "531356236";		   // 应用的APP_KEY
    String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";// 应用的回调页
    String SCOPE = 							   // 应用申请的高级权限
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
}
