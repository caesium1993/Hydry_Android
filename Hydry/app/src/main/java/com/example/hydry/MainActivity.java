package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import java.net.MalformedURLException;

/*
 *First page of our APP
 *Call AzureServicesAdapter.Initialize() function to initialize connection between Android and Azure
 */


public class MainActivity extends Activity {

    public static AzureServicesAdapter azureServicesAdapter;//variable for Azure connection
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            AzureServicesAdapter.Initialize(this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        azureServicesAdapter=AzureServicesAdapter.getInstance();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    //launch a specified activity
    public void ToLogin(View view){
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    //launch a specified activity
    public void ToRegister(View view){
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
