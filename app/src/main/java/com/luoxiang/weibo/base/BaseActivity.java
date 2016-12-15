package com.luoxiang.weibo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.luoxiang.weibo.R;
import com.luoxiang.weibo.views.ToolBarX;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.base
 * className:	        BaseActivity
 * author:	            Luoxiang
 * time:	            2016/9/7	8:55
 * desc:	            全局activity的基类
 */
public abstract class BaseActivity
        extends AppCompatActivity
{

    private static Toast mToast;

    private RelativeLayout mBaseRlContent;
    private Toolbar mToolbar;
    private ToolBarX mToolBarX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mBaseRlContent  = (RelativeLayout) findViewById(R.id.base_rl_content);
        mToolbar = (Toolbar) findViewById(R.id.base_toolbar);
        View    view    = getLayoutInflater().inflate(getLayoutID(), mBaseRlContent , false);
        mBaseRlContent.addView(view);
        /**
         * toolbar的工具类
         */
        mToolBarX = new ToolBarX(this , mToolbar);


        initView();
        initData();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    /**
     * 获取内容的布局
     * @return 返回布局ID
     */
    protected abstract int getLayoutID();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        /**
         * 编写进入的动画和出去的动画
         */
        overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_in_left_right, R.anim.anim_out_left_right);
    }

    /**
     * toast提示
     * @param content 提示的内容
     */
    public static void showToast(Context context, String content) {
        if (mToast == null) {
            mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }

    /**
     * 获得一个toolbar的工具类
     * @return 返回工具类
     */
    public ToolBarX getToolBarX(){
        if (mToolBarX == null){
            mToolBarX = new ToolBarX(this , mToolbar);
        }
        return mToolBarX;
    }
}
