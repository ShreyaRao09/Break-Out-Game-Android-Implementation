/*
 *CS6326.001, Spring, 2017: Final Project- Breakout Game
 *Authors: Shivika Prasanna - sxp162630
 *         Shreya Vishwanath Rao - sxr169330
 *Purpose: To score a high score by breaking all the brick in least time possible
 *How to play: 1. Click on New Game and tap the screen to begin
 *             2. Use touch to move the paddle
 *             3. Ball bounces off the paddle to break the bricks
 *             4. Prevent the ball from falling off the paddle
 *             5. Different colored bricks require different number of hits, i.e., Yellow - 1 hit,
 *             Orange - 2 hits & Red - 3 hits
 *             6. Player can see his score in High Scores if the time he took is higher than the previous scores
 *             7. Instructions allow a new player to learn about the game
 *             8. Settings allow the user to change the speed of the ball
 */

package com.example.breakoutgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//This is the main activity which represents the main page, the buttons on the main page that call their respective activities.
//Authors: Shivika Prasanna & Shreya Vishwanath Rao
public class MainActivity extends AppCompatActivity {

    Button btnNewGame;
    Button btnHighScores;
    Button btnInstructions;
    Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewGameActivity.class);
                startActivity(intent);
            }
    });
        btnInstructions = (Button) findViewById(R.id.btnInstructions);
        btnInstructions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InstructionsActivity.class);
                startActivity(intent);
            }
        });

        btnHighScores = (Button) findViewById(R.id.btnHighScores);
        btnHighScores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighScoresActivity.class);
                startActivity(intent);
            }
        });

        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

}
