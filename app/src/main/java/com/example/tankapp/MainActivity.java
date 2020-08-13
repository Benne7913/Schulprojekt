package com.example.tankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int FAST_UPDATE_INTERVAL = 5;
    public static final int PERMISSIONS_FINE_LOCATION = 99;
    AnimationDrawable SearchAnimation;
    private BroadcastReceiver MyReceiver = null;
    String lat;
    String alt;

    //Standort
    LocationRequest locationRequest;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyReceiver = new MyReceiver();
        broadcastIntent();
        ImageView SearchImage = (ImageView) findViewById(R.id.search_image);
        SearchImage.setBackgroundResource(R.drawable.animation);
        SearchAnimation = (AnimationDrawable) SearchImage.getBackground();

        //Standort
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(1000 * DEFAULT_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(1000 * FAST_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // Evtl in den Optionen einen Switch einbauen für GPS Sensor oder Wifi + Funkmast

        SearchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchAnimation.start();
                updateGPS();
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
            case R.id.anzeige:
                    Intent intentAnzeige = new Intent(this, Anzeige.class);
                    this.startActivity(intentAnzeige);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case PERMISSIONS_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    updateGPS();
                }
                else {
                    Toast.makeText(this, "Keine Berechtigungen um richtig zu arbeiten", Toast.LENGTH_SHORT).show();
                    finish();
                }
            break;
        }
    }

    private void updateGPS(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if(location != null){
                        lat = String.valueOf(location.getLatitude());

                        if(location.hasAltitude()){
                            alt = String.valueOf(location.getAltitude());
                        }
                        Toast.makeText(getApplicationContext(),
                                alt + " + " + lat,
                                Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }
        }

    }


}
