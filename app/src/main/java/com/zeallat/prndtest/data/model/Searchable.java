package com.zeallat.prndtest.data.model;

/**
 * 검색 가능 데이터 인터페이스
 */
public interface Searchable {

    public enum Type {BRAND, MODEL_GROUP, MODEL}

    String getDisplayName();
}
