package com.zeallat.prndtest.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoJunLee on 2018-01-06.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    private List<T> mItems = new ArrayList<>();

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItem(T item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<T> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mItems.remove(index);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mItems.clear();
        notifyDataSetChanged();
    }

    protected T getItem(int index) {
        return mItems.get(index);
    }
}
