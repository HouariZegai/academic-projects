package com.houarizegai.simplenotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static int notificationID = 1;
    private static String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Messages", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void onShowNotification(View view) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentText("Title - Hello")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("Content of toast")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(notificationID++, mBuilder.build());
    }

}
