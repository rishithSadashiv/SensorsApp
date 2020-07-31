// https://developer.android.com/guide/topics/sensors/sensors_motion#sensors-motion-significant

package com.example.sensorsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.sensorsapp.R;


public class SignificantMovementActivity extends AppCompatActivity {


    TextView textViewSignificant;

    private SensorManager sensorManager;
    private Sensor sensor;
    private TriggerEventListener triggerEventListener;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_significant_movement);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);

        textViewSignificant = findViewById(R.id.textviewSignificantMovement);
        textViewSignificant.setText("Not Triggered");

        triggerEventListener = new TriggerEventListener() {
            boolean triggered = false;

            @Override
            public void onTrigger(TriggerEvent triggerEvent) {
                triggered = !triggered;
                if(triggered){
                    textViewSignificant.setText("Triggered");
                }else{
                    textViewSignificant.setText("Not Triggered");
                }
            }
        };
    }


}