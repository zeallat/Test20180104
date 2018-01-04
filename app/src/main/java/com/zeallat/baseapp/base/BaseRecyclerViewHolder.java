package com.zeallat.baseapp.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * BaseRecyclerViewHolder.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-13.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
