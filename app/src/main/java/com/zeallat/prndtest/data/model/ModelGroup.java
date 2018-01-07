
package com.zeallat.prndtest.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ModelGroup extends RealmObject {

    @SerializedName("absolute_url")
    private String mAbsoluteUrl;
    @PrimaryKey
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("models")
    private RealmList<Model> mModels;

    public String getAbsoluteUrl() {
        return mAbsoluteUrl;
    }

    public void setAbsoluteUrl(String absoluteUrl) {
        mAbsoluteUrl = absoluteUrl;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public RealmList<Model> getModels() {
        return mModels;
    }

    public void setModels(RealmList<Model> models) {
        mModels = models;
    }
}