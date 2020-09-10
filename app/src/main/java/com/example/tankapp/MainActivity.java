package com.example.tankapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tankapp.activitys.Setting_Activity;
import com.example.tankapp.activitys.Appinfo_Activity;
import com.example.tankapp.activitys.Result_Activity;
import com.example.tankapp.activitys.adapter.Result_Adapter;
import com.example.tankapp.model.General_Model;
import com.example.tankapp.objects.Gasstation;
import com.example.tankapp.services.API_Service;
import com.example.tankapp.utils.Utils;
import com.spark.submitbutton.SubmitButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

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

    private BroadcastReceiver MyReceiver = null;
    private GpsTracker gpsTracker;
    private General_Model m_kGeneralModel;
    private SubmitButton SearchButton;
    private MainActivity rcView;

//////////////////////////////////////////////////////////////
// OVERRIDE METHODS
//////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //initialize model
        if (m_kGeneralModel == null)
            m_kGeneralModel = new General_Model();

        //register internet-receivers
        MyReceiver = new MyReceiver();
        broadcastIntent();

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        ///////////////////////////////////////////////
        //Search Button
        ///////////////////////////////////////////////
        //assign searchbutton
        SearchButton = (SubmitButton) findViewById(R.id.search_button);
        //start the search
        SearchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int duration = 3000;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    public void run()
                    {
                        callAPIService();  //start the API call
                }   }, duration);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.allgemein: //start settings_activity
                    Intent intentAllg = new Intent(this, Setting_Activity.class);
                    intentAllg.putExtra("sendToSettingActivity", m_kGeneralModel);
                    startActivityForResult(intentAllg , 1);
                    return true;
            case R.id.info://start information_activity
                    Intent intentInfo = new Intent(this, Appinfo_Activity.class);
                    this.startActivity(intentInfo);
                    return true;
            default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //receive the modal back from settings_activity
    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) //receive data from setting_activity
            if (resultCode == RESULT_OK)
                m_kGeneralModel = (General_Model) data.getSerializableExtra("receiveToMainActivityFromSetting");

        if (requestCode == 2) //receive data from result_activity
            if (resultCode == RESULT_OK)
                m_kGeneralModel = (General_Model) data.getSerializableExtra("receiveToMainActivityFromResult");

        if(resultCode == RESULT_CANCELED) //problems with received data
            Toast.makeText(this, "Daten konnten nicht zurückgestellt werden",0).show();
    }

//////////////////////////////////////////////////////////////
// OWN METHODS
//////////////////////////////////////////////////////////////
    //get the current GPS-Location
    public boolean getCurrentLocation(){
        gpsTracker = new GpsTracker(this);
        if(gpsTracker.canGetLocation()){
            m_kGeneralModel.setLat(String.valueOf(gpsTracker.getLatitude()));
            m_kGeneralModel.setLng(String.valueOf(gpsTracker.getLongitude()));
            return true;
        }else{
            gpsTracker.showSettingsAlert();
            return false;
        }
    }

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //create gasstations
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
        m_kGeneralModel.setGasstaions(lkGasstations);

        callResultActivity();
    }

    //start Result_Activity
    private void callResultActivity()
    {
        Intent intentRes = new Intent(MainActivity.this, Result_Activity.class);
        intentRes.putExtra("sendToResultActivity", m_kGeneralModel);
        startActivityForResult(intentRes , 2);
    }

    //start the API call
    private void callAPIService()
    {
        if(getCurrentLocation())
        {
            startService(new Intent(this, API_Service.class));
            API_Service.ereignisHandler = ereignisHandler;
            API_Service.url = Utils.getAPIString(m_kGeneralModel.getLat(), m_kGeneralModel.getLng(), m_kGeneralModel.getRadius());
            API_Service.view = this;

            stopService(new Intent(this, API_Service.class));
        }
    }
}
