package com.example.breakoutgame;

import android.content.Context;
import android.graphics.Point;
import android.widget.LinearLayout;

//This class defines the layout of the game and calls the game view
//Authors: Shivika Prasanna & Shreya Vishwanath Rao
public class NewGameLayout extends LinearLayout {

    private NewGameView newGameView;

    public NewGameLayout(Context context) {
        super(context);
    }

    public NewGameLayout(Context context, Point point){
        super(context);
        LayoutParams lpView = new LayoutParams(LayoutParams.WRAP_CONTENT, point.y);

        newGameView = new NewGameView(context,point);
        setOrientation(VERTICAL);

        newGameView.setLayoutParams(lpView);
        addView(newGameView);
        lpView = new LayoutParams(point.x, point.y);
    }

    public void resume() {
        newGameView.resume();
    }

    public void pause(){
        newGameView.pause();
    }
}
