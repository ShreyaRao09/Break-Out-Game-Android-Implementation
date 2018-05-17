package com.example.breakoutgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

//This Activity is for the Instructions Page
//Authors: Shreya Vishwanath Rao
public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        //Give the word corresponding to a color its respective color on the activity
        String part1="Use the ball to break the bricks. ";
        String yellow="<font color='#DAA520'>Yellow</font>";
        String part2=" bricks break with one hit. ";
        String blue="<font color='#FF8000'>Orange</font>";
        String part3=" bricks break with two hits and ";
        String red="<font color='#DC143C'>Red</font>";
        String part4=" bricks break with three hits.";
        TextView t=(TextView)findViewById(R.id.inst_ball_text);
        t.setText(Html.fromHtml(part1+yellow+part2+blue+part3+red+part4));
    }
}
