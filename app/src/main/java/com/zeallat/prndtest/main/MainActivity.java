package com.zeallat.prndtest.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseViewActivity;
import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.view.decoration.GridSpacingItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseViewActivity<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.textViewSearch) TextView mTextViewSearch;
    @BindView(R.id.containerSearch) LinearLayout mContainerSearch;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private MainCarRecyclerAdapter mCarRecyclerAdapter;
    private GridLayoutManager mGridLayoutManager;
    private static final int GRID_SPAN_COUNT = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        new MainPresenter(this);
        setContentView(R.layout.activity_main);
        initView();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setConfig(BaseActivityConfig config) {

    }

    @OnClick(R.id.containerSearch)
    public void onViewClicked() {
    }

    private void initView() {
        mCarRecyclerAdapter = new MainCarRecyclerAdapter();
        mGridLayoutManager = new GridLayoutManager(this, GRID_SPAN_COUNT);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return MainCarRecyclerAdapter.ViewType.valueOf(position).getSpanSize();
            }
        };
        mGridLayoutManager.setSpanSizeLookup(spanSizeLookup);
        mRecyclerView.addItemDecoration(
                new GridSpacingItemDecoration(GRID_SPAN_COUNT, SizeUtils.dp2px(8), SizeUtils.dp2px(16), true, spanSizeLookup));
        mRecyclerView.setAdapter(mCarRecyclerAdapter);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
    }

    @Override
    public void addCars(List<Car> cars) {
        mCarRecyclerAdapter.addItems(cars);
    }

    @Override
    public void setCars(List<Car> cars) {
        mCarRecyclerAdapter.setItems(cars);
    }
}



