package com.zeallat.prndtest.data.model.specification;


import com.zeallat.prndtest.data.source.Specification;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public class CarSpecificationById implements Specification {
    private String id;

    public CarSpecificationById(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
