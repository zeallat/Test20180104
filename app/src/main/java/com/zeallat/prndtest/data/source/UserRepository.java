package com.zeallat.prndtest.data.source;

import com.zeallat.prndtest.data.model.User;
import com.zeallat.prndtest.data.source.remote.UserRemoteDataSource;

/**
 * Created by HoJunLee on 2017-11-15.
 */

public class UserRepository extends BaseRepository<User, UserRemoteDataSource> {
    public UserRepository() {
        super(new UserRemoteDataSource());
    }
}
