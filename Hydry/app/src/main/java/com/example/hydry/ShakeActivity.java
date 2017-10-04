package com.example.hydry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;

public class ShakeActivity extends Activity implements SensorEventListener {

    private long time = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        if(x>6&&y>6&&z>6){//当绝对值>6并且两次摇的时间间隔>1秒的时候，才认为是摇动了一次，可以自定义值大小
            if(System.currentTimeMillis()-time>1000){
                //摇了以后要做的事情
                //!!!
                toAfterShake();
                time = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void toAfterShake(){
        Intent intent_after_shake = new Intent(ShakeActivity.this, AfterShakeActivity.class);
        startActivity(intent_after_shake);
    }
}

