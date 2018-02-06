package com.example.siemaneczko.map;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.siemaneczko.map.R.id.spinnerMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private List<String> citiesList = new ArrayList<String>();
    private List<Double> wList = new ArrayList<Double>();
    private List<Double> lList = new ArrayList<Double>();
    private int firstSpinnerPosition = 0;
    private boolean mapStyle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        loadMap();
    }

    public void loadMap(){
        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.miasta);
        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();

        while(scanner.hasNextLine()){
            builder.append(scanner.nextLine());
        }
        parseJson(builder.toString());
    }

    private void parseJson(String s){
        JSONObject root  = null;
        try {
            root = new JSONObject(s);
            JSONArray cities = root.getJSONArray("cities");
            for (int i=0; i < cities.length(); i++){
                JSONObject city  = cities.getJSONObject(i);
                citiesList.add(city.getString("name"));
                wList.add(city.getDouble("w"));
                lList.add(city.getDouble("l"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citiesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItem = (Spinner) findViewById(spinnerMap);
        sItem.setAdapter(adapter);

        Spinner sItem2 = (Spinner) findViewById(R.id.spinnerMap2);
        sItem2.setAdapter(adapter);


        sItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    firstSpinnerPosition = position;
                    changePoint(position);
                }else{
                    mMap.clear();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        sItem2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //GoogleMap map;
                if(position != 0) {
                    changeLine(position);
                }else{
                    mMap.clear();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

    }

    private void changePoint(int position){
        //String str = String.valueOf(position);
        //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        LatLng newLoc = new LatLng(wList.get(position), lList.get(position));
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(newLoc));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLoc));
    }
    private void changeLine(int position){
        mMap.clear();
        if (firstSpinnerPosition != 0) {
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(wList.get(firstSpinnerPosition), lList.get(firstSpinnerPosition)), new LatLng(wList.get(position), lList.get(position)))
                    .width(5)
                    .color(Color.RED));
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    public void button(View view){
        if (mapStyle)
            mapStyle = false;
        else
            mapStyle = true;

        if (mapStyle)
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }


}
