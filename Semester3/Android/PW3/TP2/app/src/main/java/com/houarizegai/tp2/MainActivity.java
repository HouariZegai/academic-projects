package com.houarizegai.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    TextView txtFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFlag = findViewById(R.id.txtFlag);

        Intent intent = getIntent();
        txtFlag.setText(intent.getStringExtra("str"));
    }

    public void ExplicitActivation(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void implicitActivation(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")));
    }
}
