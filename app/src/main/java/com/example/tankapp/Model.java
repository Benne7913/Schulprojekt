package com.example.tankapp;

//Enthält alle Informationen

import java.io.Serializable;

public class Model implements Serializable
{
    private int radius = 5;

    public Model()
    {

    }

    //Getter & Setter
    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }
}
