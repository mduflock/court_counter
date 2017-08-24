package com.example.android.courtcounteruserinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Game {
    //declare variables
    int teamAPoints = 0;
    int teamBPoints = 0;
    boolean gameOver = false;

    //constructor
    Game(int a, int b, boolean c) {
        teamAPoints = a;
        teamBPoints = b;
        gameOver = c;
    }
}