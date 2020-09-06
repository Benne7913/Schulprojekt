package com.example.tankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.tankapp.activitys.Setting_Activity;
import com.example.tankapp.activitys.Appinfo_Activity;
import com.example.tankapp.activitys.Result_Activity;
import com.example.tankapp.model.General_Model;
import com.spark.submitbutton.SubmitButton;


public class MainActivity extends AppCompatActivity{

    private BroadcastReceiver MyReceiver = null;
    private GpsTracker gpsTracker;
    private General_Model m_kGeneralModel;
    private SubmitButton SearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Model initalisieren
        m_kGeneralModel = new General_Model();

        //Registrieren des Internet-Receivers
        MyReceiver = new MyReceiver();
        broadcastIntent();


        //Suchen Button zuweisen
        SearchButton = (SubmitButton) findViewById(R.id.search_button);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        SearchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int duration = 3000;
                Handler handler = new Handler();
                //Warten bis die Animation zu Ende ist
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if(getLocation()) {
                            //Aufruf der neuen Activity
                            Intent intentRes = new Intent(MainActivity.this, Result_Activity.class);
                            intentRes.putExtra("model", m_kGeneralModel);
                            startActivity(intentRes);
                        }
                    }}, duration);
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
            case R.id.allgemein:
                    Intent intentAllg = new Intent(this, Setting_Activity.class);
                    intentAllg.putExtra("model", m_kGeneralModel);
                    this.startActivity(intentAllg);
                    return true;
            case R.id.info:
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

    public boolean getLocation(){
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
}
