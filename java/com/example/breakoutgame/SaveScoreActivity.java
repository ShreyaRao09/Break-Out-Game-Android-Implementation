package com.example.breakoutgame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;

//This activity saves the user name and score into a file and on saving, calls the main activity
//Authors: Shivika Prasanna & Shreya Vishwanath Rao
public class SaveScoreActivity extends AppCompatActivity {

    EditText txtName;
    Button btnSave;
    String filename = "scores.txt";

    Timer time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_score);

        Bundle b = getIntent().getExtras();
        final Long score = b.getLong("Score");

        txtName = (EditText) findViewById(R.id.txtName);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Context context = getApplicationContext();
                    if (txtName.getText().toString().matches("")) {
                        Toast.makeText(context, "Please enter your name!", Toast.LENGTH_SHORT).show();
                        txtName.requestFocus();
                    }
                 else{
                    writeScoreIntoFile(SaveScoreActivity.this, score);
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void writeScoreIntoFile(Context context, Long score) throws IOException {
        try {
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_APPEND));
                String toWrite = score.toString() + "\t" + txtName.getText().toString().trim() + "\n";
                System.out.println("toWrite: " + toWrite);
                osw.write(toWrite);
                osw.close();

                System.out.println("In else");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
