package com.example.ladmaro.vacationdeciderapp2;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PickTravel extends AppCompatActivity {


    DatabaseHelper mDatabaseHelper;

    private Spinner spSpinner;
    TextView cities;
    TextView resorts;
    ImageView flag;
    Button next;
    TextView exp;
    TextView ticket;
    TextView currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_travel);


        spSpinner = (Spinner) findViewById(R.id.spSpinner);
        cities = (TextView) findViewById(R.id.cityTextView);
        resorts = (TextView) findViewById(R.id.hotelTextView);
        flag = (ImageView) findViewById(R.id.flagImg);
        next = (Button) findViewById(R.id.calcBtn);
        exp = (TextView) findViewById(R.id.expTxt);
        ticket = (TextView) findViewById(R.id.flyTxt);
        currency = (TextView) findViewById(R.id.txtCurrency);
        mDatabaseHelper = new DatabaseHelper(PickTravel.this);

        Intent intent = getIntent();
        final String setCurrency = intent.getStringExtra("currency");
        currency.setText(setCurrency);

        populateListView();
    }

        private void populateListView() {

            //get the data and append to a list
            Cursor data = mDatabaseHelper.getData();
            ArrayList<String> listData = new ArrayList<>();
            while (data.moveToNext()) {
                //get the value from the database in column 1
                //then add it to the ArrayList
                listData.add(data.getString(1));
            }
            //create the list adapter and set the adapter
            ArrayAdapter adapter = new ArrayAdapter<>(PickTravel.this, android.R.layout.simple_list_item_1, listData);
            spSpinner.setAdapter(adapter);

            spSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String country = adapterView.getItemAtPosition(i).toString();
                    String city = adapterView.getItemAtPosition(i).toString();
                    String hotel = adapterView.getItemAtPosition(i).toString();
                    String expend = adapterView.getItemAtPosition(i).toString();
                    String fly = adapterView.getItemAtPosition(i).toString();

                    Cursor data = mDatabaseHelper.getItemID2(country); //get the id associated with that name
                    int itemID = -1;
                    while(data.moveToNext()){
                        itemID = data.getInt(0);
                        city = data.getString(1);
                        hotel = data.getString(2);
                        expend = data.getString(3);
                        fly = data.getString(4);
                    }
                    if(itemID > -1){
                        cities.setText("City: " + city);
                        resorts.setText("Hotel: " + hotel);
                        exp.setText("" + expend);
                        ticket.setText("" + fly);
                        flag.setImageResource(R.mipmap.ic_launcher);
                    }
                    if (country.equals("England")) {
                        flag.setImageResource(R.drawable.uk);
                    } if (country.equals("Germany")) {
                        flag.setImageResource(R.drawable.germany);
                    } if (country.equals("France")) {
                        flag.setImageResource(R.drawable.france);
                    } if (country.equals("Poland")) {
                        flag.setImageResource(R.drawable.poland);
                    } if (country.equals("USA")) {
                        flag.setImageResource(R.drawable.us);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    toastMessage("No ID associated with that name");
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PickTravel.this, Calculate.class);
                    intent.putExtra("land", spSpinner.getSelectedItem().toString());
                    intent.putExtra("expenditure", exp.getText().toString());
                    intent.putExtra("fly", ticket.getText().toString());
                    intent.putExtra("currency", currency.getText().toString());
                    startActivity(intent);
                }
            });

        }

        private void toastMessage(String message){
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        }


    }
