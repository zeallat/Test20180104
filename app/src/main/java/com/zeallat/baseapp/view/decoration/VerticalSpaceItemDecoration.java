package com.zeallat.baseapp.view.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * VerticalSpaceItemDecoration.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-08-17.
 */
public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
    }
}