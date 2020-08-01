package com.e.incubationcell.medicaps.ac.countit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.sql.Time;

public class Spalash extends AppCompatActivity {
    private static int time_out = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Spalash.this,MainActivity.class));
                finish();
            }
        },time_out);
    }
}