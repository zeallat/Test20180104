package com.zeallat.prndtest.data.source;


import com.zeallat.prndtest.data.model.ModelGroup;
import com.zeallat.prndtest.data.source.remote.ModelGroupRemoteDataSource;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public class ModelGroupRepository extends BaseRepository<ModelGroup, ModelGroupRemoteDataSource> {
    public ModelGroupRepository() {
        super(new ModelGroupRemoteDataSource());
    }
}
