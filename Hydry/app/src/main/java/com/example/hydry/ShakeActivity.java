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

public class ShakeActivity extends Activity implements SensorEventListener {

    private long time = 0;
    private static int restaurant = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shake);
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL);//获取传感器列表
//        for(int i=0;i<list.size();i++){
//            Log.d("--MainActivity",list.get(i).getName());//获取传感器的名字
//        }
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//得到加速度传感器对象
        manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);//注册监听
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        //取x，y，z三个方向晃动的绝对值
        int x = (int) Math.abs(values[0]);
        int y = (int) Math.abs(values[1]);
        int z = (int) Math.abs(values[2]);
        if(x > 5 && y > 5 && z > 5){
            if(System.currentTimeMillis() - time > 2000){
                //摇了以后要做的事
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

    public void toAfterShake1(){
        Intent intent_after_shake = new Intent(ShakeActivity.this, AfterShake_1Activity.class);
        startActivity(intent_after_shake);
    }

    public void toAfterShake2(){
        Intent intent_after_shake = new Intent(ShakeActivity.this, AfterShake_2Activity.class);
        startActivity(intent_after_shake);
    }

    public void toAfterShake3(){
        Intent intent_after_shake = new Intent(ShakeActivity.this, AfterShake_3Activity.class);
        startActivity(intent_after_shake);
    }

    public void toAfterShake4(){
        Intent intent_after_shake = new Intent(ShakeActivity.this, AfterShake_4Activity.class);
        startActivity(intent_after_shake);
    }
}

