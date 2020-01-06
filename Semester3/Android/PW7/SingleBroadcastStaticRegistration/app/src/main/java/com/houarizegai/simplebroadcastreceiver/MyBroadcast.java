package com.houarizegai.singlebroad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {

    private final String TAG = "Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "INTENT RECEIVER");

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);

        Toast.makeText(context, "INTENT RECEIVED by Receiver", Toast.LENGTH_SHORT).show();
    }
}
