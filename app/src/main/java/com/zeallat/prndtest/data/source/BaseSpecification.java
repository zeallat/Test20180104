package com.zeallat.prndtest.data.source;

/**
 * 베이스 조회조건
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
