package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_menu);

    }

    //launch a specified activity
    public void toPlanToGo(View view){
        Intent intent_plan_to_go = new Intent(MainMenuActivity.this, PlanToGoActivity.class);
        startActivity(intent_plan_to_go);
    }

    //launch a specified activity
    public void toArrived (View view) {
        LocationDetermination mLocation = new LocationDetermination(MainMenuActivity.this);
        mLocation.checkGPSSetting();
        String location=mLocation.getLocation();
        Intent intent_arrived = new Intent(MainMenuActivity.this,ArrivedMenuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Location",location);
        intent_arrived.putExtras(bundle);
        startActivity(intent_arrived);
    }
}
