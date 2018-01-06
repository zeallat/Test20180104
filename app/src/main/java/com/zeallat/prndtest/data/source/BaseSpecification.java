package com.zeallat.prndtest.data.source;

/**
 * Created by HoJunLee on 2018-01-06.
 */

public class BaseSpecification implements Specification {
    private int page = 1;

    public BaseSpecification() {
    }

    public BaseSpecification(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
