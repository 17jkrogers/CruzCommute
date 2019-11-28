package com.group25.cruzcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

public class ReportActivity extends AppCompatActivity {

    ArrayList<String> routeNums;
    ArrayAdapter<String> adapter;
    Spinner routeSpinner;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        routeSpinner = findViewById(R.id.routeSpinner);
        updateSpinner();

    }

    public void onReportClicked(View view) {
        String routeStr = routeSpinner.getSelectedItem().toString();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference("route" + routeStr);

        RadioGroup selector = findViewById(R.id.congestionGroup);
        RadioButton selected = findViewById(selector.getCheckedRadioButtonId());
        final String congLevel = selected.getText().toString();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String oldVal = dataSnapshot.getValue(String.class);
                ref.setValue(congLevel + oldVal.substring(oldVal.indexOf(",")));
            }

            @Override
            public void onCancelled(DatabaseError dbErr) {
            }
        });

        Toast.makeText(this, "Thank you for submitting your report!", Toast.LENGTH_LONG).show();
    }


    public void updateSpinner(){
        routeNums = new ArrayList<String>();

        db = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = db.getReference();

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Route added;
                Map<String, Object> vals = (Map<String, Object>) dataSnapshot.getValue();

                for (Map.Entry<String, Object> value : vals.entrySet()) {
                    String routeNum = value.getKey().substring(value.getKey().indexOf("e") + 1);
                    routeNums.add(routeNum);
                }

                ArrayAdapter adapter = new ArrayAdapter(ReportActivity.this, android.R.layout.simple_spinner_item, routeNums);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                routeSpinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError dbErr) {
            }
        });
    }
}
