package com.zeallat.prndtest.base;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoJunLee on 2018-01-07.
 */

public abstract class BasePagerAdapter<T> extends PagerAdapter {
    private List<T> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (mItems != null && !mItems.isEmpty()) {
            View view = getView(container, position, getItem(position));
            container.addView(view);
            return view;
        } else {
            return super.instantiateItem(container, position);
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    protected abstract View getView(@NonNull ViewGroup container, int position, T item);

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

    public T getItem(int position) {
        return mItems.get(position);
    }
}
