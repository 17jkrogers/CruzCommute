package com.group25.cruzcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.ArrayList;


public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ArrayList routeList = new ArrayList<Route>();
        ListView routeView = findViewById(R.id.routeList);
        Calendar busCalendar = Calendar.getInstance(TimeZone.getDefault());
        final int currentDay = busCalendar.get(Calendar.DAY_OF_WEEK);
        routeList.add(new Route("16", "High", 25));
        routeList.add(new Route("15", "Low", -5));
        routeList.add(new Route("19", "Medium", 5));
        routeList.add(new Route( "10", "Medium", 10));
        routeList.add(new Route( "20", "High", 5));
        routeList.add(new Route( "22", "High", 15));

        routeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg) {
                if (currentDay == 1 || currentDay == 7) {
                    if (position == 0) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/16/we"));
                        startActivity(intent);
                    } else if (position == 1) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/15/we"));
                        startActivity(intent);
                    } else if (position == 2) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/19/we"));
                        startActivity(intent);
                    } else if (position == 3) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/10/we"));
                        startActivity(intent);
                    } else if (position == 4) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/20/we"));
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/22/we"));
                        startActivity(intent);
                    }
                }
                else{
                    if (position == 0) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/16/wd"));
                        startActivity(intent);
                    } else if (position == 1) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/15/wd"));
                        startActivity(intent);
                    } else if (position == 2) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/19/wd"));
                        startActivity(intent);
                    } else if (position == 3) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/10/wd"));
                        startActivity(intent);
                    } else if (position == 4) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/20/wd"));
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.scmtd.com/en/routes/schedule/20201/22/wd"));
                        startActivity(intent);
                    }
                }
            }
        });


        RouteAdapter adapter = new RouteAdapter(this, R.layout.route_list, routeList);
        routeView.setAdapter(adapter);
    }

    public void transferReportActivity(View view) {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }
}
