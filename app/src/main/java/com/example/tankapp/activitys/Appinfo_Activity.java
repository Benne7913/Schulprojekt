package com.example.tankapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tankapp.R;

public class Appinfo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
