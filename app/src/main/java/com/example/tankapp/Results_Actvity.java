package com.example.tankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Results_Actvity extends AppCompatActivity {

    private RecyclerView rcView;
    ArrayList<Tankstelle> m_kTankstellen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Holt die mitgegebene Tankstellenliste
        Bundle bundle = getIntent().getExtras();
        m_kTankstellen = (ArrayList<Tankstelle>) bundle.getSerializable("tankstellenliste");

        //RecyclerView mit Klasse verbinden
        rcView = findViewById(R.id.view);
        Intent intent = getIntent();

        Results_Adapter adapter = new Results_Adapter(this, m_kTankstellen );
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));
    }
}