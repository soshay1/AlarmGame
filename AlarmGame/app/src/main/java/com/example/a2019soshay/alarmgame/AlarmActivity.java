package com.example.a2019soshay.alarmgame;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 0);
        calendar.add(Calendar.MINUTE, 0);
        calendar.add(Calendar.HOUR, 3);

        //Intent _myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        //_myIntent.putExtra("MyMessage","Content of the message");
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 123, _myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //AlarmManager alarmManager = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24, pendingIntent);
    }
}
