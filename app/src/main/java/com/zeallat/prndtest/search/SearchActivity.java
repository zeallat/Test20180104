package com.zeallat.prndtest.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseViewActivity;
import com.zeallat.prndtest.data.model.Searchable;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseViewActivity<SearchContract.Presenter> implements SearchContract.View {

    public static final String EXTRA_TYPE = "1cbec92e-8fad-4cbf-9320-ec965c5327a9";

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private Searchable.Type mType = Searchable.Type.BRAND;

    private SearchRecyclerAdapter mRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getIntent().getExtras() != null) {
            mType = (Searchable.Type) getIntent().getExtras().getSerializable(EXTRA_TYPE);
        }
        new SearchPresenter(this, mType);
        setContentView(R.layout.activity_search);
        initView();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setConfig(BaseActivityConfig config) {
        config.setToolbarEnable(true).setToolbarNavigationType(NavigationType.BACK);
        switch (mType) {
            case BRAND:
                config.setToolbarTitle("브랜드 선택");
                break;
            case MODEL_GROUP:
                config.setToolbarTitle("차종 선택");
                break;
            case MODEL:
                config.setToolbarTitle("모델 선택");
                break;
        }
    }

    private void initView() {
        mRecyclerAdapter = new SearchRecyclerAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerAdapter.setOnClickListener((position, item) -> mPresenter.onClickSearchItem(item));
    }

    @Override
    public void setSearchResult(List<Searchable> searchables) {
        mRecyclerAdapter.setItems(searchables);
    }
}



