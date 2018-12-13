package com.example.antonio.frogger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameOver extends Activity {
    Button newGameButton;
    Button exitButton;
    TextView score;
    TextView prvniScore;
    TextView druheScore;
    TextView tretiScore;
    TextView user;

    SharedPreferences mySharedPref;
    SharedPreferences.Editor mySharedEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newGameButton = findViewById(R.id.button2);
        exitButton = findViewById(R.id.button3);
        setContentView(R.layout.activity_game_over);
        Intent intent = getIntent();
        int s = intent.getIntExtra("score",0);
        score = findViewById(R.id.textView13);
        user = findViewById(R.id.textView4);
        score.setText(String.valueOf(s));
        mySharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        user.setText(mySharedPref.getString("name", "TopPlayer"));
        int first = mySharedPref.getInt("first", 0);
        int second = mySharedPref.getInt("second", 0);
        int third = mySharedPref.getInt("third", 0);
        mySharedEditor = mySharedPref.edit();
        if (s > first)
        {
            mySharedEditor.putInt("first", s);
            mySharedEditor.putInt("second", first);
            mySharedEditor.putInt("third", second);
            mySharedEditor.apply();
        }
        else if (s > second)
        {
            mySharedEditor.putInt("second", s);
            mySharedEditor.putInt("third", second);
            mySharedEditor.apply();
        }
        else if (s > third)
        {
            mySharedEditor.putInt("third", second);
            mySharedEditor.apply();
        }
        prvniScore = findViewById(R.id.textView6);
        druheScore = findViewById(R.id.textView7);
        tretiScore = findViewById(R.id.textView8);
        prvniScore.setText(String.valueOf(mySharedPref.getInt("first", 0)));
        druheScore.setText(String.valueOf(mySharedPref.getInt("second", 0)));
        tretiScore.setText(String.valueOf(mySharedPref.getInt("third", 0)));
    }

    public void startNewGame(View view) {
        setContentView(new FroggerView(this));
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
