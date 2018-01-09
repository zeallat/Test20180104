
package com.zeallat.prndtest.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * 차량 모델 데이터 모델
 */
public class Model implements Searchable {

    @SerializedName("absolute_url")
    private String mAbsoluteUrl;
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;

    public String getAbsoluteUrl() {
        return mAbsoluteUrl;
    }

    public void setAbsoluteUrl(String absoluteUrl) {
        mAbsoluteUrl = absoluteUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
