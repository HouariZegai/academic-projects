package com.houarizegai.alarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;

    PendingIntent nReceiverPendingIntent;
    PendingIntent loggerReceiverPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent nIntent = new Intent(this, AlarmNotificationReceiver.class);
        nReceiverPendingIntent = PendingIntent.getBroadcast(this, 0, nIntent, 0);

        Intent loggerIntent = new Intent(this, AlarmLoggerReceiver.class);
        loggerReceiverPendingIntent = PendingIntent.getBroadcast(this, 0, loggerIntent, 0);

    }

    public void onSingleAlarm(View view) {
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000,
                nReceiverPendingIntent);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000,
                loggerReceiverPendingIntent);
    }

    public void onRepeatingAlarm(View view) {
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 5000,
                3000,
                nReceiverPendingIntent);

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 5000,
                3000,
                loggerReceiverPendingIntent
        );

        Toast.makeText(this, "Repeating Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void onInexactAlarm(View view) {
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 5000,
                3_000, nReceiverPendingIntent);

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 5000,
                3_000, loggerReceiverPendingIntent);
    }

    public void onCancelAlarm(View view) {
        alarmManager.cancel(nReceiverPendingIntent);
        alarmManager.cancel(loggerReceiverPendingIntent);

        Toast.makeText(this, "Canceling Repeating Alarms", Toast.LENGTH_SHORT).show();
    }
}
