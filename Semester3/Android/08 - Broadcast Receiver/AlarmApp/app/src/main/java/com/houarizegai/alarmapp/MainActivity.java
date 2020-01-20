package com.houarizegai.alarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    static NotificationManager notificationManager;

    AlarmManager mAlarmManager;

    PendingIntent mNotificationReceiverPendingIntent;
    PendingIntent mLoggerReceiverPendingIntent;

    private final static int INITIAL_ALARM_DELAY = 5000;
    private final static int JITTER = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent  mNotificationReceiverIntent = new Intent(MainActivity.this, AlarmNotificationReceiver.class);
        mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, mNotificationReceiverIntent, 0);

        Intent mLoggerReceiverIntent = new Intent(MainActivity.this, AlarmLoggerReceiver.class);
        mLoggerReceiverPendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, mLoggerReceiverIntent, 0);

        createNotificationChannel();
    }

    public void onSingleAlarm(View view) {
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + INITIAL_ALARM_DELAY,
                mNotificationReceiverPendingIntent);

        mAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + INITIAL_ALARM_DELAY + JITTER,
                mLoggerReceiverPendingIntent);

        Toast.makeText(getApplicationContext(), "Single Alarm Set", Toast.LENGTH_LONG).show();
    }

    public void onRepeatingAlarm(View view) {
        mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                mNotificationReceiverPendingIntent);

        mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY + JITTER,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                mLoggerReceiverPendingIntent);

        Toast.makeText(this, "Repeating Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void onInexactAlarm(View view) {
        mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                mNotificationReceiverPendingIntent);

        mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY + JITTER,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                mLoggerReceiverPendingIntent);

        Toast.makeText(this, "Inexact Repeating Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void onCancelAlarm(View view) {
        mAlarmManager.cancel(mNotificationReceiverPendingIntent);
        mAlarmManager.cancel(mLoggerReceiverPendingIntent);

        Toast.makeText(this, "Repeating Alarms Cancelled", Toast.LENGTH_SHORT).show();
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
}
