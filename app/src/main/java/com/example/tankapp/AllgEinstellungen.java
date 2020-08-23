package com.example.tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AllgEinstellungen extends AppCompatActivity {

    public static int currentRange = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allg_einstellungen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}
