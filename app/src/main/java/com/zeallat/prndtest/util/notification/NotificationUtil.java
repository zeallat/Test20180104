package com.zeallat.prndtest.util.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by HoJunLee on 2017-12-12.
 */

public class NotificationUtil {

    private Context mContext;

    public NotificationUtil(Context context) {
        mContext = context;
    }

    public void show(NotificationOption option) {

        NotificationOption.Channel channel = option.getChannel();

        NotificationManager manager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channel.getId(), channel.getName(), channel.getImportance());
            if (manager != null) {
                manager.createNotificationChannel(notificationChannel);
            }
            builder = new NotificationCompat.Builder(mContext, notificationChannel.getId());
        } else {
            builder = new NotificationCompat.Builder(mContext);
        }

        builder.setSmallIcon(NotificationOption.DEFAULT_ICON)
                .setContentText(option.getBody())
                .setContentTitle(option.getTitle())
//                .setContentIntent(pending)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(Uri.parse(Settings.System.DEFAULT_NOTIFICATION_URI.toString()))
                .setVibrate(new long[]{0, 1000})
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(builder);
        bigTextStyle.setBigContentTitle(option.getTitle());
        bigTextStyle.setSummaryText(option.getBody());
        bigTextStyle.bigText(option.getBody());
        builder.setStyle(bigTextStyle);

        manager.notify(12345, builder.build());
    }

}
