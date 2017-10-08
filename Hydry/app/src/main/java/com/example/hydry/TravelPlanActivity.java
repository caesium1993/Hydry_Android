package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/*
 * This activity is used to show the travel plan menu
 */

public class TravelPlanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_travel_plan);
    }

    //launch a specified activity
    public void toVisa(View view){
        Intent intent_visa = new Intent(TravelPlanActivity.this, VisaActivity.class);
        startActivity(intent_visa);
    }
}
