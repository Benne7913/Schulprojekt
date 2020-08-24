package com.example.tankapp;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RestAPI
{
    JSONArray arr;
    ArrayList<Tankstelle> stelle1;
    public RestAPI()
    {
        stelle1 = new ArrayList<>();

    }

    public void getInfoFromAPI(Context ct)
    {
        String url = "https://creativecommons.tankerkoenig.de/json/list.php?lat=52.521&lng=13.438&rad=1.5&sort=dist&type=all&apikey=00000000-0000-0000-0000-000000000002";


        RequestQueue queue = Volley.newRequestQueue(ct);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        stelle1 = toArray(response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }




    private ArrayList<Tankstelle> toArray(String json)
    {
        ArrayList<Tankstelle> stelle = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(json);


             arr = obj.getJSONArray("stations");
            for (int i = 0; i < arr.length(); i++) {
                Tankstelle gasstation = new Tankstelle();
                gasstation.setName(arr.getJSONObject(i).getString("name"));
                gasstation.setId(arr.getJSONObject(i).getString("id"));
                gasstation.setBrand(arr.getJSONObject(i).getString("brand"));
                gasstation.setStreet(arr.getJSONObject(i).getString("street"));
                gasstation.setPlace(arr.getJSONObject(i).getString("place"));
                gasstation.setLat(arr.getJSONObject(i).getDouble("lat"));
                gasstation.setLng(arr.getJSONObject(i).getDouble("lng"));
                gasstation.setDiesel(arr.getJSONObject(i).getDouble("diesel"));
                gasstation.setE5(arr.getJSONObject(i).getDouble("e5"));
                gasstation.setE10(arr.getJSONObject(i).getDouble("e10"));
                gasstation.isOpen(arr.getJSONObject(i).getBoolean("isOpen"));
                gasstation.setHousnumber(arr.getJSONObject(i).getString("houseNumber"));
                gasstation.setPostCode(arr.getJSONObject(i).getInt("postCode"));
                stelle.add(gasstation);
            }

        } catch (Throwable t) {
            Log.e("Error JSON", "Could not parse malformed JSON: \"" + json + "\"");
        }
        return stelle;
    }




}
