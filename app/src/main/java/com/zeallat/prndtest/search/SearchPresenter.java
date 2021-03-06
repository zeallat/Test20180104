package com.zeallat.prndtest.search;

import android.support.annotation.Nullable;

import com.zeallat.prndtest.data.model.Brand;
import com.zeallat.prndtest.data.model.ModelGroup;
import com.zeallat.prndtest.data.model.PaginationInfo;
import com.zeallat.prndtest.data.model.Searchable;
import com.zeallat.prndtest.data.model.specification.BrandSpecificationById;
import com.zeallat.prndtest.data.model.specification.ModelGroupSpecificationById;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.BrandRepository;
import com.zeallat.prndtest.data.source.ModelGroupRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 검색 프레젠터
 */
public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;
    private Searchable.Type mType;
    private BrandRepository mBrandRepository = new BrandRepository();
    private ModelGroupRepository mModelGroupRepository = new ModelGroupRepository();

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
                getModelGroups();
                break;
            case MODEL:
                getModels();
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
        switch (mType) {
            case BRAND:
                mView.showModelGroupSearchPage(item.getId());
                break;
            case MODEL_GROUP:
                mView.showModelSearchPage(item.getId());
                break;
            case MODEL:
                mView.showModelSearchResultPage(item.getId(), item.getName());
                break;
        }
    }

    /**
     * 브랜드 목록 조회
     */
    private void getBrands() {
        mBrandRepository.query(new BaseDataSource.GetDataCallback<Brand>() {
            @Override
            public void onDataLoaded(List<Brand> datas, @Nullable PaginationInfo paginationInfo) {
                if (mView.isDestroyed()) return;
                mView.setNoItemViewVisible(false);
                List<Searchable> searchables = new ArrayList<>();
                searchables.addAll(datas);
                mView.setSearchResult(searchables);
            }

            @Override
            public void onDataNotAvailable() {
                mView.setNoItemViewVisible(true);
            }
        });
    }

    /**
     * 모델그룹 목록 조회
     */
    private void getModelGroups() {
        int brandId = mView.getSearchId();
        if (brandId < 0) return;
        mBrandRepository.query(new BrandSpecificationById(brandId), new BaseDataSource.GetDataCallback<Brand>() {
            @Override
            public void onDataLoaded(List<Brand> datas, @Nullable PaginationInfo paginationInfo) {
                if (mView.isDestroyed()) return;
                mView.setNoItemViewVisible(false);
                if (datas.size() > 0) {
                    List<Searchable> searchables = new ArrayList<>();
                    searchables.addAll(datas.get(0).getModelGroups());
                    mView.setSearchResult(searchables);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mView.setNoItemViewVisible(true);
            }
        });
    }

    /**
     * 모델 목록 조회
     */
    private void getModels() {
        int modelGroupId = mView.getSearchId();
        if (modelGroupId < 0) return;
        mModelGroupRepository.query(new ModelGroupSpecificationById(modelGroupId), new BaseDataSource.GetDataCallback<ModelGroup>() {
            @Override
            public void onDataLoaded(List<ModelGroup> datas, @Nullable PaginationInfo paginationInfo) {
                if (mView.isDestroyed()) return;
                mView.setNoItemViewVisible(false);
                if (datas.size() > 0) {
                    List<Searchable> searchables = new ArrayList<>();
                    searchables.addAll(datas.get(0).getModels());
                    mView.setSearchResult(searchables);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mView.setNoItemViewVisible(true);
            }
        });
    }

}
