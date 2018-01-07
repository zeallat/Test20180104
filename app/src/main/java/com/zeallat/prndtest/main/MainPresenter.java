package com.zeallat.prndtest.main;

import android.support.annotation.Nullable;
import android.util.Log;

import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.model.PaginationInfo;
import com.zeallat.prndtest.data.model.specification.CarSpecificationByModelId;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.BaseSpecification;
import com.zeallat.prndtest.data.source.CarRepository;
import com.zeallat.prndtest.data.source.DefaultSpecification;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private CarRepository mCarRepository = new CarRepository();
    private int mCurrentPage = 1;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        getCars(true);
        String searchName = mView.getSearchModelName();
        mView.setSearchKeyword(searchName);
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

    private void getCars(boolean isResetRequired) {
        BaseSpecification specification;
        int searchModelId = mView.getSearhModelId();
        specification =
                (searchModelId < 0) ? new DefaultSpecification() : new CarSpecificationByModelId(searchModelId);
        specification.setPage(mCurrentPage);

        mCarRepository.query(specification, new BaseDataSource.GetDataCallback<Car>() {
            @Override
            public void onDataLoaded(List<Car> datas, @Nullable PaginationInfo paginationInfo) {
                if (isResetRequired) {
                    mView.setCars(datas);
                    mView.finishRefresh();
                } else {
                    mView.addCars(datas);
                }
            }

            @Override
            public void onDataNotAvailable() {
                Log.d("MainPresenter", "onDataNotAvailable");
            }
        });
    }

    @Override
    public void onRefreshCars() {
        mCurrentPage = 1;
        getCars(true);
    }

    @Override
    public void reachBottomOfCars() {
        mCurrentPage++;
        getCars(false);
    }

    @Override
    public void onClickSearchBox() {
        mView.showSearchPage();
    }

}
