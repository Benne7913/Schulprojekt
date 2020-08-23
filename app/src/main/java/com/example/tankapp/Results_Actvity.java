package com.example.tankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;

public class Results_Actvity extends AppCompatActivity {

    private RecyclerView rcView;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //RecyclerView mit Klasse verbinden
        rcView.findViewById(R.id.resultsview);

        Tankstelle tank = new Tankstelle();
        Results_Adapter adapter = new Results_Adapter(this, tank );
    }
}