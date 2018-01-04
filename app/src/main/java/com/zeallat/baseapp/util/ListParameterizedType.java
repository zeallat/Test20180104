package com.zeallat.baseapp.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * ListParameterizedType.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-24.
 */

public class ListParameterizedType<X> implements ParameterizedType {

    private Class<?> wrapped;

    public ListParameterizedType(Class<X> wrapped) {
        this.wrapped = wrapped;
    }

    public Type[] getActualTypeArguments() {
        return new Type[]{wrapped};
    }

    public Type getRawType() {
        return List.class;
    }

    public Type getOwnerType() {
        return null;
    }

}