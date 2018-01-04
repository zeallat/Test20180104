package com.zeallat.baseapp.util;

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

}
