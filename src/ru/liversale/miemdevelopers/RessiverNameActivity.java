package ru.liversale.miemdevelopers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Juko
 * Date: 30.09.13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public class RessiverNameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ressiver_name_layout);
        String ressivedName = getIntent().getExtras().getString("name");
        ((TextView)findViewById(R.id.ressiver_text_textView)).setText(ressivedName);
    }
}
