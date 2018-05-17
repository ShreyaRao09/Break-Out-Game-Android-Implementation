package com.example.breakoutgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Point;
import android.view.Display;

//This Activity is for the Settings page where the user can change the speed of the ball
//Authors: Shivika Prasanna
public class SettingsActivity extends AppCompatActivity {

    SettingsBar barValue;
    Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        barValue= new SettingsBar(this,size);
        setContentView(barValue);
    }
}
