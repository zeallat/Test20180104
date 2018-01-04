package com.zeallat.prndtest.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.zeallat.prndtest.data.model.User;

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

    private static final String PREF_IS_FIRST_LAUNCH = "faf482fb-4c84-49c9-b6e3-bb8d168be413";
    private static final String PREF_USER = "258b0389-235b-4848-8a12-67c5e64ab6b6";

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

    @Override
    public void setUser(User user) {
        mPrefs.edit().putString(PREF_USER, new Gson().toJson(user)).apply();
    }

    @Override
    public User getUser() {
        String user = mPrefs.getString(PREF_USER, null);
        if (user == null) return null;
        return new Gson().fromJson(user, User.class);
    }

    @Override
    public void setIsFirstLaunch(boolean isFirstLaunch) {
        mPrefs.edit().putBoolean(PREF_IS_FIRST_LAUNCH, isFirstLaunch).apply();
    }

    @Override
    public boolean isFirstLaunch() {
        return mPrefs.getBoolean(PREF_IS_FIRST_LAUNCH, true);
    }
}
