package com.example.tankapp;

import java.io.Serializable;

public class Tankstelle implements Serializable {

    private String id;
    private String name ;
    private String brand ;
    private String street ;
    private String place ;
    private double lat ;
    private double lng ;
    private double dist ; //range
    private double diesel ;
    private double e5 ;
    private double e10 ;
    private boolean isOpen = false;
    private String housnumber;
    private int postCode;
    private int imageRessource;

    public Tankstelle()
    {

    }


    public int getImageRessource() { return imageRessource; }

    public void setImageRessource(int imageRessource) { this.imageRessource = imageRessource; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public double getDiesel() {
        return diesel;
    }

    public void setDiesel(double diesel) {
        this.diesel = diesel;
    }

    public double getE5() {
        return e5;
    }

    public void setE5(double e5) {
        this.e5 = e5;
    }

    public double getE10() {
        return e10;
    }

    public void setE10(double e10) {
        this.e10 = e10;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getHousnumber() {
        return housnumber;
    }

    public void setHousnumber(String housnumber) {
        this.housnumber = housnumber;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
