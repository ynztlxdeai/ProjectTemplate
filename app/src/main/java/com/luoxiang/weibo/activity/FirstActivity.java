package com.luoxiang.weibo.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.luoxiang.weibo.R;
import com.luoxiang.weibo.base.BaseActivity;
import com.luoxiang.weibo.views.ToolBarX;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.activity
 * className:	        FirstActivity
 * author:	            Luoxiang
 * time:	            2016/9/7	9:05
 * desc:	            测试用第一个activity
 */
public class FirstActivity
        extends BaseActivity
{


    @Bind(R.id.first)
    TextView mFirst;
    @Bind(R.id.first_button)
    Button   mFirstButton;

    private ToolBarX mToolBarX;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mToolBarX.setTitle("first title").setSubtitle("sub title").setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mToolBarX = getToolBarX();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_first;
    }


    @OnClick(R.id.first_button)
    public void onClick() {
        startActivity(new Intent(this , SecondActivity.class));
    }
}
