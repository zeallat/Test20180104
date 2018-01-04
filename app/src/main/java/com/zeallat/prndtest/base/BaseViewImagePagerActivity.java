package com.zeallat.prndtest.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.imagepager.ImagePagerFragment;

import butterknife.BindView;

/**
 * Created by HoJunLee on 2017-10-29.
 */

public abstract class BaseViewImagePagerActivity<T extends BasePresenter> extends BaseViewActivity<T> implements BaseView<T> {

    @BindView(R.id.viewPager) ViewPager mViewPager;

    private ImagePagerFragmentAdapter mImagePagerFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.base_layout_image_pager);
        initView();
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        mImagePagerFragmentAdapter =
                new ImagePagerFragmentAdapter(getSupportFragmentManager(),
                        getResourceNameFormat(),
                        getResourceIndexBegin(),
                        getResourceSize());
        mViewPager.setAdapter(mImagePagerFragmentAdapter);
    }

    protected abstract String getResourceNameFormat();

    protected abstract int getResourceIndexBegin();

    protected abstract int getResourceSize();

    public class ImagePagerFragmentAdapter extends FragmentPagerAdapter {
        private String mNameFormat;
        private int mIndexBegin;
        private int mSize;

        public ImagePagerFragmentAdapter(FragmentManager fm, String nameFormat, int indexBegin, int size) {
            super(fm);
            mNameFormat = nameFormat;
            mIndexBegin = indexBegin;
            mSize = size;
        }

        @Override
        public Fragment getItem(int position) {
            return ImagePagerFragment.newInstance(String.format(mNameFormat, mIndexBegin + position));
        }

        @Override
        public int getCount() {
            return mSize;
        }
    }

}
