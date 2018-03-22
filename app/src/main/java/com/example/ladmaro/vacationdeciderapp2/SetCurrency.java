package com.example.ladmaro.vacationdeciderapp2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetCurrency extends Fragment {

    Button set;
    Spinner spSpinner;

    public SetCurrency() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_set_currency, container, false);

        spSpinner = (Spinner) v.findViewById(R.id.spCurrency);
        set = (Button) v.findViewById(R.id.btnSet);


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PickTravel.class);
                intent.putExtra("currency", spSpinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });




        return v;
    }

}
