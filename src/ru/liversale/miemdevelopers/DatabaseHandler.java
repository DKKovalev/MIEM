package ru.liversale.miemdevelopers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class Event{
    String title;
    Double lat, lng;

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    Double getLat() {
        return lat;
    }

    void setLat(Double lat) {
        this.lat = lat;
    }

    Double getLng() {
        return lng;
    }

    void setLng(Double lng) {
        this.lng = lng;
    }
}

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_ver_" + DATABASE_VERSION + ".db";

    private static final String EVENTS = "events";

    private static final String TITLE = "title";
    private static final String LAT = "lat";
    private static final String LNG = "lng";

    Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_EVENTS = "CREATE TABLE "
                + EVENTS + "("
                + TITLE + " STRING,"
                + LAT + " DOUBLE,"
                + LNG + " DOUBLE)" ;
        db.execSQL(CREATE_TABLE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + EVENTS);
        onCreate(db);
    }

    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("PRAGMA synchronous=OFF");
            db.setLockingEnabled(false);
            db.beginTransaction();
            int newId = db.update(EVENTS, getContentValues(event), "title='" + event.getTitle() + "'", null);
            if (newId == 0) {
                db.insert(EVENTS, null, getContentValues(event));
            }
            db.setTransactionSuccessful();

        } catch (Exception e) {

        } finally {
            db.endTransaction();
            db.setLockingEnabled(true);
            db.execSQL("PRAGMA synchronous=NORMAL");
        }

        db.close();
    }

    private ContentValues getContentValues(Event event) {
        ContentValues values = new ContentValues();
        values.put(TITLE, event.getTitle());
        values.put(LAT, event.getLat());
        values.put(LNG, event.lng);
        return values;
    }

    public ArrayList<Event> getEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Event> events = new ArrayList<Event>();
        Cursor eventsCursor = db.rawQuery("SELECT * FROM " + EVENTS, null);
        if (eventsCursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setTitle(eventsCursor.getString(0));
                event.setLat(eventsCursor.getDouble(1));
                event.setLng(eventsCursor.getDouble(2));
                events.add(event);
            } while (eventsCursor.moveToNext());
        }
        eventsCursor.close();
        db.close();
        return events;
    }

    public void formatDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EVENTS, null, null);
        db.close();
    }

    public Event getEvent(String title) throws Exception {
        ArrayList<Event> events =getEvents();
        for (Event event : events)
            if (event.getTitle().equals(title)) {
                return event;
            }
        throw new Exception("Event  with titel=" + title + " not found");
    }
}
