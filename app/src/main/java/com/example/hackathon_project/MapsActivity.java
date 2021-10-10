package com.example.hackathon_project;

import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathon_project.models.PlaceModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googlemap;
    private Button back;
    private PlaceModel placeModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello");
        setContentView(R.layout.activity_maps);

        back = findViewById(R.id.map_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps_fragment);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googlemap = googleMap;
        Intent i = getIntent();
        placeModel = (PlaceModel) i.getSerializableExtra("PlaceModel");
        String lat,lng,name;
        Double l,l1;
        lat = placeModel.getLat();
        lng = placeModel.getLng();
        l = Double.parseDouble(lat);
        l1 = Double.parseDouble(lng);
        name = placeModel.getName();

        LatLng latLng;
        System.out.println(lat + " , " + lng);
        if(!lat.equals(null) && !lng.equals(null)) {
            latLng = new LatLng(l, l1);
        }
        else
            {
                name = "Maharashtra";
                latLng = new LatLng(19.7515,75.7139);
            }

        List<LatLng> latlng = new ArrayList<>();
        latlng.add(latLng);
        addHeatMap(latlng);


        googlemap.addMarker(new MarkerOptions().position(latLng).title(name));
        googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f));
    }

    private void addHeatMap(List<LatLng> latLng) {
        List<LatLng> latLngs = latLng;

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                .data(latLngs)
                .build();

        // Add a tile overlay to the map, using the heat map tile provider.
        TileOverlay overlay = googlemap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
    }
}