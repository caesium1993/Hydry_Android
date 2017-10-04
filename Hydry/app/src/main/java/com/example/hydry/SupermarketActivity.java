package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


public class SupermarketActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket);
    }

    public void Tocoles(View v){
        Intent intent=new Intent();
        intent.setClass(SupermarketActivity.this, ColesActivity.class);
        startActivity(intent);
    }

    public void Toworth(View v){
        Intent intent=new Intent();
        intent.setClass(SupermarketActivity.this, WorthActivity.class);
        startActivity(intent);
    }

    public void ToVM(View v){
        Uri uri = Uri.parse("http://www.qvm.com.au/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void Tojibihifi(View v){
        Uri uri = Uri.parse("https://www.jbhifi.com.au/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void Toofficework(View v){
        Uri uri = Uri.parse("https://www.officeworks.com.au/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void Tobigw(View v){
        Uri uri = Uri.parse("https://www.bigw.com.au/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void Tokmart(View v){
        Uri uri = Uri.parse("http://www.kmart.com.au/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void Totarget(View v){
        Uri uri = Uri.parse("https://www.target.com.au/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


}
