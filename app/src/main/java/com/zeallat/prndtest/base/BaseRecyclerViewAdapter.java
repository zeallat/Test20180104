package com.zeallat.prndtest.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoJunLee on 2018-01-06.
 */

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> mItems = new ArrayList<>();
    private OnItemClickListener<T> mOnClickListener;
    private OnItemLongClickListener<T> mOnLongClickListener;

    public interface OnItemClickListener<T> {
        void onClick(int position, T item);
    }

    public interface OnItemLongClickListener<T> {
        void onLongClick(int position, T item);
    }

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

    public void setOnClickListener(OnItemClickListener<T> onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnItemLongClickListener<T> onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setOnClickListener(view -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION || mOnClickListener == null) return;
            mOnClickListener.onClick(adapterPosition, getItem(adapterPosition));
        });
        holder.itemView.setOnLongClickListener(view -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION || mOnLongClickListener == null) return false;
            mOnLongClickListener.onLongClick(adapterPosition, getItem(adapterPosition));
            return true;
        });
    }
}
