package com.zeallat.baseapp.data.pref;

import com.zeallat.baseapp.data.model.User;

/**
 * PreferencesRepository.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-19.
 */
public interface PreferencesRepository {

    void setUser(User user);

    User getUser();

    void setIsFirstLaunch(boolean isFirstLaunch);

    boolean isFirstLaunch();
}
