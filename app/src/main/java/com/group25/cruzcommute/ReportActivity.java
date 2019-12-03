package com.group25.cruzcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    boolean numberEntered;

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
        String buttonText = selected.getText().toString();
        EditText slowDown = findViewById(R.id.plain_text_input);
        final String slowReport = slowDown.getText().toString();
        int parsed;
        numberEntered = !(slowReport.equals(""));
        if(slowReport.length() > 3){
            Toast.makeText(this, "Please enter a valid number between -60 and 60", Toast.LENGTH_LONG).show();
            return;
        }
        try{
            parsed = Integer.parseInt(slowReport);
            if(parsed > 60 || parsed < -60){
                Toast.makeText(this, "Please enter a valid number between -60 and 60", Toast.LENGTH_LONG).show();
                return;
            }
        } catch(Exception e){
            Toast.makeText(this, "Please enter a valid number between -60 and 60", Toast.LENGTH_LONG).show();
            return;
        }
        final String congLevel = buttonText.substring(0,1) + buttonText.substring(1).toLowerCase();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!numberEntered) {
                    String oldVal = dataSnapshot.getValue(String.class);
                    ref.setValue(congLevel + oldVal.substring(oldVal.indexOf(",")));
                }
                else{
                    ref.setValue(congLevel + ", " + slowReport);
                }
            }

            @Override
            public void onCancelled(DatabaseError dbErr) {
            }
        });
        Toast.makeText(this, "Thank you for submitting your report!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


    public void updateSpinner(){
        routeNums = new ArrayList<String>();

        db = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = db.getReference();

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> vals = (Map<String, Object>) dataSnapshot.getValue();

                for (Map.Entry<String, Object> value : vals.entrySet()) {
                    String routeNum = value.getKey().substring(value.getKey().indexOf("e") + 1);
                    routeNums.add(routeNum);
                }

                adapter = new ArrayAdapter(ReportActivity.this, R.layout.spinner_item, routeNums);
                adapter.setDropDownViewResource(R.layout.spinner_dropdown);

                routeSpinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError dbErr) {
            }
        });
    }
}
