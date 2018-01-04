package com.zeallat.prndtest.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.text.TextUtils.isEmpty;

/**
 * Created by HoJunLee on 2017-11-16.
 */

public class FcmService extends FirebaseInstanceIdService {

    @Override
    public void onCreate() {
        super.onCreate();
        updateToken(FirebaseInstanceId.getInstance().getToken());
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        updateToken(FirebaseInstanceId.getInstance().getToken());
    }

    private void updateToken(String token) {
        if (isEmpty(token)) {
            Log.d("FcmService", "token is not exist.");
            return;
        }
        Log.d("FcmService", "token is refreshed : " + token);
        //todo: update token
    }

}
