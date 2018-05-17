package com.example.breakoutgame;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

//This class is to take the value from the seekbar and set it as the ball speed
//Authors: Shivika Prasanna
public class SettingsBar extends LinearLayout {

    public SettingsBar(Context context) {
        super(context);
    }

    static SeekBar seekBar;

    //Set the bar and the thumb size and length and change it as the user changes it
    public SettingsBar(Context context, Point point){
        super(context);
        setBackgroundColor(Color.argb(255, 0, 0, 0));
        setOrientation(HORIZONTAL);
        seekBar = new SeekBar(context);
        seekBar.setMax(100);
        LayoutParams lp = new LayoutParams(point.x-100, 150);
        seekBar.setLayoutParams(lp);
        ShapeDrawable thumb = new ShapeDrawable(new OvalShape());
        thumb.setIntrinsicHeight(80);
        thumb.setIntrinsicWidth(30);
        seekBar.setThumb(thumb);
        //Set default progress as 40
        seekBar.setProgress(40);
        seekBar.setVisibility(View.VISIBLE);
        seekBar.setBackgroundColor(Color.WHITE);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //Allow user to change it and accordingly change the speed of the ball
            public void onStopTrackingTouch(SeekBar arg0) {
                if (arg0.getProgress() < 25) {
                    Ball.rise = 2;
                    Ball.sliderValue = 2;
                    seekBar.setProgress(arg0.getProgress());
                } else if (arg0.getProgress() >= 25 && arg0.getProgress() < 50) {
                    Ball.rise = 4;
                    Ball.sliderValue = 4;
                    seekBar.setProgress(arg0.getProgress());
                } else if (arg0.getProgress() >= 50 && arg0.getProgress() < 75) {
                    Ball.rise = 6;
                    Ball.sliderValue = 6;
                    seekBar.setProgress(arg0.getProgress());
                } else {
                    Ball.rise = 8;
                    Ball.sliderValue = 8;
                    seekBar.setProgress(arg0.getProgress());
                }
                System.out.println(arg0.getProgress());
            }

            public void onStartTrackingTouch(SeekBar arg0) {

            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

            }
        });

        TextView textView = new TextView(context);
        textView.setText("Speed");
        textView.setTypeface(null, Typeface.BOLD);
        lp = new LayoutParams(200, 150);
        textView.setLayoutParams(lp);
        textView.setBackgroundColor(Color.argb(255, 242, 241, 239));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        addView(textView);
        lp = new LayoutParams(point.x-200,150);
        seekBar.setLayoutParams(lp);
        addView(seekBar);
    }
}
