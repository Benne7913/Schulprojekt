package com.example.tankapp.model;

//Enthält alle Informationen


import com.example.tankapp.objects.Gasstation;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class General_Model implements Serializable
{
    private String radius = "10";
    private String anzeige = "100";
    // Creating a list

    private String lat="";
    private String lng="";
    private ArrayList<Gasstation> m_kgasstation;


    public General_Model()
    {

    }

    //Getter & Setter
    public String getRadius() { return radius; }
    public void setRadius(String radius) { this.radius = radius; }


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

    public ArrayList<Gasstation> getGasstaions()
    {
        return this.m_kgasstation;
    }

    public void setGasstaions(ArrayList<Gasstation> pkcoGasstations)
    {
        this.m_kgasstation = pkcoGasstations;
    }

    public String getAnzeige()
    {
        return this.anzeige;
    }

    public void setAnzeige(String piAnzeige)
    {
        this.anzeige = piAnzeige;
    }
}
