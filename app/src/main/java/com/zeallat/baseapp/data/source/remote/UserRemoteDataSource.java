package com.zeallat.baseapp.data.source.remote;

import com.zeallat.baseapp.Config;
import com.zeallat.baseapp.data.model.User;

/**
 * Created by HoJunLee on 2017-11-15.
 */

public class UserRemoteDataSource extends BaseRemoteDataSource<User> {
    public UserRemoteDataSource() {
        super(User.class);
    }

    @Override
    public String getPath() {
        return Config.FirebaseDatabaseRef.USER.getPath();
    }
}
