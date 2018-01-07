package com.zeallat.prndtest.car.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseViewActivity;
import com.zeallat.prndtest.view.HTMLTextView;

import java.util.List;

import butterknife.BindView;

public class CarDetailActivity extends BaseViewActivity<CarDetailContract.Presenter> implements CarDetailContract.View {

    @BindView(R.id.imageViewPager) ViewPager mImageViewPager;
    @BindView(R.id.htmlTextViewOriginalPrice) HTMLTextView mHtmlTextViewOriginalPrice;
    @BindView(R.id.textViewPrice) TextView mTextViewPrice;
    @BindView(R.id.textViewStatus) TextView mTextViewStatus;

    public static final String EXTRA_CAR_ID = "556b8b3f-eb7d-420b-8364-fa7000f48437";

    private int mCarId;

    private CarImagePagerAdapter mCarImagePagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCarId = extras.getInt(EXTRA_CAR_ID, -1);
        }
        new CarDetailPresenter(this, mCarId);
        setContentView(R.layout.activity_car_detail);
        initView();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setConfig(BaseActivityConfig config) {
        config.setToolbarEnable(true).setToolbarNavigationType(NavigationType.BACK);
//        if (mCar != null) config.setToolbarTitle(mCar.getFullName());

    }

    private void initView() {
        mCarImagePagerAdapter = new CarImagePagerAdapter();
        mImageViewPager.setAdapter(mCarImagePagerAdapter);
    }

    @Override
    public void setCarImages(List<String> carImageUrls) {
        mCarImagePagerAdapter.setItems(carImageUrls);
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}


