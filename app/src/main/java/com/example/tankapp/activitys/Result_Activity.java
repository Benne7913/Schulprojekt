package com.example.tankapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.tankapp.services.API_Service;
import com.example.tankapp.model.General_Model;
import com.example.tankapp.R;
import com.example.tankapp.activitys.adapter.Result_Adapter;
import com.example.tankapp.objects.Gasstation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Result_Activity extends AppCompatActivity {

    public static boolean isChildActiv = false;
    private RecyclerView rcView;
    private General_Model m_kGeneralModel;

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
        setContentView(R.layout.activity_result);

        //Model bekommen
        Intent intent = getIntent();
        m_kGeneralModel = (General_Model) intent.getSerializableExtra("model");

        //Ladebalken
       // runLoading();

        //Service call
        callAPIService();
    }

    //create and fill the List
    private void createItemList(ArrayList<Gasstation> pkGasstations)
    {
        //RecyclerView mit Klasse verbinden
        rcView = findViewById(R.id.view);
        Intent intent = getIntent();

        Result_Adapter adapter = new Result_Adapter(this, pkGasstations );
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void callAPIService()
    {
        startService(new Intent(this, API_Service.class));
        API_Service.ereignisHandler = ereignisHandler;
        API_Service.url = getAPIString();
        API_Service.view = this;

        stopService(new Intent(this, API_Service.class));
    }

    //create API [URL] String
    private String getAPIString()
    {
        //String url = "https://creativecommons.tankerkoenig.de/json/list.php?lat=52.521&lng=13.438&rad=10&sort=dist&type=all&apikey=00000000-0000-0000-0000-000000000002";
        String url = "https://creativecommons.tankerkoenig.de/json/list.php?lat=" + m_kGeneralModel.getLat()+
                        "&lng="+ m_kGeneralModel.getLng()+
                        "&rad="+ m_kGeneralModel.getRadius()+
                        "&sort=dist&type=all&apikey=4d78ed57-c1c5-2e89-8e82-c8651b3a437c";
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
        ArrayList<Gasstation> lkGasstations = new ArrayList<>();
        try
        {
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("stations");

            for (int i = 0; i < arr.length(); i++)
            {
                Gasstation gasstation = new Gasstation();
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