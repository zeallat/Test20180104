package com.zeallat.prndtest.car.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseViewActivity;
import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.view.HTMLTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class CarDetailActivity extends BaseViewActivity<CarDetailContract.Presenter> implements CarDetailContract.View {

    public static final String EXTRA_CAR_ID = "556b8b3f-eb7d-420b-8364-fa7000f48437";

    @BindView(R.id.imageViewPager) ViewPager mImageViewPager;
    @BindView(R.id.htmlTextViewOriginalPrice) HTMLTextView mHtmlTextViewOriginalPrice;
    @BindView(R.id.textViewPrice) TextView mTextViewPrice;
    @BindView(R.id.textViewStatus) TextView mTextViewStatus;
    @BindView(R.id.textViewNumber) TextView mTextViewNumber;
    @BindView(R.id.textViewMileage) TextView mTextViewMileage;
    @BindView(R.id.textViewRegistrationDate) TextView mTextViewRegistrationDate;
    @BindView(R.id.textViewYear) TextView mTextViewYear;
    @BindView(R.id.textViewFuel) TextView mTextViewFuel;
    @BindView(R.id.textViewCall) TextView mTextViewCall;
    @BindView(R.id.viewPagerIndicatoer) CircleIndicator mViewPagerIndicatoer;
    @BindView(R.id.containerNoItem) LinearLayout mContainerNoItem;

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
        mViewPagerIndicatoer.setViewPager(mImageViewPager);
        mCarImagePagerAdapter.registerDataSetObserver(mViewPagerIndicatoer.getDataSetObserver());
    }

    @Override
    public void setCarImages(List<String> carImageUrls) {
        mCarImagePagerAdapter.setItems(carImageUrls);
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @OnClick(R.id.textViewCall)
    public void onViewClicked() {
        mPresenter.onClickButtonCall();
    }

    @Override
    public void setPrice(String text) {
        mTextViewPrice.setText(text);
    }

    @Override
    public void setOriginalPrice(String text) {
        mHtmlTextViewOriginalPrice.setHtmlText(String.format("<del>%s</del>", text));
    }

    @Override
    public void setStatus(Car.Status status, String statusText) {
        int statusColor = ContextCompat.getColor(this, status.getColorResId());
        mHtmlTextViewOriginalPrice.setVisibility(status == Car.Status.ON_SALE ? View.VISIBLE : View.GONE);
        mTextViewPrice.setTextColor(
                ContextCompat.getColor(this,
                        status == Car.Status.SOLD_OUT ? R.color.text_secondary : R.color.text_primary));
        mTextViewStatus.setText(statusText);
        mTextViewStatus.setTextColor(statusColor);
        mTextViewCall.setBackgroundColor(statusColor);
    }

    @Override
    public void setNumber(String text) {
        mTextViewNumber.setText(text);
    }

    @Override
    public void setMileage(String text) {
        mTextViewMileage.setText(text);
    }

    @Override
    public void setRegistrationDate(String text) {
        mTextViewRegistrationDate.setText(text);
    }

    @Override
    public void setYear(String text) {
        mTextViewYear.setText(text);
    }

    @Override
    public void setFuel(String text) {
        mTextViewFuel.setText(text);
    }

    @Override
    public void setNoItemViewVisible(boolean isVisible) {
        mContainerNoItem.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}


