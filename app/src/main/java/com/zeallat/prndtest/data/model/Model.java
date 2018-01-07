
package com.zeallat.prndtest.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Model extends RealmObject implements Searchable {

    @SerializedName("absolute_url")
    private String mAbsoluteUrl;
    @PrimaryKey
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

    @Override
    public String getDisplayName() {
        return mName;
    }
}
