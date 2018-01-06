package com.zeallat.prndtest.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zeallat.prndtest.base.BaseRecyclerViewAdapter;
import com.zeallat.prndtest.base.BaseRecyclerViewHolder;
import com.zeallat.prndtest.data.model.Car;

/**
 * Created by HoJunLee on 2018-01-06.
 */

public class MainCarRecyclerAdapter extends BaseRecyclerViewAdapter<Car> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class VerticalItemViewHolder extends BaseRecyclerViewHolder {

        public VerticalItemViewHolder(View itemView) {
            super(itemView);
        }
    }

}
