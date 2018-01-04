package com.zeallat.prndtest.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;

/**
 * HTMLTextView.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-24.
 */
public class HTMLTextView extends android.support.v7.widget.AppCompatTextView {
    public HTMLTextView(Context context) {
        super(context);
        init();
    }

    public HTMLTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HTMLTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setHtmlText(getText().toString());
    }

    public void setHtmlText(String htmlText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT));
        } else {
            setText(Html.fromHtml(htmlText));
        }
    }
}
