package com.luoxiang.weibo.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * packageName:	    com.luoxiang.weibo.utils
 * className:	    KeyBoardUtil
 * author:	        Luoxiang
 * time:	        2016/12/21	10:38
 * desc:	        软键盘工具类
 *
 * svnVersion:
 * upDateAuthor:    Vincent
 * upDate:          2016/12/21
 * upDateDesc:      TODO
 */


public class KeyBoardUtil
{


    public static void openKeybord(EditText mEditText, Context mContext)
    {

        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    public static void closeKeybord(EditText mEditText, Context mContext)
    {

        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
