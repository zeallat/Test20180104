package com.zeallat.baseapp.view.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * SpaceItemDecoration.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-27.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int spanCount;

    public SpaceItemDecoration(int space, int spanCount) {
        this.space = space;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) < spanCount) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}
