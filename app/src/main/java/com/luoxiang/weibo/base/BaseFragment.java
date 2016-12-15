package com.luoxiang.weibo.base;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.luoxiang.weibo.R;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.base
 * className:	        BaseFragment
 * author:	            Luoxiang
 * time:	            2016/9/7	13:51
 * desc:	            fragment的基类
 */
public class BaseFragment extends Fragment {
    @Override
    public void startActivity(Intent intent) {
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        getActivity().startActivityForResult(intent , requestCode);
        getActivity().overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);
    }
}
