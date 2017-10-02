package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//import com.example.zumoappname.R;

public class PlanToGoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_to_go);
    }

    public void toTravelPlan (View view){
        Intent intent_travel_plan = new Intent(PlanToGoActivity.this, TravelPlanActivity.class);
        startActivity(intent_travel_plan);
    }
}
