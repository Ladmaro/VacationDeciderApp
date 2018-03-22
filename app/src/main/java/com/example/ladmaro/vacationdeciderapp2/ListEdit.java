package com.example.ladmaro.vacationdeciderapp2;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListEdit extends Fragment {

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    public ListEdit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_list_edit, container, false);


        mListView = (ListView) v.findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        populateListView();


        return v;
    }

    private void populateListView() {

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);


        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
                    Intent editScreenIntent = new Intent(getActivity(), EditDataFragment.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("country",country);
                    editScreenIntent.putExtra("city", city);
                    editScreenIntent.putExtra("hotel", hotel);
                    editScreenIntent.putExtra("expend", expend);
                    editScreenIntent.putExtra("fly", fly);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }

    }

