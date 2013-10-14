package ru.liversale.miemdevelopers;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Juko
 * Date: 30.09.13
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 */
public class DataApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       // startService(new Intent(this,HeartBeatService.class));
        new SharedPreferenceManager(this);
        new DatabaseHandler(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
