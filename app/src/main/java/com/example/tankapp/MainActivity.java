package com.example.tankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable SearchAnimation;
    private BroadcastReceiver MyReceiver = null;
    private GpsTracker gpsTracker;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyReceiver = new MyReceiver();
        broadcastIntent();
        ImageView SearchImage = (ImageView) findViewById(R.id.search_image);
        SearchImage.setBackgroundResource(R.drawable.animation);
        SearchAnimation = (AnimationDrawable) SearchImage.getBackground();

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        SearchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                SearchAnimation.start();
                int duration = 0;
                for (int i=0; i<SearchAnimation.getNumberOfFrames(); i++){
                    duration = duration + SearchAnimation.getDuration(i);
                }
                duration += 1200;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    public void run() {
                        getLocation(view);
                        Intent Results = new Intent(MainActivity.this, Results.class);
                        MainActivity.this.startActivity(Results);
                    }
                }, duration);

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
                    Intent intentAllg = new Intent(this, AllgEinstellungen.class);
                    this.startActivity(intentAllg);
                    return true;
            case R.id.info:
                    Intent intentInfo = new Intent(this, Info.class);
                    this.startActivity(intentInfo);
                    return true;
            default:
                    return super.onOptionsItemSelected(item);
        }

    }

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    public void getLocation(View view){
        gpsTracker = new GpsTracker(MainActivity.this);
        if(gpsTracker.canGetLocation()){
            latitude = String.valueOf(gpsTracker.getLatitude());
            longitude = String.valueOf(gpsTracker.getLongitude());
            Toast.makeText(MainActivity.this, latitude + " + " + longitude,
                    Toast.LENGTH_LONG).show();
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

}
