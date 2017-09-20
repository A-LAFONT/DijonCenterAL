package com.example.antoinelafont.dijoncenteral;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.antoinelafont.dijoncenteral.Adapter.POIAdapter;
import com.example.antoinelafont.dijoncenteral.Classes.PointOfInterest;
import com.example.antoinelafont.dijoncenteral.Services.POIService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        POIService service = new POIService("https://my-json-server.typicode.com/lpotherat/pois/pois"){
            @Override
            protected void onPostExecute( ArrayList<PointOfInterest> listOfPOI) {
                super.onPostExecute(listOfPOI);
                POIAdapter poiAdapter = new POIAdapter(MainActivity.this, listOfPOI);
                ListView listView = (ListView) findViewById(R.id.listViewPOI);
                listView.setAdapter(poiAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this, DetailActivity.class);
                        i.putExtra("id", id); //((PointOfInterest)parent.getItemAtPosition(position)).id
                        startActivity(i);
                    }
                });
            }
        };

        service.execute();

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddItineraryActivity.class);
                startActivity(i);
            }
        });
    }
}
