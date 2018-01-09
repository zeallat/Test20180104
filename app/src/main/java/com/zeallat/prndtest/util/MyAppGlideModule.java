package com.zeallat.prndtest.util;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ViewUtils;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.zeallat.prndtest.R;

/**
 * Created by HoJunLee on 2018-01-08.
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        RequestOptions requestOptions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requestOptions = new RequestOptions()
                    .placeholder(R.drawable.layer_image_default_placeholder)
                    .error(R.drawable.layer_image_error_placeholder)
                    .fallback(R.drawable.layer_image_error_placeholder);
        } else {
            requestOptions = new RequestOptions()
                    .placeholder(R.drawable.layer_image_default_placeholder)
                    .error(R.drawable.ic_image_error_placeholder)
                    .fallback(R.drawable.ic_image_error_placeholder);
        }
        builder.setDefaultRequestOptions(requestOptions);
    }
}
