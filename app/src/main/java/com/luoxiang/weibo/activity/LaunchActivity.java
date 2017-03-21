package com.luoxiang.weibo.activity;

import android.content.Intent;

import com.luoxiang.weibo.R;
import com.luoxiang.weibo.base.BaseActivity;
import com.luoxiang.weibo.utils.UIUtil;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.activity
 * className:	        LaunchActivity
 * author:	            Luoxiang
 * time:	            2016/9/7	15:00
 * desc:	            启动页面
 */
public class LaunchActivity
        extends BaseActivity
{

    private static final String TAG = "LaunchActivity";

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        UIUtil.postDelayed(() -> {
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            finish();
        }, 3000);
       /* UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);*/

    }

    @Override
    protected void initView() {
        getToolBarX().hide();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_launch;
    }

}
