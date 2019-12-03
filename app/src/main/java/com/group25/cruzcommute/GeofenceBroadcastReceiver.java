package com.group25.cruzcommute;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver {

    //private GeofencingEvent geofencingEvent
    protected void onReceive(Context context, Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.d("DEBUG", "geofence has error");
        }

        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            Log.d("DEBUG", "make a notification here");
        } else {
            Log.d("DEBUG", "wrong type of transition");
        }
    }
}
