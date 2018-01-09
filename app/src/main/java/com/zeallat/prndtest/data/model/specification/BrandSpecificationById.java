package com.zeallat.prndtest.data.model.specification;


import com.zeallat.prndtest.data.source.BaseSpecification;

/**
 * 브랜드 조회 조건 - id
 */
public class BrandSpecificationById extends BaseSpecification {
    private int id;

    public BrandSpecificationById(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
