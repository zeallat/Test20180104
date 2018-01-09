package com.zeallat.prndtest.data.source;


import com.zeallat.prndtest.data.model.ModelGroup;
import com.zeallat.prndtest.data.source.remote.ModelGroupRemoteDataSource;

/**
 * 모델 그룹 데이터 Repository
 */
public class ModelGroupRepository extends BaseRepository<ModelGroup, ModelGroupRemoteDataSource> {
    public ModelGroupRepository() {
        super(new ModelGroupRemoteDataSource());
    }
}
