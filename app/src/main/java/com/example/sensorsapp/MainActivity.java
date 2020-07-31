package com.example.sensorsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorAccelerometer, sensorGravity, sensorGyroscope, sensorRotationVector, sensorStepCounter;

    TextView textViewAccelerometer, textViewGravity, textViewGyroscope, textViewRotationVector, textViewStepCounter;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) this,sensorAccelerometer,sensorManager.SENSOR_DELAY_NORMAL);
//        TYPE_ACCELEROMETER	SensorEvent.values[0]	Acceleration force along the x axis (including gravity).	m/s2
//                              SensorEvent.values[1]	Acceleration force along the y axis (including gravity).
//                              SensorEvent.values[2]	Acceleration force along the z axis (including gravity).


        sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this,sensorGravity,SensorManager.SENSOR_DELAY_NORMAL);
//        TYPE_GRAVITY	SensorEvent.values[0]	Force of gravity along the x axis.	m/s2
//                      SensorEvent.values[1]	Force of gravity along the y axis.
//                      SensorEvent.values[2]	Force of gravity along the z axis.



        sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this,sensorGyroscope,SensorManager.SENSOR_DELAY_NORMAL);

//        TYPE_GYROSCOPE	SensorEvent.values[0]	Rate of rotation around the x axis.	rad/s
//                          SensorEvent.values[1]	Rate of rotation around the y axis.
//                          SensorEvent.values[2]	Rate of rotation around the z axis.


        sensorRotationVector = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this,sensorRotationVector,SensorManager.SENSOR_DELAY_NORMAL);
//        TYPE_ROTATION_VECTOR	SensorEvent.values[0]	Rotation vector component along the x axis (x * sin(θ/2)).	Unitless
//                              SensorEvent.values[1]	Rotation vector component along the y axis (y * sin(θ/2)).
//                              SensorEvent.values[2]	Rotation vector component along the z axis (z * sin(θ/2)).
//                              SensorEvent.values[3]	Scalar component of the rotation vector ((cos(θ/2)).1


        sensorStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this,sensorStepCounter,SensorManager.SENSOR_DELAY_NORMAL);
//        TYPE_STEP_COUNTER	SensorEvent.values[0]	Number of steps taken by the user since the last reboot while the sensor was activated.	Steps




        textViewAccelerometer = findViewById(R.id.textViewAccelerometer);
        textViewAccelerometer.setMovementMethod(new ScrollingMovementMethod());
        textViewGravity = findViewById(R.id.textViewGravity);
        textViewGravity.setMovementMethod(new ScrollingMovementMethod());
        textViewGyroscope = findViewById(R.id.textViewGyroscope);
        textViewGyroscope.setMovementMethod(new ScrollingMovementMethod());
        textViewRotationVector = findViewById(R.id.textViewRotationVector);
        textViewRotationVector.setMovementMethod(new ScrollingMovementMethod());
        textViewStepCounter = findViewById(R.id.textViewStepCounter);
        textViewStepCounter.setMovementMethod(new ScrollingMovementMethod());

    }

    public MainActivity(){

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this,sensorAccelerometer,sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener((SensorEventListener) this,sensorGravity,sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener((SensorEventListener) this,sensorGyroscope,sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener((SensorEventListener) this,sensorRotationVector,sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener((SensorEventListener) this,sensorStepCounter,sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener((SensorEventListener) this);
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener((SensorEventListener) this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            //Update UI
            textViewAccelerometer.setText("Accelerometer\n"+"x="+String.valueOf(sensorEvent.values[0])+"\ny="+sensorEvent.values[1]+"\nz="+sensorEvent.values[2]);

        }
        if(sensorEvent.sensor.getType()==Sensor.TYPE_GRAVITY){
            //UpdateUI
            textViewGravity.setText("Gravity\n"+"x="+String.valueOf(sensorEvent.values[0])+"\ny="+sensorEvent.values[1]+"\nz="+sensorEvent.values[2]);

        }
        if(sensorEvent.sensor.getType()==Sensor.TYPE_GYROSCOPE){
            //UpdateUI
            textViewGyroscope.setText("Gyroscope\n"+"x="+String.valueOf(sensorEvent.values[0])+"\ny="+sensorEvent.values[1]+"\nz="+sensorEvent.values[2]);

        }
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ROTATION_VECTOR){
            //UpdateUI
            textViewRotationVector.setText("Rotation Vector\n"+"x="+String.valueOf(sensorEvent.values[0])+"\ny="+sensorEvent.values[1]+"\nz="+sensorEvent.values[2]+"\nScalar Component="+String.valueOf(sensorEvent.values[3]));

        }
        if(sensorEvent.sensor.getType()==Sensor.TYPE_STEP_COUNTER){
            //UpdateUI
            textViewStepCounter.setText("Steps\n"+"x="+sensorEvent.values[0]);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}