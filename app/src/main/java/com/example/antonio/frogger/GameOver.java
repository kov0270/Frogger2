package com.example.antonio.frogger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameOver extends Activity {
    Button newGameButton;
    Button exitButton;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newGameButton = findViewById(R.id.button2);
        exitButton = findViewById(R.id.button3);
        setContentView(R.layout.activity_game_over);
        Bundle extras = getIntent().getExtras();
        score = findViewById(R.id.textView4);
        int s =  0;
        if (extras != null)
        {
            s = extras.getInt("score");
        }

        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
        //score.setText(s);
    }

    public void startNewGame(View view) {
        setContentView(new FroggerView(this));
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
