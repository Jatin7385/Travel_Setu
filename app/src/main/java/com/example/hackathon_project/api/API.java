/*
package com.example.hackathon_project.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    String BASE_URL = "https://maps.googleapis.com/maps/api/";

    @GET("place/nearbysearch/json")
    Call<JSONObject> getNearby(@Query("location") String location, 
    @Query("type") String type,  @Query("key") String key);

    @GET("place/textsearch/json")
    Call<JSONObject> getAutoComplete(@Query("query") String query, @Query("key") String key);

}

 */