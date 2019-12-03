package com.group25.cruzcommute;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.Date;
import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "report";
    private static Date date = null;
    private static boolean firstCheck = false;

    private Context ctx;

    public GeofenceBroadcastReceiver (Context context) {
        ctx = context;
        createNotificationChannel();
    }

    public GeofenceBroadcastReceiver () {}

    private void createNotificationChannel() {
        CharSequence name = "Report";
        String description = "Estimated bus arrival time";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = ctx.getSystemService(NotificationManager.class);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (firstCheck) {
            Date currentDate = new Date();
            boolean timeCheck = true;
            if (date != null) {
                if (currentDate.getTime() - date.getTime() < 3600000){
                    timeCheck = false;
                }
            } else {
                date = currentDate;
                Log.d("DEBUG", "first geofence trigger");
            }

            if (timeCheck) {
                GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
                if (geofencingEvent.hasError()) {
                    Log.d("DEBUG", "geofence has error");
                }

                int geofenceTransition = geofencingEvent.getGeofenceTransition();

                if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
                    Log.d("DEBUG", "geofencing works motherfuckers");
                    Intent mIntent = new Intent(context, ReportActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_stat_name)
                            .setContentTitle("Report Bus Arrival")
                            .setContentText("Tap here to report bus slowdown")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                    notificationManager.notify(1, builder.build());
                } else {
                    Log.d("DEBUG", "wrong type of transition");
                }
            }
        } else {
            setFirstCheck();
        }
    }

    public void setFirstCheck() {
        firstCheck = true;
    }
}
