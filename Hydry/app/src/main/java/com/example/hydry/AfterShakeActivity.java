package com.example.hydry;

import android.app.Activity;
import android.os.Bundle;

import java.util.Random;

public class AfterShakeActivity extends Activity {

    private int restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_shake);

        restaurant = pickRestaurant();
    }

    public static int pickRestaurant(){
        int max=5;
        int min=1;
        Random random = new Random();

        int answer = random.nextInt(max)%(max-min+1) + min;
        return answer;
    }
}
