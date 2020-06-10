package com.example.tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable SearchAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
