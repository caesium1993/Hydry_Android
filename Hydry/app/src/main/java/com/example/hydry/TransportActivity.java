package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class TransportActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
    }

    public void toPublicTransportation(View view){
        Intent intent_public_transportation = new Intent(TransportActivity.this, PublicTransportationActivity.class);
        startActivity(intent_public_transportation);
    }

    public void toCarRental(View view){
        Intent intent_car_rental = new Intent(TransportActivity.this, CarRentalActivity.class);
        startActivity(intent_car_rental);
    }

//    public void toPlanToGo(View view){
//        Intent intent_plan_to_go = new Intent(MainMenuActivity.this, PlanToGoActivity.class);
//        startActivity(intent_plan_to_go);
//    }
//
//    public void toArrived (View view) {
//        Intent intent_arrived = new Intent(MainMenuActivity.this,ArrivedMenuActivity.class);
//        startActivity(intent_arrived);
//    }
}
