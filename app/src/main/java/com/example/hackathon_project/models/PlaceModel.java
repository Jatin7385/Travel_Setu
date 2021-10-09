package com.example.hackathon_project.models;

public class PlaceModel {

    private String name;
    private String image;
    private String lat;
    private String lng;

    public AutoCompModel(String name, String image, String lat, String lng) {
        this.name = name;
        this.image = image;
        this.lat = lst;
        this.lng = lng;
    }

    public String getName() { return name; }

    public String getImage() { return image; }

    public String setName(String name) { this.name = name; }

    public String setImage(String image) { this.image = image; }

    public String getLat() { return lat; }

    public String getLng() { return lng; }

    public String setLat(String name) { this.lat = lat; }

    public String setLng(String image) { this.lng = lng; }
}