package com.example.a2019soshay.alarmgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LabelActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mNext;

    public LabelActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
        mEditText = (EditText) findViewById(R.id.edit_label);
        mNext = (Button) findViewById(R.id.next);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LabelActivity.this,MainSetActivity.class);
                i.putExtra("label",mEditText.getText());
                startActivity(i);
            }
        });
    }
}
