package com.zeallat.prndtest.util;

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

}
