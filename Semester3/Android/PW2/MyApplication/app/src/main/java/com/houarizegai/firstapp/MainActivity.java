package com.houarizegai.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int onCreateCounter, onStartCounter, onResumeCounter, onRestartCounter;

    public TextView txtOnCreate, txtOnStart, txtOnResume, txtOnRestart;

    public static int onDestroyCounter, onPauseCounter, onStopCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    protected void onStop() {
        super.onStop();
        onStopCounter++;
        Log.d("onStop()", String.valueOf(onStopCounter));
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseCounter++;
        Log.d("onPause()", String.valueOf(onPauseCounter));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyCounter++;
        Log.d("Destroy()", String.valueOf(onDestroyCounter));
    }

    public void onStartActivity2(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
