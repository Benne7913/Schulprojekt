package com.example.tankapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

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

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //initialize model
        if (m_kGeneralModel == null)
            m_kGeneralModel = new General_Model();

        //register internet-receivers
        MyReceiver = new MyReceiver();
        broadcastIntent();


        //assign searchbutton
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
                //wait for animation ends
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if(getLocation()) {
                            //call new activity
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
                    intentAllg.putExtra("Model", m_kGeneralModel);

                    startActivityForResult(intentAllg , 1);
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
    //receive the modal back from settings_activity
    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
            if (resultCode == RESULT_OK) //data was receive
                m_kGeneralModel = (General_Model) data.getSerializableExtra("result");
        if(resultCode == RESULT_CANCELED)//problem with receive data
            Toast.makeText(this, "Daten konnten nicht zurückgestellt werden",0).show();
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
