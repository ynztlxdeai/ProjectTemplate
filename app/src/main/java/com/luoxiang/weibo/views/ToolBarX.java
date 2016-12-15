package com.luoxiang.weibo.views;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.widget.RelativeLayout;

import com.luoxiang.weibo.R;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.utils
 * className:	        ToolBarX
 * author:	            Luoxiang
 * time:	            2016/9/7	11:27
 * desc:	            toolbar的工具类
 */
public class ToolBarX {
    private ActionBar         mActionBar;
    private RelativeLayout    mBaseToolbarRl;
    private Toolbar           mToolbar;
    private AppCompatActivity mActivity;

    public ToolBarX(AppCompatActivity activity, Toolbar toolbar) {
        mActivity = activity;
        mToolbar = toolbar;
        mBaseToolbarRl = (RelativeLayout) mToolbar.findViewById(R.id.base_toolbar_rl);

        mActivity.setSupportActionBar(mToolbar);
        mActionBar = mActivity.getSupportActionBar();
    }

    /**
     * 隐藏
     */
    public void hide() {
        mActionBar.hide();
    }

    /**
     * 设置点击监听
     * @param listener 监听器
     * @return 返回本身
     */
    public ToolBarX setNavigationOnClickListener(View.OnClickListener listener) {
        mToolbar.setNavigationOnClickListener(listener);
        return this;
    }

    /**
     * 改变toolbar的图标
     * @param resId 图标资源
     * @return 返回本身
     */
    public ToolBarX setNavigationIcon(@DrawableRes int resId) {
        mToolbar.setNavigationIcon(resId);
        return this;
    }

    public ToolBarX setNavigationIcon(@Nullable Drawable icon) {
        mToolbar.setNavigationIcon(icon);
        return this;
    }

    /**
     * 设置主标题
     * @param title 标题名字
     * @return 返回本身
     */
    public ToolBarX setTitle(String title) {
        mActionBar.setTitle(title);
        return this;
    }

    /**
     * 设置副标题
     * @param title 副标题名字
     * @return 返回本身
     */
    public ToolBarX setSubtitle(String title) {
        mActionBar.setSubtitle(title);
        return this;
    }

    public ToolBarX setTitle(int titleRes) {
        mActionBar.setTitle(titleRes);
        return this;
    }

    public ToolBarX setSubtitle(int titleRes) {
        mActionBar.setSubtitle(titleRes);
        return this;
    }

    /**
     * 设置是否显示返回按钮
     * @param isShow 是否显示
     * @return 返回本身
     */
    public ToolBarX setDisplayHomeAsUpEnabled(boolean isShow) {
        mActionBar.setDisplayHomeAsUpEnabled(isShow);
        return this;

    }

    /**
     * 设置自定义的view
     *      添加的时候先移除掉所有的 防止重复添加
     * @param view 自定义的view
     * @return 返回本身
     */
    public ToolBarX setCustomView(View view) {
        mBaseToolbarRl.removeAllViews();
        mBaseToolbarRl.addView(view);
        return this;
    }

    /**
     * 设置是否显示title
     * @param showTitle true显示
     * @return 返回自己
     */
    public ToolBarX setDisplayShowTitleEnabled(boolean showTitle) {
        mActionBar.setDisplayShowTitleEnabled(showTitle);

        return this;
    }

    /**
     * 设置logo
     * @param resId logoID
     * @return 本身
     */
    public ToolBarX setLogo(int resId) {
        mToolbar.setLogo(resId);
        return this;
    }

    /**
     * 设置返回键位置图表 左上角
     * @param resId 资源ID
     * @return 返回本身
     */
    public ToolBarX setHomeAsUpIndicator(int resId) {
        mActionBar.setHomeAsUpIndicator(resId);

        return this;
    }

    /**
     * 加载一个菜单布局
     * @param resId 菜单布局
     * @return 本身
     */
    public ToolBarX inflateMenu(int resId) {
        mToolbar.inflateMenu(resId);

        return this;
    }

    /**
     * 设置一个菜单监听
     * @param listener 监听器
     * @return 本身
     */
    public ToolBarX setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener) {
        mToolbar.setOnMenuItemClickListener(listener);
        return this;
    }

    public ToolBarX createContextMenu(ContextMenu menu) {
        mToolbar.createContextMenu(menu);
        mToolbar.showContextMenu();
        return this;
    }


}