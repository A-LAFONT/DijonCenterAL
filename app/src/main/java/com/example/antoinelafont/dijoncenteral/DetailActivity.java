package com.example.antoinelafont.dijoncenteral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.antoinelafont.dijoncenteral.Classes.PointOfInterest;
import com.example.antoinelafont.dijoncenteral.Services.POIService;

import java.util.ArrayList;

/**
 * Created by Antoine LAFONT on 20/09/2017.
 */

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {

    public static double LAT, LON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Intent i = getIntent();

        POIService service = new POIService("https://my-json-server.typicode.com/lpotherat/pois/pois"){
            @Override
            protected void onPostExecute( ArrayList<PointOfInterest> listOfPOI) {
                super.onPostExecute(listOfPOI);

                TextView tvName = (TextView) findViewById(R.id.tvName);
                TextView tvCity = (TextView) findViewById(R.id.tvCity);
                TextView tvAddress = (TextView) findViewById(R.id.tvAddress);

                int id = (int)i.getLongExtra("id", 0);

                PointOfInterest poi = listOfPOI.get(id);

                LAT = poi.location.position.lat;
                LON = poi.location.position.lon;

                tvName.setText(poi.name);
                tvCity.setText(poi.location.city + ", ");
                tvAddress.setText(poi.location.address);
            }
        };

        service.execute();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        if(LAT != 0 && LON != 0)
        {
            LatLng pos = new LatLng(LAT, LON);
            googleMap.addMarker(new MarkerOptions().position(pos));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
        }
    }
}
