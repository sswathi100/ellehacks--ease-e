package com.example.ssendhil.smstrial;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {
    Model mModel;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private static final int SWITCH_INTENT_DELAY = 3000;
    Button breatheButton;
    Button breatheButton2;
    Button breatheButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mModel = Model.getInstance();
        mModel.addObserver(this);
        mModel.setContext(getApplicationContext());

        while (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            getPermission();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Outreach.class);
                getApplicationContext().startActivity(intent);
            }
        }, 300);

        breatheButton = findViewById(R.id.breathe_button);

        breatheButton2 = findViewById(R.id.breathe2_button);

        breatheButton3 = findViewById(R.id.breathe3_button);

        breatheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), Outreach.class);
                        intent.putExtra("duration", 15000);
                        getApplicationContext().startActivity(intent);
                    }
                }, 100);
            }
        });

        breatheButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), Outreach.class);
                        intent.putExtra("duration", 30000);
                        getApplicationContext().startActivity(intent);
                    }
                }, 100);
            }
        });

        breatheButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), Outreach.class);
                        intent.putExtra("duration", 100000);
                        mModel.sendSMSMessage();
                        getApplicationContext().startActivity(intent);
                    }
                }, 100);
            }
        });
    }

    protected void getPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.SEND_SMS)) {
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("destroy", "MainActivity: onDestory");
        // Remove observer when activity is destroyed.
        mModel.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d("Update", "Came here");
    }
}
