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

    private final int mSpanCount;
    private final int mSpacing;
    private final int mEdgeSpacing;
    private final boolean isIncludeEdge;
    private final GridLayoutManager.SpanSizeLookup mSpanSizeLookup;

    public static class Builder {
        private int mSpanCount;
        private int mSpacing;
        private int mEdgeSpacing;
        private boolean isIncludeEdge;
        private GridLayoutManager.SpanSizeLookup mSpanSizeLookup;

        public Builder(int spanCount, int spacing) {
            mSpanCount = spanCount;
            mSpacing = spacing;
            mEdgeSpacing = spacing;
            isIncludeEdge = false;
            mSpanSizeLookup = new GridLayoutManager.DefaultSpanSizeLookup();
        }

        public Builder setEdgeSpacing(int edgeSpacing) {
            mEdgeSpacing = edgeSpacing;
            return this;
        }

        public Builder setIncludeEdge(boolean includeEdge) {
            isIncludeEdge = includeEdge;
            return this;
        }

        public Builder setSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
            mSpanSizeLookup = spanSizeLookup;
            return this;
        }

        public GridSpacingItemDecoration build() {
            return new GridSpacingItemDecoration(this);
        }
    }

    private GridSpacingItemDecoration(Builder builder) {
        mSpanCount = builder.mSpanCount;
        mSpacing = builder.mSpacing;
        mEdgeSpacing = builder.mEdgeSpacing;
        isIncludeEdge = builder.isIncludeEdge;
        mSpanSizeLookup = builder.mSpanSizeLookup;
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