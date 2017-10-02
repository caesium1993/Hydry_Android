package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//import com.example.zumoappname.R;

public class TravelPlanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_plan);
    }

    public void toVisa(View view){
        Intent intent_visa = new Intent(TravelPlanActivity.this, VisaActivity.class);
        startActivity(intent_visa);
    }
}
