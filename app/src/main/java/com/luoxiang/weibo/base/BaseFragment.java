package com.luoxiang.weibo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luoxiang.weibo.R;

import butterknife.ButterKnife;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.base
 * className:	        BaseFragment
 * author:	            Luoxiang
 * time:	            2016/9/7	13:51
 * desc:	            fragment的基类
 */
public abstract class BaseFragment extends Fragment{

    private View mParentView;

    private FragmentActivity mActivity;

    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;

    //标志位 fragment是否可见
    protected boolean isVisible;

    public abstract
    @LayoutRes
    int getLayoutResId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state)
    {
        if(getLayoutResId() != 0){
            mParentView = inflater.inflate(getLayoutResId(), container, false);
        }else {
            mParentView = getLayoutView();
        }
        mActivity = getSupportActivity();
        return mParentView;
    }

    /**
     * 获取布局显示
     * @return 显示的view
     */
    protected abstract View getLayoutView();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
    }

    public abstract void finishCreateView(Bundle state);

    @Override
    public void onAttach(Activity activity)
    {

        super.onAttach(activity);
        this.mActivity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach()
    {

        super.onDetach();
        this.mActivity = null;
    }

    public FragmentActivity getSupportActivity()
    {

        return super.getActivity();
    }

    /**
     * Fragment数据的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {

        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint())
        {
            isVisible = true;
            onVisible();
        } else
        {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible()
    {

        lazyLoad();
    }

    protected void lazyLoad() {}

    protected void onInvisible() {}

    protected void loadData() {}

    protected void showProgressBar() {}

    protected void hideProgressBar() {}

    protected void initRecyclerView() {}

    protected void initRefreshLayout() {}

    protected void finishTask() {}

    @SuppressWarnings("unchecked")
    public <T extends View> T $(int id)
    {

        return (T) mParentView.findViewById(id);
    }

    @Override
    public void startActivity(Intent intent) {
        if (mActivity == null){
            return;
        }
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (mActivity == null){
            return;
        }
        mActivity.startActivityForResult(intent , requestCode);
        mActivity.overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);
    }

}
