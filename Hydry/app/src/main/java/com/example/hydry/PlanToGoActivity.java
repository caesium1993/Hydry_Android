package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
 * This activity is used to enable the users to choose where they plan to go
 */

public class PlanToGoActivity extends Activity {
    private ArrayList<String> countries = new ArrayList<String>();
    private Spinner mSpinner;
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_plan_to_go);
        countries.add("Australia");
        countries.add("Brazil");
        countries.add("Burundi");
        countries.add("Canada");
        countries.add("China");
        countries.add("Colombia");
        countries.add("Fiji");
        countries.add("Iceland");
        countries.add("India");
        countries.add("Italy");
        countries.add("Mexico");
        countries.add("Thailand");
        countries.add("U.K");
        countries.add("Zambia");

        mSpinner = (Spinner) findViewById(R.id.spinner_countries);
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,countries);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(PlanToGoActivity.this, countries.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    //launch a specified activity
    public void toTravelPlan (View view){
        Intent intent_travel_plan = new Intent(PlanToGoActivity.this, TravelPlanActivity.class);
        startActivity(intent_travel_plan);
    }
}
