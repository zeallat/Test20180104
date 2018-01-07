package com.zeallat.prndtest.data.source;


import com.zeallat.prndtest.data.model.Brand;
import com.zeallat.prndtest.data.source.remote.BrandRemoteDataSource;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public class BrandRepository extends BaseRepository<Brand, BrandRemoteDataSource> {
    public BrandRepository() {
        super(new BrandRemoteDataSource());
    }
}
