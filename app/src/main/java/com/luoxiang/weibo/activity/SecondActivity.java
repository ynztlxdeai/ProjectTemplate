package com.luoxiang.weibo.activity;

import com.luoxiang.weibo.R;
import com.luoxiang.weibo.base.BaseActivity;
import com.luoxiang.weibo.views.ToolBarX;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.activity
 * className:	        SecondActivity
 * author:	            Luoxiang
 * time:	            2016/9/7	9:06
 * desc:	            测试用第二个activity
 */
public class SecondActivity extends BaseActivity {

    private ToolBarX mToolBarX;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mToolBarX.hide();
    }

    @Override
    protected void initView() {
        mToolBarX = getToolBarX();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_second;
    }
}
