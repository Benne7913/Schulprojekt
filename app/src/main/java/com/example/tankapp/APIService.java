package com.example.tankapp;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class APIService extends Service
{
    public static Handler ereignisHandler;
    public static Results_Actvity view;
    public static String url = " ";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

       if(url.isEmpty()) { return; }


        RequestQueue queue = Volley.newRequestQueue(view);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Message msg = Message.obtain(); // Creates an new Message instance
                        msg.obj = response; // Put the string into Message, into "obj" field.
                        msg.setTarget(ereignisHandler); // Set the Handler
                        msg.sendToTarget(); //Send the message
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}


