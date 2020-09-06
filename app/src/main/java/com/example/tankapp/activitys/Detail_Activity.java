package com.example.tankapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tankapp.R;
import com.example.tankapp.objects.Gasstation;

import java.text.NumberFormat;
import java.util.Locale;

public class Detail_Activity extends AppCompatActivity {

    private Gasstation m_kGasstation;

    private TextView    textName;
    private TextView    textStreet;
    private TextView    textCity;
    private TextView    textDiesel;
    private TextView    textE10;
    private TextView    textE5;
    private TextView    textDistance;
    private ImageView   imageLogo;
    private ImageView   imageOpenClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textName    = findViewById(R.id.textTankstellenname);
        textStreet  = findViewById(R.id.text_result_Street);
        textCity    = findViewById(R.id.text_result_City);
        textDiesel    = findViewById(R.id.result_text_diesel);
        textE10    = findViewById(R.id.result_text_E10);
        textE5    = findViewById(R.id.result_text_E5);
        textDistance   = findViewById(R.id.result_text_distance);
        imageLogo   = findViewById(R.id.result_detail_gasstationlogo);
        imageOpenClose   = findViewById(R.id.result_image_openclose);



        getData();
        setData();
    }

    private void getData()
    {
        if(getIntent().hasExtra("tankstelle"))
        {
            Intent intent = getIntent();
            m_kGasstation = (Gasstation) intent.getSerializableExtra("tankstelle");

        }else{
            Toast.makeText(this, "Keine Daten verfügbar", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData()
    {
        textName.setText(m_kGasstation.getBrand());
        textStreet.setText(m_kGasstation.getStreet());

        String lsCityplace = m_kGasstation.getPostCode() + " " + m_kGasstation.getPlace();
        textCity.setText(lsCityplace);

        //Distance
        textDistance.setText(m_kGasstation.getDist() + " km");

        //Price
        textDiesel.setText("Diesel: " + priceEuro(m_kGasstation.getDiesel()));
        textE10.setText("E10:     " + priceEuro(m_kGasstation.getE10()));
        textE5.setText("E5:       " + priceEuro(m_kGasstation.getE5()));

        //open or close
        if(m_kGasstation.isOpen())
            imageOpenClose.setImageResource(R.drawable.oeffnen);
        else
            imageOpenClose.setImageResource(R.drawable.schliessen);

        imageLogo.setImageResource(m_kGasstation.getImageRessource());


    }

    private String priceEuro(Double pdPreis)
    {
        Locale locale = Locale.GERMANY;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String s = numberFormat.format(pdPreis);
        return s;
    }
}