package com.example.tankapp.utils;

import java.io.Serializable;

public class Filter implements Serializable {


    private double range = 5.0;



    public Filter()
    {

    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

}
