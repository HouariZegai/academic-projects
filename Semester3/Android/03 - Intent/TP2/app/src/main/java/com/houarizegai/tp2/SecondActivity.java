package com.houarizegai.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    public EditText editTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTxt = findViewById(R.id.editTxt);
    }

    public void onEnter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("str", editTxt.getText().toString());

        startActivity(intent);
    }
}
