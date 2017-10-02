package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class PublicTransportationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_transportation);
    }

    public void toPtv(View view){
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=au.gov.vic.ptv&hl=en-us");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
