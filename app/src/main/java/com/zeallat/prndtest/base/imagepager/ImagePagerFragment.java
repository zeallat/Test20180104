package com.zeallat.prndtest.base.imagepager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseViewFragment;

import butterknife.BindView;


public class ImagePagerFragment extends BaseViewFragment<ImagePagerContract.Presenter> implements ImagePagerContract.View {

    private static final String ARGS_IMAGE = "5111ba17-1841-43c3-a817-1f403925f7cd";
    @BindView(R.id.imageViewContent) ImageView mImageViewContent;

    private String mImage = "";

    public static ImagePagerFragment newInstance(String image) {
        Bundle args = new Bundle();
        args.putString(ARGS_IMAGE, image);
        ImagePagerFragment fragment = new ImagePagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            mImage = getArguments().getString(ARGS_IMAGE);
        }
        super.onCreate(savedInstanceState);
        new ImagePagerPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_layout_fragment_image_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //set image
        Context context = mImageViewContent.getContext();
        int id = context.getResources().getIdentifier(mImage, "drawable", context.getPackageName());
        mImageViewContent.setImageResource(id);
    }

}


