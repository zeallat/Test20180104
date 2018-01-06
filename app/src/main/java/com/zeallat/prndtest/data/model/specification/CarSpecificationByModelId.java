package com.zeallat.prndtest.data.model.specification;

import com.zeallat.prndtest.data.source.Specification;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public class CarSpecificationByModelId implements Specification {
    private String modelId;

    public CarSpecificationByModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelId() {
        return modelId;
    }
}
