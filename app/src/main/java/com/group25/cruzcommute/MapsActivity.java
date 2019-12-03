//references:   https://developers.google.com/maps/documentation/android-sdk/current-place-tutorial
//              https://developer.android.com/training/location/geofencing
package com.group25.cruzcommute;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GeofencingClient geofencingClient;
    private List<LatLng> coordinates = new ArrayList<>();
    private ArrayList<Geofence> fences = new ArrayList<Geofence>();
    private PendingIntent geofencePendingIntent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geofencingClient = LocationServices.getGeofencingClient(this);
        coordinates.add(new LatLng(36.9707, -122.0249));
        coordinates.add(new LatLng(36.9690, -122.0277));
        coordinates.add(new LatLng(36.9688, -122.0289));
        coordinates.add(new LatLng(36.9686, -122.0311));
        coordinates.add(new LatLng(36.9669, -122.0406));
        coordinates.add(new LatLng(36.9682, -122.0432));
        coordinates.add(new LatLng(36.9694, -122.0459));
        coordinates.add(new LatLng(36.9737, -122.0501));
        coordinates.add(new LatLng(36.9742, -122.0503));
        coordinates.add(new LatLng(36.9754, -122.0524));
        coordinates.add(new LatLng(36.9767, -122.0538));
        coordinates.add(new LatLng(36.9776, -122.0536));
        coordinates.add(new LatLng(36.9773, -122.0543));
        coordinates.add(new LatLng(36.9814, -122.0519));
        coordinates.add(new LatLng(36.9857, -122.0535));
        coordinates.add(new LatLng(36.9912, -122.0548));
        coordinates.add(new LatLng(36.9942, -122.0555));
        coordinates.add(new LatLng(36.9966, -122.0553));
        coordinates.add(new LatLng(36.9975, -122.0551));
        coordinates.add(new LatLng(36.9990, -122.0551));
        coordinates.add(new LatLng(36.9998, -122.0583));
        coordinates.add(new LatLng(36.9998, -122.0622));
        coordinates.add(new LatLng(36.9992, -122.0644));
        coordinates.add(new LatLng(36.9967, -122.0635));
        coordinates.add(new LatLng(36.9928, -122.0650));
        coordinates.add(new LatLng(36.9917, -122.0668));
        coordinates.add(new LatLng(36.9905, -122.0660));
        coordinates.add(new LatLng(36.9899, -122.0671));
        coordinates.add(new LatLng(36.9836, -122.0649));
        coordinates.add(new LatLng(36.9827, -122.0627));
        coordinates.add(new LatLng(36.9798, -122.0593));
        coordinates.add(new LatLng(36.9787, -122.0577));
        coordinates.add(new LatLng(36.9628, -122.0445));
        coordinates.add(new LatLng(36.9662, -122.0397));
        coordinates.add(new LatLng(36.9653, -122.0384));
        coordinates.add(new LatLng(36.9639, -122.0364));
        coordinates.add(new LatLng(36.9636, -122.0357));
        coordinates.add(new LatLng(36.9624, -122.0340));
        coordinates.add(new LatLng(36.9614, -122.0324));
        coordinates.add(new LatLng(36.9604, -122.0291));
        coordinates.add(new LatLng(36.9613, -122.0258));
        coordinates.add(new LatLng(36.9637, -122.0249));
        coordinates.add(new LatLng(36.9656, -122.0253));
        coordinates.add(new LatLng(36.9770, -122.0528));
        coordinates.add(new LatLng(36.9773, -122.0503));
        coordinates.add(new LatLng(36.9777, -122.0483));
        coordinates.add(new LatLng(36.9776, -122.0422));
        coordinates.add(new LatLng(36.9778, -122.0336));
        coordinates.add(new LatLng(36.9769, -122.0329));
        coordinates.add(new LatLng(36.9773, -122.0301));
        coordinates.add(new LatLng(36.9761, -122.0279));
        coordinates.add(new LatLng(36.9735, -122.0271));
        coordinates.add(new LatLng(36.9722, -122.0271));
        coordinates.add(new LatLng(36.9747, -122.0584));
        coordinates.add(new LatLng(36.9737, -122.0584));
        coordinates.add(new LatLng(36.9703, -122.0584));
        coordinates.add(new LatLng(36.9686, -122.0584));
        coordinates.add(new LatLng(36.9665, -122.0571));
        coordinates.add(new LatLng(36.9643, -122.0567));
        coordinates.add(new LatLng(36.9589, -122.0574));
        coordinates.add(new LatLng(36.9550, -122.0570));
        coordinates.add(new LatLng(36.9550, -122.0544));
        coordinates.add(new LatLng(36.9556, -122.0490));
        coordinates.add(new LatLng(36.9556, -122.0480));
        coordinates.add(new LatLng(36.9559, -122.0455));
        coordinates.add(new LatLng(36.9562, -122.0430));
        coordinates.add(new LatLng(36.9566, -122.0405));
        coordinates.add(new LatLng(36.9570, -122.0377));
        coordinates.add(new LatLng(36.9574, -122.0339));
        coordinates.add(new LatLng(36.9578, -122.0322));
        coordinates.add(new LatLng(36.9580, -122.0309));
        coordinates.add(new LatLng(36.9580, -122.0300));
        coordinates.add(new LatLng(36.9548, -122.0576));
        coordinates.add(new LatLng(36.9545, -122.0623));
        coordinates.add(new LatLng(36.9525, -122.0654));
        for(LatLng coord : coordinates){
            fences.add(new Geofence.Builder()
                .setRequestId(coord.toString())

                .setCircularRegion(coord.latitude,
                                    coord.longitude,
                                    100.0f)
                .setExpirationDuration(-1)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());
        }

        geofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("DEBUG", "geofence added");
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("DEBUG", "geofence not added");
                        e.printStackTrace();
                    }
                });

    }

    private GeofencingRequest getGeofencingRequest(){
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_EXIT);
        builder.addGeofences(fences);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent(){
        if(geofencePendingIntent != null){
            return geofencePendingIntent;
        }
        else{
            Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
            geofencePendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            return geofencePendingIntent;
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Santa Cruz.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
        enableMyLocationIfPermitted();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(11);
    }

    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("DEBUG", "access not granted yet");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION/*,
                            Manifest.permission.ACCESS_FINE_LOCATION*/},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            Log.d("DEBUG", "access already granted previously");
            mMap.setMyLocationEnabled(true);
        }
    }
    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng santa_cruz = new LatLng(36.974117, -122.121512);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(santa_cruz));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("DEBUG", "permission has been granted");
                enableMyLocationIfPermitted();
            } else {
                Log.d("DEBUG", "permission has been denied");
                showDefaultLocation();
            }
        }
    }

    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(15);
                    Log.d("DEBUG", "onmylocationbuttonclicklistener reached");
                    return false;
                }
            };


    public void onSettingsClicked(View v){
        Intent settingsIntent = new Intent(this, CommuteSettings.class);
        startActivity(settingsIntent);
    }

    public void onScheduleClicked(View v){
        Intent scheduleIntent = new Intent(this, ScheduleActivity.class);
        startActivity(scheduleIntent);
    }
}
