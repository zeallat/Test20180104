package com.zeallat.prndtest.car.detail;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BasePagerAdapter;

/**
 * Created by HoJunLee on 2018-01-07.
 */

public class CarImagePagerAdapter extends BasePagerAdapter<String> {
    @Override
    protected View getView(@NonNull ViewGroup container, int position, String item) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_car_image, container, false);
        ImageView imageView = view.findViewById(R.id.imageViewContent);
        Glide.with(imageView).load(item)
                .thumbnail(0.1f)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.layer_image_default_placeholder)
                        .error(R.drawable.layer_image_error_placeholder))
                .into(imageView);
        return view;
    }
}
