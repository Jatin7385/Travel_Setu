package com.example.hackathon_project.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PlaceModel implements Serializable {

    private String name;
    private String image;
    private String lat;
    private String lng;

    public PlaceModel() {
    }

    public PlaceModel(String name, String image, String lat, String lng) {
        this.name = name;
        this.image = image;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() { return name; }

    public String getImage() { return image; }

    public void setName(String name) { this.name = name; }

    public void setImage(String image) { this.image = image; }

    public String getLat() { return lat; }

    public String getLng() { return lng; }

    public void setLat(String name) { this.lat = lat; }

    public void setLng(String image) { this.lng = lng; }
}