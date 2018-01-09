package com.zeallat.prndtest.data.model.specification;


import com.zeallat.prndtest.data.source.BaseSpecification;

/**
 * 차량 조회 조건 - id
 */
public class CarSpecificationById extends BaseSpecification {
    private int id;

    public CarSpecificationById(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
