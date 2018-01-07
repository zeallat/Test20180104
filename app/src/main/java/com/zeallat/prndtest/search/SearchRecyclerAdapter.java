package com.zeallat.prndtest.search;

import android.support.v7.widget.RecyclerView;
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
 * Created by HoJunLee on 2018-01-07.
 */

public class SearchRecyclerAdapter extends BaseRecyclerViewAdapter<Searchable> {

//    private Searchable.Type mType;

//    public SearchRecyclerAdapter(@NonNull Searchable.Type type) {
//        checkNotNull(type);
//        mType = type;
//    }

    public SearchRecyclerAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        Searchable searchable = getItem(position);
        viewHolder.getTextViewName().setText(searchable.getDisplayName());

//        switch (mType) {
//            case BRAND:
//                break;
//            case MODEL_GROUP:
//                break;
//            case MODEL:
//                break;
//        }
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
