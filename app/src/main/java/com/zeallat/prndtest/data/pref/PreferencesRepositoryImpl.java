package com.zeallat.prndtest.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

/**
 * PreferencesRepositoryImpl.java
 *
 * @author HoJunLee
 * @description SharedPreferences helper class
 * Created by HoJunLee on 2017-07-19.
 */
public class PreferencesRepositoryImpl implements PreferencesRepository {

    private static PreferencesRepositoryImpl INSTANCE = null;

    private SharedPreferences mPrefs;

    public static void init(@NonNull Context context) {
        if (INSTANCE == null) INSTANCE = new PreferencesRepositoryImpl(context);
    }

    public static PreferencesRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("not yet initialized.");
        }
        return INSTANCE;
    }

    private PreferencesRepositoryImpl(@NonNull Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

}
