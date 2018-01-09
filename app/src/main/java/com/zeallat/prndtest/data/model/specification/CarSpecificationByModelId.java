package com.zeallat.prndtest.data.model.specification;

import com.zeallat.prndtest.data.source.BaseSpecification;

/**
 * 차량 조회조건 - model id
 */
public class CarSpecificationByModelId extends BaseSpecification {
    private int modelId;

    public CarSpecificationByModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getModelId() {
        return modelId;
    }
}
