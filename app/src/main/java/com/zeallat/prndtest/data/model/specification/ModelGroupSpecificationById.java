package com.zeallat.prndtest.data.model.specification;


import com.zeallat.prndtest.data.source.BaseSpecification;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public class ModelGroupSpecificationById extends BaseSpecification {
    private int id;

    public ModelGroupSpecificationById(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
