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
    public static ArrayList<String> mNames;
    private ArrayList<String> mLabels;
    public static ArrayList<Boolean> mButtons;
    private MyRecyclerViewAdapter adapter;
    private boolean mNewAlarm;
    private int mPositionDelete;
    private String mLabel;
    private boolean mDelete;
    private ArrayList<ArrayList<String>> mEverything;
    private boolean mCameFromAlarm;
    private boolean mAlarmChange;
    private boolean mChangedLabel;
    private int mPosition;
    public static ArrayList<Alarm> mAlarms;
    private Button mAddButton;
    private Boolean mDeletedAlarm;
    private int mHour;
    private int mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_alarm);
        startService(new Intent(this,YourService.class));
        mPrefs = getSharedPreferences("label", 0);
        //mAlarms = mPrefs.getAll().get("my_alarms");
        //mLabels = mPrefs.getAll().get("my_labels");
        //mButtons = mPrefs.getAll().get("my_buttons");
        mAddButton = (Button) findViewById(R.id.add_button);
        mEverything = new ArrayList<ArrayList<String>>();
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
        mChangedLabel = getIntent().getBooleanExtra("label_changed",false);
        if (mCameFromAlarm){
            mHour =getIntent().getIntExtra("hour",-1);
            mMinute = getIntent().getIntExtra("minute",-1);
            mAlarmChange = getIntent().getBooleanExtra("alarm_changed",false);
            mPosition = getIntent().getIntExtra("position",-1);
            mDeletedAlarm = getIntent().getBooleanExtra("deleted_alarm",false);
            if(mNewAlarm) {
                Alarm newAlarm = new Alarm();
                String newName = getIntent().getStringExtra("name");
                String newLabel = getIntent().getStringExtra("label");
                mNames.add(newName);
                mButtons.add(true);
                mAlarms.add(newAlarm);
                mLabels.add(newLabel); //adding everything
                ArrayList<String> result = new ArrayList<String>();
                result.add(newName);
                result.add(newLabel);
                result.add("ON");
                mEverything.add(result);
                Calendar cal = Calendar.getInstance();
                /*SimpleDateFormat df = new SimpleDateFormat("kk:mm", Locale.US);

                Date d1=cal.getTime();
                try{
                    d1 = df.parse(mNames.get(mPosition));
                }
                catch (ParseException e){
                    e.printStackTrace();
                }
                //d1.setYear(2019);

                Calendar c1 = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                c1.setTime(d1); // this goes to 1970 maybe just set hour and minute would be easier
                Calendar c2 = Calendar.getInstance();
                c1.set(Calendar.YEAR,c2.get(Calendar.YEAR));
                c1.set(Calendar.DAY_OF_MONTH,c2.get(Calendar.DAY_OF_MONTH));
                c1.set(Calendar.DATE,c2.get(Calendar.DATE));*/
                cal.set(Calendar.HOUR_OF_DAY,mHour);
                cal.set(Calendar.MINUTE,mMinute);
                cal.set(Calendar.SECOND,0);
                mAlarms.get(mPosition).setAlarm(ListAlarmActivity.this, cal);/////////////////////// THIS IS THE RELEVANT LINE
            }
            if (mAlarmChange){ //doesn't apply to deleted alarms
                if (mButtons.get(mPosition)){
                    mAlarms.get(mPosition).cancelAlarm(this);
                    mButtons.set(mPosition, false);
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
                    mButtons.set(mPosition, true);
                }
            }
            if(mDeletedAlarm){
                if(mButtons.get(mPosition)) {
                    mAlarms.get(mPosition).cancelAlarm(this);
                }
                    mAlarms.remove(mPosition);
                    mLabels.remove(mPosition);
                    mNames.remove(mPosition);
                    mButtons.remove(mPosition);
                    mEverything.remove(mPosition);
            }
        }
        if (mEverything!=null && mEverything.size()!=0) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.workshopParticipants);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyRecyclerViewAdapter(ListAlarmActivity.this, mEverything); //what list should i use
            adapter.setClickListener(this);//This has a solution i'll ask later
            recyclerView.setAdapter(adapter);

        }
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListAlarmActivity.this, MainSetActivity.class);
                if(mEverything==null){
                    i.putExtra("position",0);
                }
                else {
                    i.putExtra("position", mEverything.size());
                }
                i.putExtra("new_alarm",true);
                startActivity(i);
            }
        });
    }
    @Override
    public void onItemClick(View view, int position){
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,MainSetActivity.class);
        i.putExtra("position",position);
        startActivity(i);
    }

}
