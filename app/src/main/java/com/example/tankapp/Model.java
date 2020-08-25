package com.example.tankapp;

//Enthält alle Informationen


import java.io.Serializable;
import java.util.List;

public class Model implements Serializable
{
    private int radius = 5;
    // Creating a list
    public List<Tankstelle> m_kTankstellen;
    private String lat="";
    private String lng="";


    public Model()
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
