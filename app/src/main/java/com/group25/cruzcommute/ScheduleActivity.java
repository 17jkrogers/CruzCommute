package com.group25.cruzcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ArrayList routeList = new ArrayList<Route>();
        ListView routeView = findViewById(R.id.routeList);

        routeList.add(new Route("16", "High", 25));
        routeList.add(new Route("15", "Low", -5));
        routeList.add(new Route("19", "Medium", 5));

        routeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(intent);
            }
        });


        RouteAdapter adapter = new RouteAdapter(this, R.layout.route_list, routeList);
        routeView.setAdapter(adapter);
    }
}
