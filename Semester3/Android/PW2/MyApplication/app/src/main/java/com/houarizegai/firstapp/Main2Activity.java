package com.houarizegai.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public static int onCreateCounter, onStartCounter, onResumeCounter, onRestartCounter;

    public TextView txtOnCreate, txtOnStart, txtOnResume, txtOnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtOnCreate = (TextView) findViewById(R.id.txtOnCreate);
        txtOnStart = (TextView) findViewById(R.id.txtOnStart);
        txtOnResume = (TextView) findViewById(R.id.txtOnResume);
        txtOnRestart = (TextView) findViewById(R.id.txtOnRestart);

        onCreateCounter++;
        txtOnCreate.setText("onCreate(): " + onCreateCounter);
    }

    @Override
    protected void onStart() {
        onStartCounter++;
        super.onStart();
        txtOnStart.setText("onStart(): " + onStartCounter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onResumeCounter++;
        txtOnResume.setText("onResume(): " + onResumeCounter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onResumeCounter++;
        txtOnRestart.setText("onRestart(): " + onRestartCounter);
    }

    public void onBackToActivity1(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
