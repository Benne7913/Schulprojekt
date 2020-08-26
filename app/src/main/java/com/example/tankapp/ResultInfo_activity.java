package com.example.tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultInfo_activity extends AppCompatActivity {

    private Tankstelle m_kTankstelle;
    private TextView textName;
    private ImageView imageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultinfo);

        textName = findViewById(R.id.textViewName);
        imageLogo = findViewById(R.id.imageviewGassatationLogo);

        getData();
        setData();
    }

    private void getData()
    {
        if(getIntent().hasExtra("tankstelle"))
        {
            Intent intent = getIntent();
            m_kTankstelle = (Tankstelle) intent.getSerializableExtra("tankstelle");

        }else{
            Toast.makeText(this, "Keine Daten verfügbar", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData()
    {
        textName.setText(m_kTankstelle.getName());
        imageLogo.setImageResource(m_kTankstelle.getImageRessource());
    }
}