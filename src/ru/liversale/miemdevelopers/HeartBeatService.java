package ru.liversale.miemdevelopers;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Juko
 * Date: 30.09.13
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
public class HeartBeatService extends Service {
    private final static int BEAT_DELAY = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Toast.makeText(HeartBeatService.this.getApplicationContext(), "BEAT!", Toast.LENGTH_LONG).show();
            }
        } ;
        Timer timer = new Timer();
        timer.schedule(timerTask,0,BEAT_DELAY);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
