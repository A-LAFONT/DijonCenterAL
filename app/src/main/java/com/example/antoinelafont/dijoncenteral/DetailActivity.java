package com.example.antoinelafont.dijoncenteral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.antoinelafont.dijoncenteral.Classes.PointOfInterest;
import com.example.antoinelafont.dijoncenteral.Services.POIService;

import java.util.ArrayList;

/**
 * Created by Antoine LAFONT on 20/09/2017.
 */

public class DetailActivity extends AppCompatActivity {

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

                PointOfInterest poi = listOfPOI.get(i.getIntExtra("id", 0));

                tvName.setText(poi.name);
                tvCity.setText(poi.location.city + ", ");
                tvAddress.setText(poi.location.address);
            }
        };

        service.execute();
    }
}
