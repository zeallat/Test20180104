package com.zeallat.prndtest.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseViewActivity;
import com.zeallat.prndtest.car.list.CarListActivity;
import com.zeallat.prndtest.data.model.Searchable;

import java.util.List;

import butterknife.BindView;

/**
 * 검색 액티비티
 */
public class SearchActivity extends BaseViewActivity<SearchContract.Presenter> implements SearchContract.View {

    public static final String EXTRA_TYPE = "1cbec92e-8fad-4cbf-9320-ec965c5327a9";
    public static final String EXTRA_SEARCH_ID = "5de0e860-1537-479d-86c4-c52c82451932";

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.containerNoItem) View mContainerNoItem;

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
                config.setToolbarTitle(R.string.search_title_brand);
                break;
            case MODEL_GROUP:
                config.setToolbarTitle(R.string.search_title_model_group);
                break;
            case MODEL:
                config.setToolbarTitle(R.string.search_title_model);
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
        extras.putInt(CarListActivity.EXTRA_MODEL_ID, modelId);
        extras.putString(CarListActivity.EXTRA_MODEL_NAME, modelName);
        startActivity(CarListActivity.class, extras);
    }

    @Override
    public void setNoItemViewVisible(boolean isVisible) {
        mContainerNoItem.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}



