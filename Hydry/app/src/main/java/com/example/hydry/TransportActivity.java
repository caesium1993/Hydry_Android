package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
//        网页跳转实例
//        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.hertz&hl=en-us");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
    }
}
