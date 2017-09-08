package com.example.antoinelafont.dijoncenteral;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antoinelafont.dijoncenteral.Services.POIService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        POIService service = new POIService("https://my-json-server.typicode.com/lpotherat/pois/pois");

        service.execute();
    }
}
