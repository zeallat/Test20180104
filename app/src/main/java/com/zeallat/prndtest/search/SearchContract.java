package com.zeallat.prndtest.search;


import com.zeallat.prndtest.base.BasePresenter;
import com.zeallat.prndtest.base.BaseView;
import com.zeallat.prndtest.data.model.Searchable;

import java.util.List;

/**
 * 검색 Contract
 */
public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void setSearchResult(List<Searchable> searchables);

        void showModelGroupSearchPage(int brandId);

        void showModelSearchPage(int modelGroupId);

        void showModelSearchResultPage(int modelId, String modelName);

        int getSearchId();

        void setNoItemViewVisible(boolean isVisible);
    }

    interface Presenter extends BasePresenter {
        void onClickSearchItem(Searchable item);
    }
}

