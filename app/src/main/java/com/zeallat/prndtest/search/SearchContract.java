package com.zeallat.prndtest.search;


import com.zeallat.prndtest.base.BasePresenter;
import com.zeallat.prndtest.base.BaseView;

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        SearchActivity.Type getType();
    }

    interface Presenter extends BasePresenter {

    }
}

