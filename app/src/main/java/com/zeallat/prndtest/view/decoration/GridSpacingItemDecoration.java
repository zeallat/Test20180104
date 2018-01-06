package com.zeallat.prndtest.view.decoration;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * GridSpacingItemDecoration.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-27.
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private GridLayoutManager.SpanSizeLookup mSpanSizeLookup = new GridLayoutManager.DefaultSpanSizeLookup();

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        mSpanSizeLookup = spanSizeLookup;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int spanSize = mSpanSizeLookup.getSpanSize(position);
        int column = mSpanSizeLookup.getSpanIndex(position, spanCount);
        int spanGroupIndex = mSpanSizeLookup.getSpanGroupIndex(position, spanCount);

        boolean isFirstColumn = column == 0;
        boolean isLastColumn = column + spanSize == spanCount;
        boolean isTopGroup = spanGroupIndex == 0;

        if (isFirstColumn) {
            //Item at left edge
            if (includeEdge) outRect.left = spacing;
            outRect.right = isLastColumn ? (includeEdge ? spacing : 0) : spacing / 2;
        } else if (isLastColumn) {
            //Item at right edge
            outRect.left = spacing / 2;
            if (includeEdge) outRect.right = spacing;
        } else {
            //Item at middle
            outRect.left = spacing / 2;
            outRect.right = spacing / 2;
        }

        // Item at top edge
        if (isTopGroup && includeEdge) outRect.top = spacing;
        if (!isTopGroup && !includeEdge) outRect.top = spacing;

        // Item bottom
        if (includeEdge) outRect.bottom = spacing;
    }
}