package com.zeallat.baseapp.data.model;

/**
 * Created by HoJunLee on 2017-11-15.
 */

public class User extends BaseModel {

    public enum Status {
        NORMAL(1, "일반"), WITHDRAWAL(2, "탈퇴"), BLOCK(3, "정지");
        private int id;
        private String name;

        Status(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    private String name;
    private int statusId = Status.NORMAL.getId();

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
