package com.example.antonio.frogger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button startButton;
    SharedPreferences mySharedPref;
    SharedPreferences.Editor mySharedEditor;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startButton = findViewById(R.id.button);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Intent intent = new Intent(this, ChooseLevel.class);
        startActivity(intent);
    }

    public void showTop(View view) {
        Intent intent = new Intent(this, TopScore.class);
        startActivity(intent);
    }
}
