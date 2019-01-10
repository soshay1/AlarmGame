package com.example.a2019soshay.alarmgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainSetActivity extends AppCompatActivity {
    private Button mSetAlarm;
    private Button mSetSound;
    private Button mDeleteAlarm;
    private Button mSetLabel;
    private boolean mNewAlarm;
    private int mPosition;
    private String mLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_set);
        mSetAlarm = (Button) findViewById(R.id.set_alarm);
        mSetSound = (Button) findViewById(R.id.set_sound);
        mDeleteAlarm = (Button) findViewById(R.id.delete_alarm);
        mNewAlarm = getIntent().getBooleanExtra("new_alarm", false);
        mLabel = getIntent().getStringExtra("label");
        mPosition = getIntent().getIntExtra("position",-1);
        mSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSetActivity.this, ListAlarmActivity.class);
                if (mNewAlarm){
                    i.putExtra("new_alarm", true);
                }
                if(mLabel!=null){
                    i.putExtra("label",mLabel);
                }
                else{
                    i.putExtra("label","Alarm");
                }
            }
        });
        mDeleteAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSetActivity.this, ListAlarmActivity.class);
                if(mNewAlarm) {
                    i.putExtra("new_alarm",true);
                    startActivity(i);
                }
                else{
                    i.putExtra("delete_alarm",true);
                    i.putExtra("position",mPosition);
                }
            }
        });
        mSetLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSetActivity.this, LabelActivity.class);
                i.putExtra("new_alarm",mNewAlarm);
                i.putExtra("position",mPosition);
                startActivity(i);
            }
        });
    }
}
