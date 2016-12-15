package com.luoxiang.weibo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.luoxiang.weibo.R;
import com.luoxiang.weibo.base.BaseActivity;
import com.luoxiang.weibo.base.TabInfo;
import com.luoxiang.weibo.utils.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity
        extends BaseActivity
        implements TabHost.OnTabChangeListener
{

    @Bind(R.id.main_fl_container)
    FrameLayout     mMainFlContainer;
    @Bind(R.id.main_fragment_tabhost)
    FragmentTabHost mMainFragmentTabhost;

    int menuRes = R.menu.main_menu;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        initTabs();

    }

    private void initTabs() {
        mMainFragmentTabhost.setup(this , getSupportFragmentManager() , R.id.main_fl_container);
        TabInfo[] values = TabInfo.values();
        for (TabInfo info: values) {
            View            indicator = View.inflate(this, R.layout.tab_indicator, null);
            TextView        title     = (TextView) indicator.findViewById(R.id.tab_title);
            ImageView       icon      = (ImageView) indicator.findViewById(R.id.iv_icon);
            Drawable drawable = this.getResources().getDrawable(info.getResIcon());
            icon.setImageDrawable(drawable);

            title.setText(info.getTitle());

            mMainFragmentTabhost.addTab(
                    mMainFragmentTabhost.newTabSpec(info.getTag()).setIndicator(indicator),
                    info.getClazz(),
                    performBundle(info.getArg()));
        }
        mMainFragmentTabhost.setOnTabChangedListener(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    private Bundle performBundle(String msg){

        Bundle bundle = new Bundle();

        bundle.putString("key" , msg);

        return bundle;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuRes != -1){
            getMenuInflater().inflate(R.menu.main_menu , menu);
        }else {
            menu.clear();
        }
        return true;
    }

    @Override
    public void onTabChanged(String tabId) {
        Logger.e("onTabChanged: ", tabId );
        if (tabId.equals("home")){
            menuRes = R.menu.main_menu;
        }else {
            menuRes = -1;
        }
        invalidateOptionsMenu();
    }
}
