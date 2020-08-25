package com.example.tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AllgEinstellungen extends AppCompatActivity {

    private Model m_kModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allg_einstellungen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Model bekommen
        Intent intent = getIntent();
        m_kModel = (Model) intent.getSerializableExtra("model");
    }


}
