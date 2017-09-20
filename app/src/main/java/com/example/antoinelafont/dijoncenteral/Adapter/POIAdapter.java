package com.example.antoinelafont.dijoncenteral.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.antoinelafont.dijoncenteral.Classes.PointOfInterest;
import com.example.antoinelafont.dijoncenteral.R;

import java.util.ArrayList;

/**
 * Created by Antoine LAFONT on 20/09/2017.
 */

public class POIAdapter extends ArrayAdapter<PointOfInterest> {

    public POIAdapter(Context context, ArrayList<PointOfInterest> pois) {
        super(context, 0, pois);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PointOfInterest poi = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_poi, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvAdress = (TextView) convertView.findViewById(R.id.tvAddress);

        tvName.setText(poi.name);
        tvAdress.setText(poi.location.city + ", " + poi.location.address);

        return convertView;
    }
}
