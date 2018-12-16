package com.example.antonio.frogger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChooseLevel extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        ArrayList members = new ArrayList();
       /* members.add(new Level(1,"Level 1"));
        members.add(new Level(2,"Level 2"));
        members.add(new Level(3,"Level 3"));
        members.add(new Level(4,"Level 4"));
        members.add(new Level(5,"Level 5"));
        members.add(new Level(6,"Level 6"));*/
       members.add("Level 1");
       members.add("Level 2");
       members.add("Level 3");
       members.add("Level 4");
       members.add("Level 5");
       members.add("Level 6");

        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,members);
        ListView lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Start(arg2+1);
            }
        });
    }

    public void Start(int i) {
        setContentView(new FroggerView(this,i));
    }

}
