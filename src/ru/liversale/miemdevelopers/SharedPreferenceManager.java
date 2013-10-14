package ru.liversale.miemdevelopers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



/**
 * Created with IntelliJ IDEA.
 * User: Juko
 * Date: 28.01.13
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */
public class SharedPreferenceManager {

    static SharedPreferences preferences;
    public static final String SO_BIG = "so_big";

    public SharedPreferenceManager(Context context) {
       preferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public static void setBig(boolean big) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SO_BIG, big);
        editor.commit();
    }

    public static boolean isBig() {
        return preferences.getBoolean(SO_BIG, false);
    }

    public static void clear(){
    SharedPreferences.Editor editor = preferences.edit();
    editor.remove(SO_BIG);
    editor.commit();
    }
}
