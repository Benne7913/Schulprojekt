package com.example.tankapp;

//Enthält alle Informationen


import java.util.List;

public class Model
{
    private int radius = 5;
    // Creating a list
    public List<Tankstelle> m_kTankstellen;

    public Model()
    {

    }

    //Getter & Setter
    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }
}
