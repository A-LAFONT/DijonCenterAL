package com.example.antoinelafont.dijoncenteral;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antoinelafont.dijoncenteral.Adapter.POIAdapter;
import com.example.antoinelafont.dijoncenteral.Classes.PointOfInterest;
import com.example.antoinelafont.dijoncenteral.Services.POIService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static int MY_PERMISSIONS_RECEIVE_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                // Show an explanation to the user *asynchronously* -- don't block this thread waiting for the user's response! After the user sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_RECEIVE_SMS);
                // MY_PERMISSIONS_BROADCAST_SMS is an app-defined int constant. The callback method gets the result of the request.
            }
        }

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

                TextView tvNumberPOI = (TextView) findViewById(R.id.tvNumberPOI);
                tvNumberPOI.setText(String.valueOf(listOfPOI.size()));
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

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_RECEIVE_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other permissions this app might request
        }
    }
}
