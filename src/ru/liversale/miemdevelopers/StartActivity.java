package ru.liversale.miemdevelopers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity implements View.OnClickListener {

    Button myButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myButton = (Button) findViewById(R.id.button_mybutton);
        myButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_mybutton:
                Intent startTwoLessonActivityIntent = new Intent(this, LessonTwoActivity.class);
                startActivity(startTwoLessonActivityIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
