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
import com.zeallat.prndtest.main.MainActivity;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseViewActivity<SearchContract.Presenter> implements SearchContract.View {

    public static final String EXTRA_TYPE = "1cbec92e-8fad-4cbf-9320-ec965c5327a9";
    public static final String EXTRA_SEARCH_ID = "5de0e860-1537-479d-86c4-c52c82451932";

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private Searchable.Type mType = Searchable.Type.BRAND;
    private int mSearchId = -1;

    private SearchRecyclerAdapter mRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mType = (Searchable.Type) extras.getSerializable(EXTRA_TYPE);
            mSearchId = extras.getInt(EXTRA_SEARCH_ID, -1);
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

    @Override
    public void showModelGroupSearchPage(int brandId) {
        startSearchPage(Searchable.Type.MODEL_GROUP, brandId);
    }

    @Override
    public void showModelSearchPage(int modelGroupId) {
        startSearchPage(Searchable.Type.MODEL, modelGroupId);
    }

    private void startSearchPage(Searchable.Type type, int searchId) {
        Bundle extras = new Bundle();
        extras.putSerializable(SearchActivity.EXTRA_TYPE, type);
        extras.putInt(EXTRA_SEARCH_ID, searchId);
        startActivity(SearchActivity.class, extras);
    }

    @Override
    public int getSearchId() {
        return mSearchId;
    }

    @Override
    public void showModelSearchResultPage(int modelId, String modelName) {
        Bundle extras = new Bundle();
        extras.putInt(MainActivity.EXTRA_MODEL_ID, modelId);
        extras.putString(MainActivity.EXTRA_MODEL_NAME, modelName);
        startActivity(MainActivity.class, extras);
    }
}



