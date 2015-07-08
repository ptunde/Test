package com.example.tundepeter.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tundepeter.test.fragment.PersonListFragment;
import com.example.tundepeter.test.model.Person;


public class MainActivity extends ActionBarActivity implements PersonListFragment.OnPersonSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onPersonSelected(Person person) {
        // start second activity
    }
}
