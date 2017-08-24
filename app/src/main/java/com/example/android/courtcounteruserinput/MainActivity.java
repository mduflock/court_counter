//customize team names and store old game history on different page
//put game name at the top of the linear layout vert

package com.example.android.courtcounteruserinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

import static com.example.android.courtcounteruserinput.R.id.away_team_name;
import static com.example.android.courtcounteruserinput.R.id.big_title;
import static com.example.android.courtcounteruserinput.R.id.home_team_name;

public class MainActivity extends AppCompatActivity {

    //declare global variables
    int teamAPoints = 0;
    int teamBPoints = 0;
    boolean gameOver = false;

    // override the onCreate function from AppCompatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // these must be final because they are being used in the class below, and cannot
        // be changed (apparently)
        final EditText home_team_name = (EditText) findViewById(R.id.home_team_name);
        final EditText away_team_name = (EditText) findViewById(R.id.away_team_name);

        // to avoid duplicating code, I created a new TextWatcher object that will be used
        // to update the Big Title for both the home and away teams
        TextWatcher tracker = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String home_name = home_team_name.getText().toString();
                String away_name = away_team_name.getText().toString();

                updateBigTitle(home_name, away_name);
            }

            // these are necessary because TextWatcher is an abstract class - ignore
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        };

        // the TextWatcher object tracker will now be passed to the home_team_name and
        // away_team_name objects, which will use it in the method addTextChangedListener
        home_team_name.addTextChangedListener(tracker);
        away_team_name.addTextChangedListener(tracker);
    }


    // Big title functions to update the game title
    public void updateBigTitle(String home_team_name, String away_team_name) {
        TextView big_title = (TextView) findViewById(R.id.big_title);
        big_title.setText(home_team_name + " vs " + away_team_name);
    }



    /*
    These are the functions for adding points to both Team A and Team B scores.
     */

    public void freeA(View view) {
        teamAPoints++;
        calcWinner();
        setDisplayAPoints();
    }

    public void twoA(View view) {
        teamAPoints++;
        teamAPoints++;
        calcWinner();
        setDisplayAPoints();
    }

    public void threeA(View view) {
        teamAPoints++;
        teamAPoints++;
        teamAPoints++;
        calcWinner();
        setDisplayAPoints();
    }

    public void freeB(View view) {
        teamBPoints++;
        calcWinner();
        setDisplayBPoints();
    }

    public void twoB(View view) {
        teamBPoints++;
        teamBPoints++;
        calcWinner();
        setDisplayBPoints();
    }

    public void threeB(View view) {
        teamBPoints++;
        teamBPoints++;
        teamBPoints++;
        calcWinner();
        setDisplayBPoints();
    }


    /*
    These are the methods to display points for A and B.
     */

    public void setDisplayAPoints() {
        ((TextView) findViewById(R.id.a_points)).setText("Points: " + teamAPoints);
    }

    public void setDisplayBPoints() {
        ((TextView) findViewById(R.id.b_points)).setText("Points: " + teamBPoints);

    }

    // This method determines the winner and adjusts the Winner Banner text.
    public void calcWinner() {
        if (teamAPoints > teamBPoints) {
            ((TextView) findViewById(R.id.winner)).setText("The home team is winning!");
        } else if (teamBPoints > teamAPoints) {
            ((TextView) findViewById(R.id.winner)).setText("The away team is winning!");
        } else {
            ((TextView) findViewById(R.id.winner)).setText("It's a tie!");
        }
    }

    // This method is called when the Game Over button is clicked. It disables all the buttons
    // the first time this method is called, and reenables them the second time the method is called,
    // when it also sets all the points and their displays to 0.
    public void gameOver(View view) {
        //flip the boolean gameOver for the second click, so that a second click will
        //begin a new game
        gameOver = !gameOver;

        if (gameOver) {
            if (teamAPoints > teamBPoints) {
                ((TextView) findViewById(R.id.winner)).setText("The home team won!");
            } else if (teamBPoints > teamAPoints) {
                ((TextView) findViewById(R.id.winner)).setText("The away team won!");
            } else {
                ((TextView) findViewById(R.id.winner)).setText("It's a tie!");
            }

            ((Button) findViewById(R.id.game_over_button)).setText("The game is over! " +
                    "Click to start a new game.");

            enableButtons(false);
        } // end if for gameOver

        if (!gameOver) {
            //set winner banner back to It's a tie!
            ((TextView) findViewById(R.id.winner)).setText("It's a tie!");

            //set points for both teams to 0
            ((TextView) findViewById(R.id.a_points)).setText("Points: 0");
            teamAPoints = 0;
            ((TextView) findViewById(R.id.b_points)).setText("Points: 0");
            teamBPoints = 0;

            //enable buttons
            enableButtons(true);

            //reset button text value
            ((Button) findViewById(R.id.game_over_button)).setText("Is the game over? Click here!");
        }
    }

    // this function is called by gameOver to enable and disable buttons
    public void enableButtons (boolean enabled) {
        //takes in bool to determine if buttons should be enabled

        //pass in the value of enabled to the setEnabled function, which takes a bool
        ((Button) findViewById(R.id.a_free_button)).setEnabled(enabled);
        ((Button) findViewById(R.id.a_two_button)).setEnabled(enabled);
        ((Button) findViewById(R.id.a_three_button)).setEnabled(enabled);
        ((Button) findViewById(R.id.b_free_button)).setEnabled(enabled);
        ((Button) findViewById(R.id.b_two_button)).setEnabled(enabled);
        ((Button) findViewById(R.id.b_three_button)).setEnabled(enabled);
    }
} // end of class MainActivity