package com.example.ladmaro.vacationdeciderapp2;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    DrawerLayout mdrawerlayout;

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);

// Create instance of mToggle
        mToggle = new ActionBarDrawerToggle(this, mdrawerlayout, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
            }
        };

        // Toggle ends

        mdrawerlayout.addDrawerListener(mToggle);


        //Attaching itemclick to Navi drawer
        listView = findViewById(R.id.left_drawer);
        listView.setOnItemClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                Intent intent = new Intent(this, PickTravel.class);
                startActivity(intent);
                break;
            case 1:
                ShowCountries show = new ShowCountries();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_frame, show).commit();
                break;
            case 2:
                AddTravel add = new AddTravel();
                FragmentManager addManager = getSupportFragmentManager();
                addManager.beginTransaction().replace(R.id.main_frame, add).commit();
                break;
            case 3:
                ListEdit edit = new ListEdit();
                FragmentManager editManager = getSupportFragmentManager();
                editManager.beginTransaction().replace(R.id.main_frame, edit).commit();
                break;
            case 4:
                SetCurrency set = new SetCurrency();
                FragmentManager setManager = getSupportFragmentManager();
                setManager.beginTransaction().replace(R.id.main_frame, set).commit();
                break;
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }
}
