package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;



public class VisaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_visa);
    }

    //open the browser and go to a specified website
    public void toVisaWebsite(View view){
        Uri uri = Uri.parse("https://www.border.gov.au/Trav/Visa-1/600-");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
