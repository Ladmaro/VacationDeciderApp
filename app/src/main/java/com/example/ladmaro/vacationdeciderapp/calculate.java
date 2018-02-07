package com.example.ladmaro.vacationdeciderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class calculate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        Intent intent = getIntent();

        String msg = intent.getStringExtra(MainActivity.MSG);

        TextView textView = (TextView)findViewById(R.id.landTxt);
        textView.setText(msg);
    }
}
