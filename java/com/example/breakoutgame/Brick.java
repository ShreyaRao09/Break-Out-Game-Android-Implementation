package com.example.breakoutgame;

import android.graphics.RectF;

import java.util.Random;

//This class is to draw rectangular bricks on the screen and set 3 different colors to them
//Authors: Shreya Vishwanath Rao
public class Brick {

    private RectF rectBrick;
    private float x;
    private float y;
    private int rowNum;
    private boolean isPresent;
    private int color;
    Random random= new Random();

    public Brick(float x, float y, int rowNum, int givenX, int givenY, int color, int existingColor){
        this.x = x;
        this.y = y;
        this.rowNum = rowNum;
        //Choose any number from 1-3, one for each color
        color = random.nextInt(3);
        //If the color already exists, change the color
        while(color == existingColor){
            color = random.nextInt(3);
        }
        this.color = color;
        rectBrick = new RectF(x, y, (int) (x+ (givenX/(7+rowNum))), (int)(y+ givenX/12));
        this.isPresent = true;
    }

    public boolean isPresent() { return isPresent; }
    public void setPresent(boolean isPresent){
        this.isPresent = isPresent;
    }

    //Getters and Setters for Brick and Color
    public int getColor(){
        return this.color;
    }
    public void setRectangle(float x, float y,int givenX, int givenY){
        rectBrick = new RectF(x, y, (int) (x+ (givenX/(7+rowNum))), (int)(y+ givenX/12));
    }
    public void setColor(int color){
        this.color = color;
    }
    public RectF getBrick(){
        return rectBrick;
    }

}
