package com.luoxiang.weibo.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

/**
 * packageName:	    com.luoxiang.weibo.utils
 * className:	    ClipboardUtil
 * author:	        Luoxiang
 * time:	        2016/12/26	13:42
 * desc:	        复制剪贴工具类
 *
 * svnVersion:
 * upDateAuthor:    Vincent
 * upDate:          2016/12/26
 * upDateDesc:      TODO
 */


public class ClipboardUtil
{

    private static ClipboardManager mClipboardManager;

    private static ClipboardManager mNewCliboardManager;


    private static boolean isNew()
    {

        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    private static void instance(Context context)
    {

        if (isNew())
        {
            if (mNewCliboardManager == null)
                mNewCliboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        } else
        {
            if (mClipboardManager == null)
                mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        }
    }

    /**
     * 为剪切板设置内容
     *
     * @param context
     * @param text
     */
    public static void setText(Context context, CharSequence text)
    {

        if (isNew())
        {
            instance(context);
            ClipData clip = ClipData.newPlainText("simple text", text);
            mNewCliboardManager.setPrimaryClip(clip);
        } else
        {
            instance(context);
            mClipboardManager.setText(text);
        }
    }

    /**
     * 获取剪切板的内容
     *
     * @param context
     * @return
     */
    public static CharSequence getText(Context context)
    {

        StringBuilder sb = new StringBuilder();
        if (isNew())
        {
            instance(context);
            if (!mNewCliboardManager.hasPrimaryClip())
            {
                return sb.toString();
            } else
            {
                ClipData clipData = (mNewCliboardManager).getPrimaryClip();
                int count = clipData.getItemCount();

                for (int i = 0; i < count; ++i)
                {

                    ClipData.Item item = clipData.getItemAt(i);
                    CharSequence str = item.coerceToText(context);
                    sb.append(str);
                }
            }
        } else
        {
            instance(context);
            sb.append(mClipboardManager.getText());
        }
        return sb.toString();
    }
}
