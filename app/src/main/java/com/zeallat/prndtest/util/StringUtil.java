package com.zeallat.prndtest.util;

import android.support.annotation.StringRes;

import com.blankj.utilcode.util.Utils;

import java.util.Locale;

/**
 * StringUtil.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-08-10.
 */
public class StringUtil {
    public static String formatCurrency(String input) {
        return formatCurrency(Integer.valueOf(input));
    }

    public static String formatCurrency(int input) {
        return String.format("%,d", input);
    }

    public static String formatPrice(int price) {
        return String.format(Locale.KOREA, "%,d만원", price);
    }

    public static String getString(@StringRes int stringResId) {
        return Utils.getApp().getApplicationContext().getString(stringResId);
    }
}
