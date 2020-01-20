package com.houarizegai.alarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmNotificationReceiver extends BroadcastReceiver {
    private static int notificationID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"The current Time is: " + System.currentTimeMillis(), Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, MainActivity.PRIMARY_CHANNEL_ID)
                .setContentText("Weak UP")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("Content of toast -> " + notificationID)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        MainActivity.notificationManager.notify(notificationID++, mBuilder.build());
    }
}
