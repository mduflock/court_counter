package com.example.android.courtcounteruserinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static com.example.android.courtcounteruserinput.R.id.date;

public class GameLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_log);

        // create views in the LinearLayout view (within the ScrollView) for every saved game
        ViewGroup addObjectsToMe = (ViewGroup) findViewById(R.id.add_objects_to_me);

        // read information from the file into a new Game object
        // create test object manually
        // Game game = new Game("Tony", "Julia", 30, 70, "Julia");

        File[] directory = getFilesDir().listFiles();

        for (int i = 0; i < directory.length; i++) {
            try {
                View inflated = getLayoutInflater().inflate(R.layout.layout_for_game_objects, addObjectsToMe, false);
                // false is for the parameter attachToRoot

                // set variables for the views you will need to use - must use inflated
                // so that findViewById does not attempt to use the layout already on the screen
                // (these views are inflated, but are not yet displayed)
                TextView gameHeader = inflated.findViewById(R.id.game_header);
                TextView date = inflated.findViewById(R.id.date);
                TextView teamA = inflated.findViewById(R.id.team_a);
                TextView teamB = inflated.findViewById(R.id.team_b);

                // now load the files so they can be displayed on the screen
                Log.v("Files", "File Name in Current Directory: \n" + directory[i]);
                String name = directory[i].getName();
                FileInputStream fileInputStream = this.openFileInput(name);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Game gameToPrint = (Game) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();

                // change the data for the XML layout before you add the new views
                gameHeader.setText(gameToPrint.mTeamAName + " vs " + gameToPrint.mTeamBName);
                date.setText(gameToPrint.mDate.toString());
                teamA.setText(gameToPrint.mTeamAName + ": " + gameToPrint.mTeamAPoints);
                teamB.setText(gameToPrint.mTeamBName + ": " + gameToPrint.mTeamBPoints);

                // add the new views into the LinearLayout
                addObjectsToMe.addView(inflated);
            }

            catch (IOException e) {
                Log.e("IO Problem", "IOException in class Game, in file GameLogActivity", e);
            }

            catch (ClassNotFoundException c) {
                Log.e("No class", "Class not found in GameLogActivity", c);
            }
        } // end for loop to iterate over the files

        /*
        Log.e("Length", "Length of array: " + directory.length);

        for (int i = 0; i < directory.length; i++) {
            Log.v("Files", "Files in Current Directory: \n" + directory[i]);
        }
        Testing for directory length  */
    }
}
