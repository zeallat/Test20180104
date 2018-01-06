package com.zeallat.prndtest.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseViewActivity;

import butterknife.BindView;

public class SearchActivity extends BaseViewActivity<SearchContract.Presenter> implements SearchContract.View {

    public static final String EXTRA_TYPE = "1cbec92e-8fad-4cbf-9320-ec965c5327a9";

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    public enum Type {BRAND, MODEL_GROUP, MODEL}

    private Type mType = Type.BRAND;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getIntent().getExtras() != null) {
            mType = (Type) getIntent().getExtras().getSerializable(EXTRA_TYPE);
        }
        new SearchPresenter(this);
        setContentView(R.layout.activity_search);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setConfig(BaseActivityConfig config) {
        config.setToolbarEnable(true)
                .setToolbarTitle("브랜드 선택")
                .setToolbarNavigationType(NavigationType.BACK);
    }

    @Override
    public Type getType() {
        return mType;
    }
}



