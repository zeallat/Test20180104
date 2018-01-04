package com.zeallat.prndtest.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.zeallat.prndtest.R;

/**
 * Created by HoJunLee on 2017-11-16.
 */

public class FcmMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("FcmMessagingService", "From : " + remoteMessage.getFrom());

        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (remoteMessage.getData().size() > 0) {
            /**
             * 데이터 파싱
             */
            Log.d("FCM", "Message data payload: " + remoteMessage.getData());
            String message = "";
//            final String pushTypeId = remoteMessage.getData().get("flag");
//            String message = remoteMessage.getData().get("message");
//            String isAlim = remoteMessage.getData().get("badge");
//            String imageUrl = remoteMessage.getData().get("imgUrl");
            String realMessage = "";
//            String realMessage = new Gson().fromJson(message, JsonObject.class).get("message").getAsString();
            PendingIntent pending;

            Intent intent = null;
//            Intent intent = new Intent(this, MainActivity.class);
            /**
             * 팬딩 인텐트 생성
             */
            pending = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            /**
             * 알림 생성
             */

            final NotificationCompat.Builder builder;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel("c118cc1a-8148-493f-a351-48d53e6a914e", "일반", NotificationManager.IMPORTANCE_DEFAULT);
                builder = new NotificationCompat.Builder(this, notificationChannel.getId());
            } else {
                builder = new NotificationCompat.Builder(this);
            }

            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText(realMessage)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentIntent(pending)
                    .setWhen(System.currentTimeMillis())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setSound(Uri.parse(Settings.System.DEFAULT_NOTIFICATION_URI.toString()))
                    .setVibrate(new long[]{0, 1000})
                    .setAutoCancel(true);

            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(builder);
            bigTextStyle.setBigContentTitle(getString(R.string.app_name));
            bigTextStyle.setSummaryText(realMessage);
            bigTextStyle.bigText(realMessage);
            builder.setStyle(bigTextStyle);

            manager.notify(64940, builder.build());
        }

    }
}


