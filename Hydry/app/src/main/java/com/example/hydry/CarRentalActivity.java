package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class CarRentalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rental);
    }

    public void toHertz(View view){
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.hertz&hl=en-us");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void toEuropcar(View view){
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.clanmo.europcar&hl=en-us");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void toAvis(View view){
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.avis.androidapp&hl=en-us");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
