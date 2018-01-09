package com.zeallat.prndtest.data.model.specification;


import com.zeallat.prndtest.data.source.BaseSpecification;

/**
 * 모델 그룹 조회조건 - id
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
