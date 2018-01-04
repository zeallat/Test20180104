package com.zeallat.prndtest;

/**
 * Created by HoJunLee on 2017-10-09.
 */

public class Config {
    /**
     * 모든 액티비티를 세로 화면 고정으로 설정할지의 여부.
     * true로 설정시 모든 액티비티가 세로화면으로 고정된다.
     */
    public static final boolean IS_SCREEN_ORIENTATION_PORTRAIT_REQUIRED = false;

    /**
     * Firebase realtime database path
     */
    public enum FirebaseDatabaseRef {
        USER("users");
        private String path;

        FirebaseDatabaseRef(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }
}
