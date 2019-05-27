package com.example.ssendhil.ease_e;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class Settings extends AppCompatActivity implements Observer {

    //TODO: create a page to customize phoneNo, message, and video content
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d("Update", "Came here");
    }
}
