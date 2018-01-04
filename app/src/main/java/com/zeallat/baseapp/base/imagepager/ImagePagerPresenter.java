package com.zeallat.baseapp.base.imagepager;

public class ImagePagerPresenter implements ImagePagerContract.Presenter {

    private ImagePagerContract.View mView;

    public ImagePagerPresenter(ImagePagerContract.View view) {
        mView = view;
        mView.setPresenter(this);
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
    public void onCreate() {

    }
}


