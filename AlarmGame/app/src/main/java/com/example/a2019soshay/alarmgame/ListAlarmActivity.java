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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ListAlarmActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    private ArrayList<String> mNames;
    private ArrayList<String> mLabels;
    private ArrayList<Boolean> mButtons;
    private MyRecyclerViewAdapter adapter;
    private boolean mNewAlarm;
    private int mPositionDelete;
    private String mLabel;
    private boolean mDelete;
    private ArrayList<ArrayList<String>> mEverything;
    private boolean mCameFromAlarm;
    private boolean mAlarmChange;
    private int mPosition;
    private ArrayList<Alarm> mAlarms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_alarm);
        mPrefs = getSharedPreferences("label", 0);
        //mAlarms = mPrefs.getAll().get("my_alarms");
        //mLabels = mPrefs.getAll().get("my_labels");
        //mButtons = mPrefs.getAll().get("my_buttons");

        mEditor = mPrefs.edit();
        try {
            mNames = (ArrayList<String>) ObjectSerializer.deserialize(mPrefs.getString("name_list", ObjectSerializer.serialize(new ArrayList<String>())));
            mLabels = (ArrayList<String>) ObjectSerializer.deserialize(mPrefs.getString("label_list", ObjectSerializer.serialize(new ArrayList<String>())));
            mButtons = (ArrayList<Boolean>) ObjectSerializer.deserialize(mPrefs.getString("button_list", ObjectSerializer.serialize(new ArrayList<Boolean>())));
            mAlarms = (ArrayList<Alarm>) ObjectSerializer.deserialize(mPrefs.getString("alarm_list", ObjectSerializer.serialize(new ArrayList<Alarm>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //mAlarms.add("6:30");
        try {
            mEditor.putString("name_list", ObjectSerializer.serialize(mNames));
            mEditor.putString("label_list", ObjectSerializer.serialize(mLabels));
            mEditor.putString("alarm_list", ObjectSerializer.serialize(mAlarms));
            mEditor.putString("button_list", ObjectSerializer.serialize(mButtons));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //mEditor.commit();
        mNewAlarm = getIntent().getBooleanExtra("new_alarm",false);
        mCameFromAlarm= getIntent().getBooleanExtra("came_from_alarm",false);
        if (mCameFromAlarm){
            mAlarmChange = getIntent().getBooleanExtra("alarm_changed",false);
            mPosition = getIntent().getIntExtra("position",-1);
            if(mNewAlarm){
                Alarm newAlarm= new Alarm();
                String newName = getIntent().getStringExtra("name");
                String newLabel = getIntent().getStringExtra("label");
                Boolean on = getIntent().getBooleanExtra("on",false);
                if (on){
                    // do stuff after this
                }
            }
            if (mAlarmChange){
                if ((mButtons.get(mPosition))==false){
                    mAlarms.get(mPosition).cancelAlarm(this);
                    mAlarms.remove(mPosition);
                    mLabels.remove(mPosition);
                    mNames.remove(mPosition);
                    mButtons.remove(mPosition);
                    mEverything.remove(mPosition);
                }
                else{
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("KK:mm aa", Locale.US);
                    Date d1=cal.getTime();
                    try{
                        d1 = df.parse(mNames.get(mPosition));
                    }
                    catch (ParseException e){
                        e.printStackTrace();
                    }
                    Calendar c1 = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    c1.setTime(d1);
                    mAlarms.get(mPosition).setAlarm(this, cal);
                }

            }
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.workshopParticipants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(ListAlarmActivity.this, mEverything); //what list should i use
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
