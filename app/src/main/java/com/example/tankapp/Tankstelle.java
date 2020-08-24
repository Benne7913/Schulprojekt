package com.example.tankapp;

import java.io.Serializable;

public class Tankstelle implements Serializable {

    private String name = "test";
    private String range = "5";

    public Tankstelle()
    {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
