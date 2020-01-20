package com.houarizegai.uidemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress + 20;
                findViewById(R.id.txtView1).setBackgroundColor(Color.argb(progress, 255, 0, 0));
                findViewById(R.id.txtView2).setBackgroundColor(Color.argb(progress, 0, 255, 0));
                findViewById(R.id.txtView3).setBackgroundColor(Color.argb(progress, 255, 255, 0));
                findViewById(R.id.txtView4).setBackgroundColor(Color.argb(progress, 230, 0, 20));
                findViewById(R.id.txtView5).setBackgroundColor(Color.argb(progress, 250, 0, 0));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar.setProgress(10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuItemMoreInfor) {
            new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                    .setTitle("Inspired by the works of artists such as Piet Mondrian and Ben Nicholson")
                    .setMessage("Click below to learn more!")
                    .setPositiveButton("Not Now", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Nothing to do, just close
                        }
                    })
                    .setNegativeButton("Visit MOMA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")));
                        }
                    }).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
