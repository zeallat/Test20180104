package com.zeallat.prndtest.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseRecyclerViewAdapter;
import com.zeallat.prndtest.base.BaseRecyclerViewHolder;
import com.zeallat.prndtest.data.model.Searchable;

import butterknife.BindView;

/**
 * 검색 RecyclerAdapter
 */
public class SearchRecyclerAdapter extends BaseRecyclerViewAdapter<Searchable, SearchRecyclerAdapter.ItemViewHolder> {

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Searchable searchable = getItem(position);
        holder.getTextViewName().setText(searchable.getName());
    }

    class ItemViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.textViewName)
        TextView mTextViewName;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        public TextView getTextViewName() {
            return mTextViewName;
        }
    }
}
