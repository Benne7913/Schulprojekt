package com.example.tankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Results_Actvity extends AppCompatActivity {

    private RecyclerView rcView;
    ArrayList<Tankstelle> m_kTankstellen;
    private static ProgressDialog dialog;

    private EreignisHandler ereignisHandler = new EreignisHandler();

    private class EreignisHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String message = (String) msg.obj; //Extract the string from the Message

            toArray(message);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Holt die mitgegebene Tankstellenliste
       //Bundle bundle = getIntent().getExtras();
        //m_kTankstellen = (ArrayList<Tankstelle>) bundle.getSerializable("tankstellenliste");

        dialog.show(this, "Loading", "Wait while loading...");



        startService(new Intent(this, APIService.class));
        APIService.ereignisHandler = ereignisHandler;
        APIService.view = this;

        stopService(new Intent(this, APIService.class));



    }

    private void toArray(String json)
    {
        ArrayList<Tankstelle> stelle = new ArrayList<>();
        try
        {
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("stations");

            for (int i = 0; i < arr.length(); i++)
            {
                Tankstelle gasstation = new Tankstelle();
                gasstation.setName(arr.getJSONObject(i).getString("name"));
                gasstation.setId(arr.getJSONObject(i).getString("id"));
                gasstation.setBrand(arr.getJSONObject(i).getString("brand"));
                gasstation.setStreet(arr.getJSONObject(i).getString("street"));
                gasstation.setPlace(arr.getJSONObject(i).getString("place"));
                gasstation.setLat(arr.getJSONObject(i).getDouble("lat"));
                gasstation.setLng(arr.getJSONObject(i).getDouble("lng"));
                gasstation.setDiesel(arr.getJSONObject(i).getDouble("diesel"));
                gasstation.setE5(arr.getJSONObject(i).getDouble("e5"));
                gasstation.setE10(arr.getJSONObject(i).getDouble("e10"));
                gasstation.isOpen(arr.getJSONObject(i).getBoolean("isOpen"));
                gasstation.setHousnumber(arr.getJSONObject(i).getString("houseNumber"));
                gasstation.setPostCode(arr.getJSONObject(i).getInt("postCode"));
                stelle.add(gasstation);
            }

        } catch (Throwable t)
        {
            Log.e("Error JSON", "Could not parse malformed JSON: \"" + json + "\"");
        }
        init(stelle);

    }


    private void init(ArrayList<Tankstelle> m_kTankstellen)
    {
        //RecyclerView mit Klasse verbinden
        rcView = findViewById(R.id.view);
        Intent intent = getIntent();

        Results_Adapter adapter = new Results_Adapter(this, m_kTankstellen );
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, APIService.class));
        APIService.ereignisHandler = ereignisHandler;
        APIService.view = this;

        stopService(new Intent(this, APIService.class));


    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

    }

}