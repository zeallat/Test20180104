package com.zeallat.prndtest.data.model;

/**
 * Created by HoJunLee on 2018-01-06.
 */

/**
 * 페이징 정보 데이터 모델
 */
public class PaginationInfo {

    private String mLink;
    private int mCount;
    private int mPageSize;

    public PaginationInfo() {
    }

    public PaginationInfo(String link, int count, int pageSize) {
        mLink = link;
        mCount = count;
        mPageSize = pageSize;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }
}
