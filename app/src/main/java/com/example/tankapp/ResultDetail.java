package com.example.tankapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultDetail extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);
        Bundle b = getIntent().getExtras();
        String item = b.getString("item");

        Toast.makeText(ResultDetail.this, item,
                Toast.LENGTH_LONG).show();
    }


}
