package com.example.antonio.frogger;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class TopScore extends Activity {
    SharedPreferences mySharedPref;
    TextView first;
    TextView second;
    TextView third;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);
        first = findViewById(R.id.textView10);
        second = findViewById(R.id.textView11);
        third = findViewById(R.id.textView12);
        mySharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        int a = mySharedPref.getInt("first", 0);
        int b = mySharedPref.getInt("second", 0);
        int c = mySharedPref.getInt("third", 0);
        first.setText(String.valueOf(a));
        second.setText(String.valueOf(b));
        third.setText(String.valueOf(c));
    }
}
