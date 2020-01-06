package com.houarizegai.simplebroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_INTENT = "com.houarizegai.simplebroadcastreceiver.ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSimpleBroadcast(View view) {
        sendOrderedBroadcast(new Intent(CUSTOM_INTENT), android.Manifest.permission.VIBRATE);
    }
}
