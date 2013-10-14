package ru.liversale.miemdevelopers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Juko
 * Date: 14.10.13
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */

class Item {
    public int Id;
    public String Text;
    public String Lng;
    public Double Lat;
}

public class LessonThreeActivity extends Activity {
    CheckBox yourMamaCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_three_layout);
        yourMamaCheckBox = (CheckBox)findViewById(R.id.checkBox);
        yourMamaCheckBox.setChecked(SharedPreferenceManager.isBig());
        yourMamaCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               SharedPreferenceManager.setBig(isChecked);
            }
        });

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Event event = new Event();
        event.setTitle("Event at "+ String.valueOf(System.currentTimeMillis()));
        event.setLat(55.23123);
        event.setLng(37.45533);
        databaseHandler.addEvent(event);
        ArrayList<Event> events = databaseHandler.getEvents();

        try {
            MobileServiceClient mClient = new MobileServiceClient(
                     "https://advanturetravel.azure-mobile.net/",
                     "lMCIgGztOEyqRnDpjSQMLRFxzBPPxX68",
                     this
             );

            Item item = new Item();
            item.Text = "ТРЕТЬЕЛЕКЦИОННЫЙ ПРЕКРАСНЫЙ ЭЛЕМЕНТ";
            item.Lat = (double)System.currentTimeMillis();
            item.Lng= "21123.0";
            mClient.getTable(Item.class).insert(item, new TableOperationCallback<Item>() {
                public void onCompleted(Item entity, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        // Insert succeeded
                    } else {
                        // Insert failed
                    }
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
