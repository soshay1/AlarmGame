package com.example.a2019soshay.alarmgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAlarmActivity extends AppCompatActivity extends Broadcas {
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    private ArrayList<String> mAlarms;
    private ArrayList<String> mLabels;
    private ArrayList<Boolean> mButtons;
    private MyRecyclerViewAdapter adapter;
    private boolean mNewAlarm;
    private int mPositionDelete;
    private String mLabel;
    private boolean mDelete;
    private boolean mCameFromAlarm;
    private boolean mAlarmChange;
    private int mPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_alarm);
        mPrefs = getSharedPreferences("label", 0);
        mAlarms = mPrefs.getAll().get("my_alarms");
        mLabels = mPrefs.getAll().get("my_labels");
        mButtons = mPrefs.getAll().get("my_buttons");
        mEditor = mPrefs.edit();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.workshopParticipants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNewAlarm = getIntent().getBooleanExtra("new_alarm",false);
        mCameFromAlarm= getIntent().getBooleanExtra("came_from_alarm",false);
        if (mCameFromAlarm){
            mAlarmChange = getIntent().getBooleanExtra("alarm_changed",false);
            mPosition = getIntent().getIntExtra("position",-1);
            if (mAlarmChange){
                if ((mButtons.get(mPosition))==false){

                }
                    //cancel alarm
                //if alarm turned on '' '' '' '':
                    //start alarm
            }
        }
        adapter = new MyRecyclerViewAdapter(this, myAlarm); //what list should i use
        adapter.setClickListener(this);//This has a solution i'll ask later
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemClick(View view, int position){
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,MainSetActivity.class);
        startActivity(i);
    }
}
