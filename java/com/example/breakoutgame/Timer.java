package com.example.breakoutgame;

import android.os.Handler;

//This class is to display the timer and count the time until the player loses or finishes the game
//Authors: Shreya Vishwanath Rao
public class Timer {

    public static long endTime;
    public static long startTime;
    private final Handler myHandler = new Handler();
    private String timerValue;
    private long millis;

    //Set initial timer value
    public Timer()
    {
        timerValue="00:00";
    }

    public String getTimer()
    {
        return timerValue;
    }

    public long getScore()
    {
        return millis;
    }

    public void setTimer()
    {
        timerValue = "00:00";
    }

    //Update the UI using the handler and the runnable
    private void doSomeHardWork()
    {
        millis = System.currentTimeMillis() - NewGameView.startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        timerValue = String.format("%02d:%02d", minutes, seconds);
        myHandler.post(updateRunnable);

    }

    private void doSomeSoftWork(){
        myHandler.post(updater);
    }

    //Call the activity method that updates the UI
    final Runnable updateRunnable = new Runnable() {
        public void run() {
            updateUI();
        }
    };

    //Call the activity method that updates the UI
    final Runnable updater = new Runnable() {
        public void run() {

            updaterUI();
        }
    };

    private void updateUI()
    {

    }

    //Update the UI
    private void updaterUI()
    {
        NewGameView.isFirstGame = false;
        NewGameView.isBallMoving = false;
        NewGameView.isGameOver = true;
    }

    //Start the timer when the ball starts moving
    public void startTimerThread(){
        new Thread(){
            @Override
            public void run(){
                while(NewGameView.isBallMoving)
                    doSomeHardWork();
                doSomeSoftWork();
            }
        }.start();
    }
}
