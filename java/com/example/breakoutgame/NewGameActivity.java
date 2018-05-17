package com.example.breakoutgame;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;


//This activity opens the new game and calls the NewGameLayout to draw on canvas
//Authors: Shivika Prasanna & Shreya Vishwanath Rao
public class NewGameActivity extends AppCompatActivity implements SensorEventListener{

    private float lastX, lastY, lastZ;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Thread accelerometerThread;
    public static int tiltDirection=0;
    NewGameLayout gameScreenLayout;
    boolean firstReadingDone = false;
    long lastSensorReading = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        Display display = getWindowManager().getDefaultDisplay();

        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);
        gameScreenLayout= new NewGameLayout(this,size);
        setContentView(gameScreenLayout);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        if (NewGameView.isBallMoving)
            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                lastSensorReading = System.currentTimeMillis();
                firstReadingDone = true;
                float x = event.values[0];
                Ball.tiltValue = x;
                if (x > 0) {
                    tiltDirection = -1;
                    Ball.isTilted = true;
                } else if (x < 0) {
                    tiltDirection = 1;
                    Ball.isTilted = true;
                } else {
                    Ball.isTilted = false;
                }
            }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        gameScreenLayout.resume();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
        gameScreenLayout.pause();
    }
}
