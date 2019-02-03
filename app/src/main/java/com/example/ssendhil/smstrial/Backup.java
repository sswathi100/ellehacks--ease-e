package com.example.ssendhil.smstrial;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Observable;
import java.util.Observer;

public class Backup extends AppCompatActivity implements Observer {

    private View mContentView;
    private View mContentOutView;
    private Model mModel;
    private Boolean breathIn = false;
    private FrameLayout myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_outreach);

        mContentView = findViewById(R.id.breath_in_content);
        mContentOutView = findViewById(R.id.breath_out_content);

        mModel = Model.getInstance();
        mModel.addObserver(this);

        myView = findViewById(R.id.view);

        final Handler h = new Handler();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread
                Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object
                h.postDelayed(this, 2000);
                crossfade();
            }
        };
        h.post(runnableCode);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 10000);
    }

    private void crossfade() {
        View fadeInView = mContentView;
        View fadeOutView = mContentOutView;
        if (breathIn) {
            fadeInView.setVisibility(View.VISIBLE);
            fadeOutView.setVisibility(View.GONE);
            myView.setBackgroundColor(Color.RED);
            breathIn = false;
        } else {
            fadeOutView.setVisibility(View.VISIBLE);
            fadeInView.setVisibility(View.GONE);
            myView.setBackgroundColor(Color.GREEN);
            breathIn = true;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d("Update", "Came here");
    }
}
