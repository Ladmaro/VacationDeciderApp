package com.example.ladmaro.vacationdeciderapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditDataFragment extends AppCompatActivity {

    private Button btnSave;
    private TextView countryNow, cityNow, hotelNow;
    private EditText editable_expend, editable_fly;

    DatabaseHelper mDatabaseHelper;

    private String selectedExpend, selectFly, selectCountry, selectCity, selectHotel;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_fragment);

        btnSave = (Button) findViewById(R.id.btnUpdate);
        countryNow = (TextView) findViewById(R.id.txtNowCountry);
        cityNow = (TextView) findViewById(R.id.txtNowCity);
        hotelNow = (TextView) findViewById(R.id.txtNowHotel);
        editable_expend = (EditText) findViewById(R.id.newExpend);
        editable_fly = (EditText) findViewById(R.id.newFly);
        mDatabaseHelper = new DatabaseHelper(this);


        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectCountry = receivedIntent.getStringExtra("country");

        //now get the name we passed as an extra
        selectCity = receivedIntent.getStringExtra("city");

        //now get the name we passed as an extra
        selectHotel = receivedIntent.getStringExtra("hotel");

        //now get the name we passed as an extra
        selectedExpend = receivedIntent.getStringExtra("expend");

        //now get the name we passed as an extra
        selectFly = receivedIntent.getStringExtra("fly");

        //set the text to show the current selected name
        countryNow.setText(selectCountry);

        //set the text to show the current selected name
        cityNow.setText(selectCity);

        //set the text to show the current selected name
        hotelNow.setText(selectHotel);

        //set the text to show the current selected name
        editable_expend.setText(selectedExpend);

        //set the text to show the current selected name
        editable_fly.setText(selectFly);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemExpend = editable_expend.getText().toString();
                String itemFly = editable_fly.getText().toString();
                if(!itemExpend.equals("") && !itemFly.equals("")){
                    mDatabaseHelper.updateName(itemExpend, itemFly, selectedID, selectedExpend, selectFly);
                    Toast.makeText(EditDataFragment.this,"Updated!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(EditDataFragment.this,"You must enter a name",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
