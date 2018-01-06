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

    private int mSpanCount;
    private int mSpacing;
    private int mEdgeSpacing;
    private boolean isIncludeEdge;
    private GridLayoutManager.SpanSizeLookup mSpanSizeLookup = new GridLayoutManager.DefaultSpanSizeLookup();

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean isIncludeEdge) {
        mSpanCount = spanCount;
        mSpacing = spacing;
        mEdgeSpacing = spacing;
        this.isIncludeEdge = isIncludeEdge;
    }

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean isIncludeEdge, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        mSpanCount = spanCount;
        mSpacing = spacing;
        mEdgeSpacing = spacing;
        this.isIncludeEdge = isIncludeEdge;
        mSpanSizeLookup = spanSizeLookup;
    }

    public GridSpacingItemDecoration(int spanCount, int spacing, int edgeSpacing, boolean isIncludeEdge, GridLayoutManager.SpanSizeLookup
            spanSizeLookup) {
        mSpanCount = spanCount;
        mSpacing = spacing;
        mEdgeSpacing = edgeSpacing;
        this.isIncludeEdge = isIncludeEdge;
        mSpanSizeLookup = spanSizeLookup;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int spanSize = mSpanSizeLookup.getSpanSize(position);
        int column = mSpanSizeLookup.getSpanIndex(position, mSpanCount);
        int spanGroupIndex = mSpanSizeLookup.getSpanGroupIndex(position, mSpanCount);

        boolean isFirstColumn = column == 0;
        boolean isLastColumn = column + spanSize == mSpanCount;
        boolean isTopGroup = spanGroupIndex == 0;

        if (isFirstColumn) {
            //Item at left edge
            if (isIncludeEdge) outRect.left = mEdgeSpacing;
            outRect.right = isLastColumn ? (isIncludeEdge ? mEdgeSpacing : 0) : mSpacing / 2;
        } else if (isLastColumn) {
            //Item at right edge
            outRect.left = mSpacing / 2;
            if (isIncludeEdge) outRect.right = mEdgeSpacing;
        } else {
            //Item at middle
            outRect.left = mSpacing / 2;
            outRect.right = mSpacing / 2;
        }

        // Item at top edge
        if (isTopGroup && isIncludeEdge) outRect.top = mEdgeSpacing;
        if (!isTopGroup && !isIncludeEdge) outRect.top = mSpacing;

        // Item bottom
        if (isIncludeEdge) outRect.bottom = mSpacing;
    }
}