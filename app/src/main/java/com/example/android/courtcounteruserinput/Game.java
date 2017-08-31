package com.example.android.courtcounteruserinput;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Game implements Serializable {
    //declare variables
    String mTeamAName = "";
    String mTeamBName = "";
    int mTeamAPoints = 0;
    int mTeamBPoints = 0;
    String mWinner = "";
    String mFileName = "";
    Date mDate;

    //constructor
    Game(String aName, String bName, int aPts, int bPts, String winner) {
        mTeamAName = aName;
        mTeamBName = bName;
        mTeamAPoints = aPts;
        mTeamBPoints = bPts;
        mWinner = winner;
        mDate = Calendar.getInstance().getTime();
        mFileName = mTeamAName + " vs " + mTeamBName + " on " + Calendar.getInstance().getTime()
            + "!";
    }

    // This function implements Serializable to handle writing the object data to a file
    public void writeToFile(Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(mFileName, Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this);
            outputStream.close();
            fileOutputStream.close();
        }

        catch (IOException i) {
            Log.e("Game", "IOException in class Game", i);
        } // try catch block is necessary to catch this IOException
    }

    // This function is commented out because it will only display an individual file, but the app
    // pulls a log of all the Game objects from the directory, then displays them together.
    /* public void displayFile(Context context) {
        try {
            FileInputStream fileInputStream = context.openFileInput(mFileName);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            Game game = (Game) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        }

        catch (IOException i) {
            Log.e("Game", "IOException in class Game");
        }

        catch (ClassNotFoundException c) {
            Log.e("Game", "ClassNotFoundException in class Game");
        }
    }  */
}