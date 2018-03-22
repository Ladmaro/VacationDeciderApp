package com.example.ladmaro.vacationdeciderapp2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTravel extends Fragment {

    EditText etCountry, etCity, etHotel, etExpend, etFly;
    Button btnAdd, btnView;
    DatabaseHelper myDB;


    public AddTravel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_travel, container, false);

        etCountry = (EditText) v.findViewById(R.id.addCountry);
        etCity = (EditText) v.findViewById(R.id.addCity);
        etHotel = (EditText) v.findViewById(R.id.addHotel);
        etExpend = (EditText) v.findViewById(R.id.addExpend);
        etFly = (EditText) v.findViewById(R.id.addFly);
        btnAdd = (Button) v.findViewById(R.id.btnAdd);
        myDB = new DatabaseHelper(getActivity());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = etCountry.getText().toString();
                String city = etCity.getText().toString();
                String hotel = etHotel.getText().toString();
                String expenditure = etExpend.getText().toString();
                String fly = etFly.getText().toString();
                if (country.length() != 0 && city.length() != 0 && hotel.length()!= 0 && expenditure.length() != 0 && fly.length() != 0) {
                    AddData(country, city, hotel, expenditure, fly);
                    etCountry.setText("");
                    etCity.setText("");
                    etHotel.setText("");
                    etExpend.setText("");
                    etFly.setText("");
                }else {
                    Toast.makeText(getActivity(),"You must put something in the text fields",Toast.LENGTH_LONG).show();
                }

            }
        });

        return v;
    }

    public void AddData(String cntry,String cty, String htl, String expnd, String flight ){
        boolean insertData = myDB.addData(cntry,cty,htl,expnd,flight);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }


    }

