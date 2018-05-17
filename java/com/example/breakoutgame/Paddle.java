package com.example.breakoutgame;

import android.graphics.RectF;

//This class is to draw a rectangular paddle and to move it left or right on the screen
//Authors: Shivika Prasanna & Shreya Vishwanath Rao
public class Paddle {

    private RectF rectangle;
    private float x;
    private float y;
    private float speed;

    //Set the paddle length and breadth
    public Paddle(int givenX,int givenY){
        x = givenX/2-100;
        y = givenY-200;
        rectangle = new RectF(x, y, x+ givenX/6, y + 50);
        speed = 350;
    }

    //Getters for X and Y axis
    public float getX(){
        return x;
    }
    public float gety(){
        return y;
    }

    //Move the paddle to left or right, depending on where the user touches on the screen
    public void editRectangle(int xPressed, int direction){
        if(xPressed<rectangle.left && direction ==-1){
            rectangle.left -= 20;
            rectangle.right -=20;
        }else if(xPressed>rectangle.left && direction == 1) {
            rectangle.left += 20;
            rectangle.right += 20;
        }else{
            NewGameView.shouldMovePaddle = false;
        }
        this.x = rectangle.left;
    }

    public RectF getRectangle(){
        return rectangle;
    }
}
