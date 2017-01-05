package com.luoxiang.weibo.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * packageName:	    com.luoxiang.weibo.utils
 * className:	    ShareUtil
 * author:	        Luoxiang
 * time:	        2016/12/23	10:13
 * desc:	        分享工具类
 *
 * svnVersion:
 * upDateAuthor:    Vincent
 * upDate:          2016/12/23
 * upDateDesc:      TODO
 */


public class ShareUtil
{

    /**
     *
     * @param url 网址
     * @param title 标题
     * @param content 内容描述
     * @param context 上下文
     */
    public static void shareLink(String url, String title, String content ,  Context context)
    {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT,content + url);
        context.startActivity(Intent.createChooser(intent, title));
    }

    /**
     *  ".stl", "application/vnd.ms-pkistl"
     * @param context
     * @param file
     */
    public static void shareFile(Context context , File file , String mime){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.putExtra(Intent.EXTRA_STREAM,
                       Uri.fromFile(file));
        share.setType(mime);//此处可发送多种文件
        context.startActivity(Intent.createChooser(share, "Share"));
    }
}
