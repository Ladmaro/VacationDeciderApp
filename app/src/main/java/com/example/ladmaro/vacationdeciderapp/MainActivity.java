package com.example.ladmaro.vacationdeciderapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String MSG = "";

    String[] cities;
    String[] hotel;

    Spinner sp;
    TextView city;
    TextView resort;
    ImageView flag;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        cities = res.getStringArray(R.array.city);
        hotel = res.getStringArray(R.array.hotel);

        ArrayAdapter<String> adapter;

        sp = (Spinner) findViewById(R.id.spSpinner);
        city = (TextView) findViewById(R.id.cityTextView);
        resort = (TextView) findViewById(R.id.hotelTextView);
        flag = (ImageView) findViewById(R.id.flagImg);
        next = (Button)findViewById(R.id.calcBtn);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                    city.setText("" + cities[position]);
                    resort.setText("" + hotel[position]);
                    flag.setImageResource(R.drawable.uk);
                        break;
                    case 1:
                        city.setText("" + cities[position]);
                        resort.setText("" + hotel[position]);
                        flag.setImageResource(R.drawable.germany);
                        break;
                    case 2:
                        city.setText("" + cities[position]);
                        resort.setText("" + hotel[position]);
                        flag.setImageResource(R.drawable.france);
                        break;
                    case 3:
                        city.setText("" + cities[position]);
                        resort.setText("" + hotel[position]);
                        flag.setImageResource(R.drawable.poland);
                        break;
                    case 4:
                        city.setText("" + cities[position]);
                        resort.setText("" + hotel[position]);
                        flag.setImageResource(R.drawable.us);
                        break;
                        default:
                            break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,calculate.class);
                intent.putExtra(MSG,sp.getSelectedItem().toString());
                startActivity(intent);
            }
        });


        }



    }
