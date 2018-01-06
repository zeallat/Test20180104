package com.zeallat.prndtest.data.model.specification;

import com.zeallat.prndtest.data.source.BaseSpecification;

/**
 * Created by HoJunLee on 2018-01-05.
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
