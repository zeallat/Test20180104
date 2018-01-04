package com.zeallat.baseapp.data.source;

import android.support.annotation.NonNull;

import com.zeallat.baseapp.data.model.User;
import com.zeallat.baseapp.data.source.remote.UserRemoteDataSource;

/**
 * Created by HoJunLee on 2017-11-15.
 */

public class UserRepository extends BaseRepository<User, UserRemoteDataSource> {
    public UserRepository() {
        super(new UserRemoteDataSource());
    }
}
