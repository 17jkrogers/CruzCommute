package com.group25.cruzcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    ArrayList<Integer> routeNums;
    ArrayAdapter<Integer> adapter;
    Spinner routeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        routeSpinner = findViewById(R.id.routeSpinner);

        routeNums = new ArrayList<Integer>();

        routeNums.add(0, 15);
        routeNums.add(1, 16);
        routeNums.add(2, 19);

        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, routeNums);

        routeSpinner.setAdapter(adapter);
    }

    public void onReportClicked(View view) {
        Toast.makeText(this, "Thank you for submitting your report!", Toast.LENGTH_LONG).show();
    }
}
