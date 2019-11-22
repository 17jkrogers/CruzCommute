package com.group25.cruzcommute;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
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
        routeNums.add(3, 10);
        routeNums.add(4, 20);
        routeNums.add(5, 22);



        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, routeNums);

        routeSpinner.setAdapter(adapter);
    }

    public void onReportClicked(View view) {
        String routeStr = routeSpinner.getSelectedItem().toString();
        int route = Integer.parseInt(routeStr);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference("route" + route);

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

    /* TODO: Keyton
    Add code to store report data to the firebase. Collaborate with Danyal to get exact formatting.
     */
}
