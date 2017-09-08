package com.example.antoinelafont.dijoncenteral.Services;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.antoinelafont.dijoncenteral.Classes.POILocation;
import com.example.antoinelafont.dijoncenteral.Classes.POIPosition;
import com.example.antoinelafont.dijoncenteral.Classes.PointOfInterest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine LAFONT on 08/09/2017.
 */

public class POIService extends AsyncTask<String, Void, Void> {

    public String myUrl;
    public List<PointOfInterest> listOfPOI = new ArrayList<>();

    public POIService(String url) {
        myUrl = url;
    }

    public List<PointOfInterest> getDataFromStream() {
        try {
            URL url = new URL(myUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream in = connection.getInputStream();

            JsonReader reader = new JsonReader(new InputStreamReader(in));

            List<PointOfInterest> listOfPOI = new ArrayList<>();

            reader.beginArray();
            while (reader.hasNext()) {
                listOfPOI.add(readMessage(reader));
            }
            reader.endArray();

            return listOfPOI;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PointOfInterest readMessage(JsonReader reader) throws IOException {
        PointOfInterest poi = new PointOfInterest();

        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();

                switch (name) {
                    case "id":
                        poi.id = reader.nextString();
                        break;
                    case "type":
                        poi.type = reader.nextString();
                        break;
                    case "name":
                        poi.name = reader.nextString();
                        break;
                    case "location":
                        poi.location = readLocation(reader);
                        break;
                    default: reader.skipValue(); break;
                }
            }
            reader.endObject();

            return poi;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public POILocation readLocation(JsonReader reader) {
        try {
            POILocation location = new POILocation();

            reader.beginObject();
            while(reader.hasNext()) {
                String name = reader.nextName();

                switch(name) {
                    case "adress":
                        location.address = reader.nextString();
                        break;
                    case "postalCode":
                        location.postalCode = reader.nextString();
                        break;
                    case "city":
                        location.city = reader.nextString();
                        break;
                    case "position":
                        location.position = readPosition(reader);
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();

            return location;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public POIPosition readPosition(JsonReader reader) {
        try {
            POIPosition position = new POIPosition();

            reader.beginObject();
            while(reader.hasNext()) {
                String name = reader.nextName();

                switch(name) {
                    case "lat":
                        position.lat = reader.nextDouble();
                        break;
                    case "lon":
                        position.lon = reader.nextDouble();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();

            return position;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            listOfPOI = getDataFromStream();

            return null;
        } catch (Exception e) {
            return null;
        }

    }
}
