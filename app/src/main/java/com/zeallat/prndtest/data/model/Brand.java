
package com.zeallat.prndtest.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Brand extends RealmObject {

    @SerializedName("absolute_url")
    private String mAbsoluteUrl;
    @PrimaryKey
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("model_groups")
    private RealmList<ModelGroup> mModelGroups;

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

    public RealmList<ModelGroup> getModelGroups() {
        return mModelGroups;
    }

    public void setModelGroups(RealmList<ModelGroup> modelGroups) {
        mModelGroups = modelGroups;
    }
}