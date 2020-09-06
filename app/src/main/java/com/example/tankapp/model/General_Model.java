package com.example.tankapp.model;

//Enthält alle Informationen


import com.example.tankapp.objects.Gasstation;

import java.io.Serializable;
import java.util.List;

public class General_Model implements Serializable
{
    private int radius = 10;
    // Creating a list
    public List<Gasstation> m_kTankstellen;
    private String lat="";
    private String lng="";


    public General_Model()
    {

    }

    //Getter & Setter
    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
