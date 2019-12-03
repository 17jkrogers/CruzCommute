//references: https://developers.google.com/maps/documentation/android-sdk/current-place-tutorial
package com.group25.cruzcommute;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GeofencingClient geofencingClient;
    private List<LatLng> coordinates = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geofencingClient = LocationServices.getGeofencingClient(this);
        //coordinates
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Santa Cruz and move the camera
        //LatLng santa_cruz = new LatLng(36.974117, -122.030792);
        //float zoom = 14.0f;
        //mMap.addMarker(new MarkerOptions().position(santa_cruz).title("Marker in Santa Cruz"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santa_cruz, zoom));
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
        /*switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {*/
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("DEBUG", "permission has been granted");
                    enableMyLocationIfPermitted();
                } else {
                    Log.d("DEBUG", "permission has been denied");
                    showDefaultLocation();
                }
            /*}

        }*/
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
