package com.example.tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AllgEinstellungen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allg_einstellungen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
