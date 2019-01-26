package com.example.a2019soshay.alarmgame;
/*import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.nio.charset.IllegalCharsetNameException;
import java.util.Calendar;

public class Alarm extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // When alarm comes, my code will go here. Pass on to other activity ?
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // Toast!
        Intent i = new Intent(); //Intent
        i.setClassName("com.example.a2019soshay.alarmgame", "com.example.a2019soshay.alarmgame.MathQuiz");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //Taking this out?
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //context.startActivity(i); //The problem line!!!!!


        wl.release();
    }

    public void setAlarm(Context context, Calendar cal)
    {
        Calendar cal2 = Calendar.getInstance();
        //Intent intentAlarm = Intent(context, AlaramFireReceiver::class.java).let {
        //PendingIntent.getBroadcast(context, tick, it, PendingIntent.FLAG_ONE_SHOT)


//alarm fire next day if this condition is not statisfied
        if (cal.before(Calendar.getInstance())) {
            cal.add(Calendar.DATE, 1);
        }
        //if(cal.get(Calendar.HOUR)<cal2.get(Calendar.HOUR)){//If this time has already passed, go to the next day
       //     cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1); //Obviously does not work for end of month can work around that do not care right now
    //}
        AlarmManager am =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}*/
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example
        Intent i = new Intent(); //Intent
        i.setClassName("com.example.a2019soshay.alarmgame", "com.example.a2019soshay.alarmgame.MathQuiz");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //Taking this out?
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i); //The problem line!!!!!
    }

    public void setAlarm(Context context, Calendar cal)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        Log.i("message",Long.toString(cal.getTimeInMillis())+" "+Long.toString(System.currentTimeMillis()));
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1000 * 60 , pi); // Millisec * Second * Minute
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}