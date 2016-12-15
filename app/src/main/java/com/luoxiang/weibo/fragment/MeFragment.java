package com.luoxiang.weibo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luoxiang.weibo.base.BaseFragment;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.fragment
 * className:	        MeFragment
 * author:	            Luoxiang
 * time:	            2016/9/7	13:55
 * desc:	            TODO
 */
public class MeFragment extends BaseFragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {


        // 这行代码是获取到Activity那边传过来的参数 bundle
        Bundle bundle = getArguments();



        TextView tv = new TextView(getActivity());

        tv.setBackgroundColor(Color.parseColor("#33ff0000"));

        tv.setText("这是内容");


        if(bundle != null){
            tv.setText(bundle.getString("key"));
        }

        tv.setTextSize(20);

        tv.setGravity(Gravity.CENTER);


        return tv;
    }
}
