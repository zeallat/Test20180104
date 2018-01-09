package com.zeallat.prndtest.data.source;


import com.zeallat.prndtest.data.model.Brand;
import com.zeallat.prndtest.data.source.remote.BrandRemoteDataSource;

/**
 * 브랜드 데이터 Repository
 */
public class BrandRepository extends BaseRepository<Brand, BrandRemoteDataSource> {
    public BrandRepository() {
        super(new BrandRemoteDataSource());
    }
}
