package com.zeallat.prndtest.search;

import android.support.annotation.Nullable;
import android.util.Log;

import com.zeallat.prndtest.data.model.Brand;
import com.zeallat.prndtest.data.model.PaginationInfo;
import com.zeallat.prndtest.data.model.Searchable;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.BrandRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;
    private Searchable.Type mType;
    private BrandRepository mBrandRepository = new BrandRepository();

    public SearchPresenter(SearchContract.View view, Searchable.Type type) {
        mView = view;
        mView.setPresenter(this);
        mType = type;
    }

    @Override
    public void onCreate() {
        switch (mType) {
            case BRAND:
                getBrands();
                break;
            case MODEL_GROUP:
                break;
            case MODEL:
                break;
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClickSearchItem(Searchable item) {
        Log.d("SearchPresenter", "item:" + item);
        switch (mType) {
            case BRAND:
                Brand brand = (Brand) item;
                Log.d("SearchPresenter", "brand:" + brand);
                break;
            case MODEL_GROUP:
                break;
            case MODEL:
                break;
        }
    }

    private void getBrands() {
        mBrandRepository.query(new BaseDataSource.GetDataCallback<Brand>() {
            @Override
            public void onDataLoaded(List<Brand> datas, @Nullable PaginationInfo paginationInfo) {
                List<Searchable> searchables = new ArrayList<>();
                for (Brand data : datas) searchables.add(data);
                mView.setSearchResult(searchables);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
