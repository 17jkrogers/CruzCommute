package com.group25.cruzcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.ArrayList;
import java.util.Calendar;


public class ScheduleActivity extends AppCompatActivity {

    private FirebaseDatabase db;
    private ArrayList<Route> routeList;
    ListView routeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        routeList = new ArrayList<Route>();

        db = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = db.getReference();

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Route added;
                Map<String, Object> vals = (Map<String, Object>) dataSnapshot.getValue();

                for(Map.Entry<String, Object> value: vals.entrySet()){
                    String routeNum = value.getKey().substring(value.getKey().indexOf("e") + 1);
                    String congLevel = value.getValue().toString().substring(0, value.getValue().toString().indexOf(","));
                    int slowdown = Integer.parseInt(value.getValue().toString().substring(value.getValue().toString().indexOf(",") + 2));
                    added = new Route(routeNum, congLevel, slowdown);
                    routeList.add(added);

                    routeView = findViewById(R.id.routeList);
                    Calendar busCalendar = Calendar.getInstance(TimeZone.getDefault());
                    final int currentDay = busCalendar.get(Calendar.DAY_OF_WEEK);

                    routeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg) {

                            if(currentDay == 1 || currentDay == 7) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/" + routeList.get(position).getRouteNum() + "/we"));
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/" + routeList.get(position).getRouteNum() + "/wd"));
                                startActivity(intent);
                            }
                        }
                    });


                    RouteAdapter adapter = new RouteAdapter(ScheduleActivity.this, R.layout.route_list, routeList);
                    routeView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError dbErr) {
            }
        });


    }

    public void transferReportActivity(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    public void addToList(Route added){
        routeList.add(added);
    }
}
