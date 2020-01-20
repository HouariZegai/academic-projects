package com.houarizegai.customnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    // Notification ID to allow for future updates
    private static final int NOTIFICATION_ID = 1;

    // Notification Count
    private int mNotificationCounter;

    // Notification Action Elements
    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    // Notification Sound and Vibration on Arrival
    private long[] mVibratePattern = { 0, 200, 200, 300 };

    RemoteViews mContentView = new RemoteViews(
            "com.houarizegai.customnotification",
            R.layout.custom_notification);

    NotificationManager mNotificationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mContentIntent = PendingIntent.getActivity(this, 0,
                new Intent(getApplicationContext(), SubActivity.class),
                PendingIntent.FLAG_ONE_SHOT);

        createNotificationChannel();

        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setTicker("This is a Custom Notification Message!")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(mContentIntent)
                .setVibrate(mVibratePattern)
                .setContent(mContentView);

        findViewById(R.id.btnNotify).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Define the Notification's expanded message and Intent:
                mContentView.setTextViewText(R.id.text, "You have Been Notified! (" + ++mNotificationCounter + ")");

                // Pass the Notification to the NotificationManager:
                mNotificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
            }
        });

    }

    private void createNotificationChannel() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Messages", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            mNotificationManager.createNotificationChannel(channel);
        }
    }
}
