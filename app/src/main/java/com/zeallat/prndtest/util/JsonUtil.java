package com.zeallat.prndtest.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;

/**
 * JsonUtil.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-24.
 */
public class JsonUtil {
    public static <T> List<T> fromJsonList(JsonElement jsonElement, Class<T> classOfT) {
        return new Gson().fromJson(jsonElement, new ListParameterizedType<>(classOfT));
    }

    public static <T> JsonArray toJsonList(List<T> list, Class<T> classOfT) {
        return new Gson().toJsonTree(list, new ListParameterizedType<>(classOfT)).getAsJsonArray();
    }
}
