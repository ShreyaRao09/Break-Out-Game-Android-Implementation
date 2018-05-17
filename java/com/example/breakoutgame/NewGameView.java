package com.example.breakoutgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//This class draws the game layout using canvas and paint. It also resets the values. It detects touch and performs accordingly.
//Authors: Shivika Prasanna & Shreya Vishwanath Rao
public class NewGameView extends SurfaceView implements Runnable{
    @Override
    public void run(){
        while(!isPaused){
            try {
                Thread.sleep(10);
            }catch(InterruptedException ie){

            }
            draw();
        }
    }

    SurfaceHolder holder;
    Canvas canvas;
    Paint paint;
    int screenX, screenY;
    Paddle paddle;
    Ball ball;
    Point size;
    float xPressed;
    float yPressed;
    float desiredX;
    boolean isPaused = false;
    static boolean isGameOver = false;
    static boolean isBallMoving = false;
    static boolean isFirstGame = true;
    static int gameOverSize = 20;
    public static long startTime=0;
    int directionPressed = 0;
    public static boolean isGameWon= false;
    static boolean shouldMovePaddle = false;
    static Brick[][] bricks = new Brick[6][];
    private Context myContext;
    Timer timer;

    public NewGameView(Context context, Point size){
        super(context);
        myContext = context;
        this.size=size;
        holder = getHolder();
        paint = new Paint();
        screenX = this.size.x;
        screenY = this.size.y;
        paddle = new Paddle(screenX,screenY);
        ball = new Ball(screenX,screenY,this);
        timer = new Timer();
        int startCount = 7;
        int existingColor = 0 ;
        for(int i=0; i<=5; i++){
            float currX=0;
            float currY=(int) (i*(screenX/12));
            bricks[i] = new Brick[startCount];
            for(int j=0;j<startCount;j++){

                Brick brick = new Brick(currX,currY,i,screenX,screenY,j+i,existingColor);
                bricks[i][j] = brick;
                existingColor = brick.getColor();
                currX += (int)(screenX/startCount);
            }
            startCount++;
        }
    }

    public void draw() {
        if (holder.getSurface().isValid()) {
            canvas = holder.lockCanvas();
            paint.setStyle(Paint.Style.FILL);
            // Draw the background color
            canvas.drawColor(Color.argb(255, 0, 0, 0));
            // Choose the brush color for drawing
            paint.setColor(Color.argb(255, 192, 192, 192)); //Paddle
            // Draw the paddle
            if (shouldMovePaddle && !isGameOver)
                paddle.editRectangle((int) desiredX, directionPressed);
            if (isBallMoving && !isGameOver)
                ball.editBallPosition();
            canvas.drawRect(paddle.getRectangle(), paint);
            paint.setColor(Color.argb(255, 255, 204, 204)); //Ball
            canvas.drawCircle(ball.getx(), ball.getY(), ball.getRadius(), paint);
            int startCount = 7;
            for (int i = 0; i <= 5; i++) {
                for (int j = 0; j < startCount; j++) {
                    Brick brick = bricks[i][j];
                    switch (brick.getColor()) {
                        case 0: paint.setColor(Color.argb(255, 218, 165, 32));
                            //Gold: 255, 215, 0
                            //Dark Golden Rod: 184, 134, 11
                            //Golden Rod: 218, 165, 32
                            break;
                        case 1: paint.setColor(Color.argb(255, 255, 128, 0));
                            break; //Orange
                        case 2: paint.setColor(Color.argb(255, 220, 20, 60));
                            break; //Red
                        default: break;
                    }
                    if (brick.isPresent()) {
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawRect(brick.getBrick(), paint);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setColor(Color.argb(255, 0, 0, 0));
                        canvas.drawRect(brick.getBrick(), paint);
                    }
                }
                startCount++;
            }
            paint.setStyle(Paint.Style.STROKE);
            paint.setTextSize(40);
            paint.setColor(Color.argb(255, 255, 255, 255));
            canvas.drawText(timer.getTimer(),10,screenY-100,paint);
            if (isGameOver && !isFirstGame && !isGameWon) {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.RED);
                paint.setTextSize(gameOverSize);
                canvas.drawText("Game Over!", screenX / 24, screenY / 2, paint);
                if (gameOverSize < 100) {
                    gameOverSize += 10;
                }
            }
            if (isGameWon) {
                isGameWon = false;
                isGameOver = true;
                isBallMoving = false;

            }
            holder.unlockCanvasAndPost(canvas);
        }
    }


    public void resume() {
        isPaused = false;
        new Thread(this).start();
    }
    public void pause(){
        isPaused = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:
                if(isGameOver && !isFirstGame && !isGameWon)
                {
                    Intent intent = new Intent(myContext, SaveScoreActivity.class);
                    Bundle b = new Bundle();
                    b.putLong("Score", timer.getScore());
                    intent.putExtras(b);
                    myContext.startActivity(intent);
                    resetThings();
                }
                if((isGameOver==false && isFirstGame && !isBallMoving) || (isGameOver&&!isFirstGame && !isBallMoving)){
                    resetThings();
                }
                xPressed = motionEvent.getX();
                float yPressed = motionEvent.getY();
                if(yPressed>screenY-1000 && yPressed < screenY-50)
                    if(xPressed < (2*paddle.getX() + screenX/6)/2){
                        directionPressed = -1;
                        shouldMovePaddle = true;
                        if(xPressed<screenX/12)
                            desiredX = 0;
                        else{
                            desiredX = xPressed - screenX/12;
                        }
                    }
                    else if(xPressed > (2*paddle.getX() + screenX/6)/2){
                        directionPressed = 1;
                        shouldMovePaddle = true;
                        if(xPressed>screenX - screenX/12)
                            desiredX = screenX - screenX/6;
                        else{
                            desiredX = xPressed - screenX/12;
                        }
                    }

                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:
                shouldMovePaddle=false;
                break;
        }
        return true;
    }

    public void resetThings(){
        paddle = new Paddle(screenX,screenY);
        ball = new Ball(screenX,screenY,this);
        isGameWon=false;
        int startCount = 7;
        int existingColor = 0 ;
        gameOverSize = 20;
        if(!isFirstGame)
            for(int i=0; i<=5; i++){
                float currX=0;
                float currY=(int) (i*(screenX/12));
                bricks[i] = new Brick[startCount];
                for(int j=0;j<startCount;j++){

                    Brick brick = new Brick(currX,currY,i,screenX,screenY,j+i,existingColor);
                    bricks[i][j] = brick;
                    existingColor = brick.getColor();
                    currX += (int)(screenX/startCount);
                }
                startCount++;
            }
        isGameOver = false;
        isBallMoving = true;
        startTime = System.currentTimeMillis();
        timer.setTimer();
        timer.startTimerThread();
    }
}
