package com.zeallat.prndtest.car.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseRecyclerViewAdapter;
import com.zeallat.prndtest.base.BaseViewActivity;
import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.model.Searchable;
import com.zeallat.prndtest.search.SearchActivity;
import com.zeallat.prndtest.view.decoration.GridSpacingItemDecoration;
import com.zeallat.prndtest.view.listener.EndlessScrollListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CarListActivity extends BaseViewActivity<CarListContract.Presenter> implements CarListContract.View {

    public static final String EXTRA_MODEL_ID = "ebf579a7-11af-452b-b1d5-32045c0fe3d1";
    public static final String EXTRA_MODEL_NAME = "acd00022-b4f1-44d5-9046-feb173e795e6";

    @BindView(R.id.textViewSearch) TextView mTextViewSearch;
    @BindView(R.id.containerSearch) LinearLayout mContainerSearch;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

    private CarRecyclerAdapter mCarRecyclerAdapter;
    private GridLayoutManager mGridLayoutManager;
    private static final int GRID_SPAN_COUNT = 2;

    private int mSearchModelId = -1;
    private String mSearchModelName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mSearchModelId = extras.getInt(EXTRA_MODEL_ID, -1);
            mSearchModelName = extras.getString(EXTRA_MODEL_NAME, "");
        }
        new CarPresenter(this);
        setContentView(R.layout.activity_main);
        initView();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setConfig(BaseActivityConfig config) {

    }

    @OnClick(R.id.containerSearch)
    public void onViewClicked() {
        mPresenter.onClickSearchBox();
    }

    private void initView() {
        mCarRecyclerAdapter = new CarRecyclerAdapter();
        mGridLayoutManager = new GridLayoutManager(this, GRID_SPAN_COUNT);

        //Rule for calculate span size
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return CarRecyclerAdapter.ViewType.valueOf(position).getSpanSize();
            }
        };
        mGridLayoutManager.setSpanSizeLookup(spanSizeLookup);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration.Builder(GRID_SPAN_COUNT, SizeUtils.dp2px(8))
                .setEdgeSpacing(SizeUtils.dp2px(16))
                .setIncludeEdge(true)
                .setSpanSizeLookup(spanSizeLookup)
                .build());
        mRecyclerView.setAdapter(mCarRecyclerAdapter);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.addOnScrollListener(new EndlessScrollListener(mGridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mPresenter.reachBottomOfCars();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.onRefreshCars());
        mCarRecyclerAdapter.setOnClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<Car>() {
            @Override
            public void onClick(int position, Car item) {

            }
        });
    }

    @Override
    public void finishRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addCars(List<Car> cars) {
        mCarRecyclerAdapter.addItems(cars);
    }

    @Override
    public void setCars(List<Car> cars) {
        mCarRecyclerAdapter.setItems(cars);
    }

    @Override
    public void showSearchPage() {
        Bundle extras = new Bundle();
        extras.putSerializable(SearchActivity.EXTRA_TYPE, Searchable.Type.BRAND);
        startActivity(SearchActivity.class, extras);
    }

    @Override
    public int getSearhModelId() {
        return mSearchModelId;
    }

    @Override
    public String getSearchModelName() {
        return mSearchModelName;
    }

    @Override
    public void setSearchKeyword(String searchKeyword) {
        mTextViewSearch.setText(searchKeyword);
    }
}



