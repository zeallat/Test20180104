package com.zeallat.prndtest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-27.
 */
public class DateUtil {

    public enum Format {
        DAY_HOUR("yyyy년 MM월 dd일 HH:mm"), DAY("yyyy년 MM월 dd일"), HOUR("MM월 dd일 HH:mm"),
        DAY_DOT("yyyy.MM.dd"), DAY_DASH("yyyy-MM-dd");
        private String format;

        Format(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public static String formatDate(String date, Format format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            SimpleDateFormat sdfResult = new SimpleDateFormat(format.getFormat());
            Date dateSource = sdf.parse(date);
            return sdfResult.format(dateSource);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    public static Calendar calendarFromString(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(long timeInMillis, Format format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getFormat());
        Date date = new Date(timeInMillis);
        return sdf.format(date);
    }

    public static String format(long timeInMillis) {
        return format(timeInMillis, Format.DAY_HOUR);
    }

    public static String format(Calendar calendar, Format format) {
        return format(calendar.getTimeInMillis(), format);
    }

    public static String format(Calendar calendar) {
        return format(calendar.getTimeInMillis(), Format.DAY_HOUR);
    }

    public static String format(Date date, Format format) {
        return format(date.getTime(), format);
    }

    public static String format(Date date) {
        return format(date.getTime(), Format.DAY_HOUR);
    }
}
