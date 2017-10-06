package com.example.hydry;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


public class AfterShake_4Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_after_shake_4);
    }
}
