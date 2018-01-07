package com.zeallat.prndtest.search;


import com.zeallat.prndtest.base.BasePresenter;
import com.zeallat.prndtest.base.BaseView;
import com.zeallat.prndtest.data.model.Searchable;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void setSearchResult(List<Searchable> searchables);
    }

    interface Presenter extends BasePresenter {
        void onClickSearchItem(Searchable item);
    }
}

