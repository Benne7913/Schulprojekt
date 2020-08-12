package com.example.tankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable SearchAnimation;
    private BroadcastReceiver MyReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyReceiver = new MyReceiver();
        broadcastIntent();
        ImageView SearchImage = (ImageView) findViewById(R.id.search_image);
        SearchImage.setBackgroundResource(R.drawable.animation);
        SearchAnimation = (AnimationDrawable) SearchImage.getBackground();

        SearchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchAnimation.start();
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
}
