package com.zeallat.baseapp.data.model;

/**
 * Created by HoJunLee on 2017-11-15.
 */

public class BaseModel {
    private String id;
    private String groupId;
    private long createdAt = System.currentTimeMillis();
    private long modifiedAt = System.currentTimeMillis();

    public BaseModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(long modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
