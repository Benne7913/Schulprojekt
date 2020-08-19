package com.example.tankapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity {

    private String items[] = new String[] {"Tanke1", "Tanke2" ,"Tanke3", "Tanke4"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ListView listView =  (ListView) findViewById(R.id.result_list);
        ArrayAdapter<String> adapater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Results.this, ResultDetail.class).putExtra("item",items[position]));
            }
        });
    }
   
}
