package com.example.hydry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;

import java.util.Random;

//using the ACCELEROMETER to realize the 'shake the phone' function
public class ShakeActivity extends Activity implements SensorEventListener {

    private long time = 0;
    private static int restaurant = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shake);
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//get the object of the ACCELEROMETER
        manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);//register for supervision
    }

    @Override
    //activity after detecting shaking
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        //x,y,z indicate the amplitude on the three axises
        int x = (int) Math.abs(values[0]);
        int y = (int) Math.abs(values[1]);
        int z = (int) Math.abs(values[2]);
        if(x > 5 && y > 5 && z > 5){//choose the sensitivity
            if(System.currentTimeMillis() - time > 2000){//the min interval between two shaking
                //activity after detecting shaking
                switch(restaurant % 4 + 1){
                    case 1:toAfterShake1();break;
                    case 2:toAfterShake2();break;
                    case 3:toAfterShake3();break;
                    case 4:toAfterShake4();break;
                    default:break;
                }
                restaurant++;
                time = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    //launch a specified activity
    public void toAfterShake1(){
        Intent intent_after_shake = new Intent(ShakeActivity.this, AfterShake_1Activity.class);
        startActivity(intent_after_shake);
    }

    //launch a specified activity
    public void toAfterShake2(){
        Intent intent_after_shake = new Intent(ShakeActivity.this, AfterShake_2Activity.class);
        startActivity(intent_after_shake);
    }

    //launch a specified activity
    public void toAfterShake3(){
        Intent intent_after_shake = new Intent(ShakeActivity.this, AfterShake_3Activity.class);
        startActivity(intent_after_shake);
    }

    //launch a specified activity
    public void toAfterShake4(){
        Intent intent_after_shake = new Intent(ShakeActivity.this, AfterShake_4Activity.class);
        startActivity(intent_after_shake);
    }
}

