package com.zeallat.prndtest.util.notification;

import com.blankj.utilcode.util.Utils;
import com.zeallat.prndtest.R;

/**
 * Created by HoJunLee on 2017-12-12.
 */

public class NotificationOption {

    public static final int DEFAULT_ICON = R.mipmap.ic_launcher;

    public enum Channel {
        DEFAULT("97ec4e3c-3b69-4814-84c9-86e2574e799a", "일반", 4);

        private String id;
        private String name;
        private int importance;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getImportance() {
            return importance;
        }

        Channel(String id, String name, int importance) {
            this.id = id;
            this.name = name;
            this.importance = importance;
        }
    }

    private String mTitle;
    private String mBody;
    private Channel mChannel;

    private NotificationOption(Builder builder) {
        mTitle = builder.mTitle;
        mBody = builder.mBody;
        mChannel = builder.mChannel;
    }

    public static class Builder {
        private String mTitle = Utils.getApp().getApplicationContext().getString(R.string.app_name);
        private String mBody = "";
        private Channel mChannel = Channel.DEFAULT;

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setBody(String body) {
            mBody = body;
            return this;
        }

        public Builder setChannel(Channel channel) {
            mChannel = channel;
            return this;
        }

        public NotificationOption build() {
            return new NotificationOption(this);
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public Channel getChannel() {
        return mChannel;
    }
}
