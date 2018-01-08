
package com.zeallat.prndtest.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Brand implements Searchable {

    @SerializedName("absolute_url")
    private String mAbsoluteUrl;
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("model_groups")
    private List<ModelGroup> mModelGroups;

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

    public List<ModelGroup> getModelGroups() {
        return mModelGroups;
    }

    public void setModelGroups(List<ModelGroup> modelGroups) {
        mModelGroups = modelGroups;
    }
}
