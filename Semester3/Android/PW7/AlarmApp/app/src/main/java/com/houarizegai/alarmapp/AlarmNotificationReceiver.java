package com.houarizegai.alarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"The current Time is: " + System.currentTimeMillis(), Toast.LENGTH_SHORT).show();
    }
}
