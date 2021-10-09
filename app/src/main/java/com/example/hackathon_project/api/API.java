package com.example.hackathon_project;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    String BASE_URL = "https://maps.googleapis.com/maps/api/";

    @GET("place/nearbysearch/json")
    Call<JSONObject> getNearbyHome(@Query("location") String location, 
    @Query("type") String type,  @Query("key") String key);

    @GET("place/autocomplete/json")
    Call<JSONObject> getAutoComplete(@Query("input") String input, @Query("key") String key);
}