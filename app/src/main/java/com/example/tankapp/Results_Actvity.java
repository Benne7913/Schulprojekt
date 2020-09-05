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

    public static boolean isChildActiv = false;
    private RecyclerView rcView;
    private Model m_kModel;

/////////////////////////////////////////////////////////////////////////////////
    private EreignisHandler ereignisHandler = new EreignisHandler();

    class EreignisHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String message = (String) msg.obj;
            toArray(message);
        }
    }
/////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Model bekommen
        Intent intent = getIntent();
        m_kModel = (Model) intent.getSerializableExtra("model");

        //Ladebalken
       // runLoading();

        //Service call
        callAPIService();
    }

    //create and fill the List
    private void createItemList(ArrayList<Tankstelle> pkGasstations)
    {
        //RecyclerView mit Klasse verbinden
        rcView = findViewById(R.id.view);
        Intent intent = getIntent();

        Results_Adapter adapter = new Results_Adapter(this, pkGasstations );
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void callAPIService()
    {
        startService(new Intent(this, APIService.class));
        APIService.ereignisHandler = ereignisHandler;
        APIService.url = getAPIString();
        APIService.view = this;

        stopService(new Intent(this, APIService.class));
    }

    //create API [URL] String
    private String getAPIString()
    {
        //String url = "https://creativecommons.tankerkoenig.de/json/list.php?lat=52.521&lng=13.438&rad=10&sort=dist&type=all&apikey=00000000-0000-0000-0000-000000000002";
        String url = "https://creativecommons.tankerkoenig.de/json/list.php?lat=" + m_kModel.getLat()+
                        "&lng="+ m_kModel.getLng()+
                        "&rad="+m_kModel.getRadius()+
                        "&sort=dist&type=all&apikey=00000000-0000-0000-0000-000000000002";
        return url;
    }

    //Ladebalken
    private void runLoading()
    {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Tankstellendaten");
        progress.setMessage("Daten werden abgerufen");
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        callAPIService();
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy(){
        if (!isChildActiv)
        {
            super.onDestroy();
        }

    }


    private void toArray(String json)
    {
        ArrayList<Tankstelle> lkGasstations = new ArrayList<>();
        try
        {
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("stations");

            for (int i = 0; i < arr.length(); i++)
            {
                Tankstelle gasstation = new Tankstelle();
                gasstation.setName      (arr.getJSONObject(i).getString ("name"         ));
                gasstation.setId        (arr.getJSONObject(i).getString ("id"           ));
                gasstation.setBrand     (arr.getJSONObject(i).getString ("brand"        ));
                gasstation.setDist      (arr.getJSONObject(i).getDouble ("dist"         ));
                gasstation.setStreet    (arr.getJSONObject(i).getString ("street"       ));
                gasstation.setPlace     (arr.getJSONObject(i).getString ("place"        ));
                gasstation.setLat       (arr.getJSONObject(i).getDouble ("lat"          ));
                gasstation.setLng       (arr.getJSONObject(i).getDouble ("lng"          ));
                gasstation.setDiesel    (arr.getJSONObject(i).getDouble ("diesel"       ));
                gasstation.setE5        (arr.getJSONObject(i).getDouble ("e5"           ));
                gasstation.setE10       (arr.getJSONObject(i).getDouble ("e10"          ));
                gasstation.setOpen      (arr.getJSONObject(i).getBoolean("isOpen"       ));
                gasstation.setHousnumber(arr.getJSONObject(i).getString ("houseNumber"  ));
                gasstation.setPostCode  (arr.getJSONObject(i).getInt    ("postCode"     ));

                lkGasstations.add(gasstation);
            }
        } catch (Throwable t)
        {
            Log.e("Error JSON", "Could not parse malformed JSON: \"" + json + "\"");
        }
        createItemList(lkGasstations);
    }
}