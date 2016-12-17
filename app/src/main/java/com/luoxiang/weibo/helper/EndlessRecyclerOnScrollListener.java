package com.luoxiang.weibo.helper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
/**
 * packageName:	    com.luoxiang.weibo.helper
 * className:	    EndlessRecyclerOnScrollListener
 * author:	        Luoxiang
 * time:	        2016/12/17	14:38
 * desc:	        自定义RecylcerView上拉加载处理
 *
 * svnVersion:
 * upDateAuthor:    Vincent
 * upDate:          2016/12/17
 * upDateDesc:      TODO
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener
{

    private int previousTotal = 0;

    private boolean loading = true;

    private int currentPage = 1;

    private LinearLayoutManager mLinearLayoutManager;

    protected EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager)
    {

        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
    {

        super.onScrolled(recyclerView, dx, dy);

        if (dy == 0)
            return;

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int lastCompletelyVisiableItemPosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();

        if (loading)
        {
            if (totalItemCount > previousTotal)
            {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        if (!loading && (visibleItemCount > 0) && (lastCompletelyVisiableItemPosition >= totalItemCount - 1))
        {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }


    public void refresh()
    {

        loading = true;
        previousTotal = 0;
        currentPage = 1;
    }

    public abstract void onLoadMore(int currentPage);
}