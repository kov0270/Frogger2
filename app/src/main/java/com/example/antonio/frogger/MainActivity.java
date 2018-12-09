package com.example.antonio.frogger;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button startButton;
    SharedPreferences mySharedPref;
    SharedPreferences.Editor mySharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startButton = findViewById(R.id.button);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        setContentView(new FroggerView(this));

    }

    public void showTop(View view) {

    }
}
