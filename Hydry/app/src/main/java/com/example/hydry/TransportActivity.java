package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


public class TransportActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_transport);
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

    public void toPtv(View view){
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=au.gov.vic.ptv&hl=en-us");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
