package ru.liversale.miemdevelopers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: Juko
 * Date: 30.09.13
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
public class LessonTwoActivity extends Activity {
    EditText enterNameEditText;
    Button acceptNameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_two_layout);
        enterNameEditText = (EditText)findViewById(R.id.enter_name_editText);
        acceptNameButton = (Button)findViewById(R.id.accept_name_button);
        acceptNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonTwoActivity.this, RessiverNameActivity.class);
                intent.putExtra("name", enterNameEditText.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
