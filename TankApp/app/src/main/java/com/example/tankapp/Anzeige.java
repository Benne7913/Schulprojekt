package com.example.tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Anzeige extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anzeige);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
