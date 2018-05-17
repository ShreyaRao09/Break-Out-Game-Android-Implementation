package com.example.breakoutgame;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

import static com.example.breakoutgame.R.layout.da_item;

public class HighScoresActivity extends AppCompatActivity {

    String filename="scores.txt";
    int numOfLines = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        getList(HighScoresActivity.this);
    }

    public void getList(Context context)
    {
        try {
            InputStream is = context.openFileInput(filename);

            //Find length of the text file
            if (is != null) {

                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String recStr = "";
                while ((recStr = br.readLine()) != null) {
                    numOfLines++;
                }
                is.close();
                System.out.println("numOfLines: " + numOfLines);
            }

            String[] list = new String[numOfLines];
            InputStream is1 = context.openFileInput(filename);

            //Read operation
            if (is1 != null) {
                InputStreamReader isr1 = new InputStreamReader(is1);
                BufferedReader br1 = new BufferedReader(isr1);
                String recStr1 = "";
                int i = 0;
                while ((recStr1 = br1.readLine()) != null) {
                    list[i] = recStr1;
                    System.out.println(list[i]);
                    i++;
                }

                Arrays.sort(list, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        String[] temp1 = o1.split("\t");
                        String[] temp2 = o2.split("\t");
                        int int1 = Integer.parseInt(temp1[0]);
                        int int2 = Integer.parseInt(temp2[0]);
                        return -1 * Integer.compare(int1, int2);
                    }
                });
                if(i>10)
                    i=10;
                String[] toDisplay = new String[i+1];


                toDisplay[0]= "NAME\t\tSCORE";
                 //Swap to (Name, Score)
                for (int j = 0; j < i; j++) {
                    toDisplay[j+1] = list[j];
                    String[] items = toDisplay[j+1].split(("\t"));
                    toDisplay[j+1] = items[1] + "\t" + "\t" + items[0];
                }

                //Adapter to display in List
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, da_item, toDisplay);

                ListView listView = (ListView) findViewById(R.id.listViewMain);
                listView.setAdapter(adapter);
            }
            is.close();
        }
            catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
