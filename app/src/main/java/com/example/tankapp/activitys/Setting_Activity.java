package com.example.tankapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tankapp.model.General_Model;
import com.example.tankapp.R;

public class Setting_Activity extends AppCompatActivity {

    private General_Model m_kGeneralModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Model bekommen
        Intent intent = getIntent();
        m_kGeneralModel = (General_Model) intent.getSerializableExtra("model");
    }


}
