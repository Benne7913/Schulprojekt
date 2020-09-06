package com.example.tankapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tankapp.MainActivity;
import com.example.tankapp.model.General_Model;
import com.example.tankapp.R;

import java.util.ArrayList;

public class Setting_Activity extends AppCompatActivity {

    private General_Model m_kGeneralModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Model bekommen
        Intent intent = getIntent();
        m_kGeneralModel = (General_Model) intent.getSerializableExtra("Model");

        //Spinner
        String[] firstItem = {};
        Spinner spinner = findViewById(R.id.setting_spinner_range);
        ArrayList<String> lcosRange = new ArrayList<>();
        lcosRange.add("5");
        lcosRange.add("10");
        lcosRange.add("20");
        lcosRange.add("25");
        lcosRange.add("30");
        lcosRange.add("40");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lcosRange);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        //set current Range
        int index = lcosRange.indexOf(m_kGeneralModel.getRadius());
        spinner.setSelection(index);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                m_kGeneralModel.setRadius(parent.getItemAtPosition(position).toString());

            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = getIntent();
                intent.putExtra("result", m_kGeneralModel);
                setResult(RESULT_OK,intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onBackPressed() {

    }
}



