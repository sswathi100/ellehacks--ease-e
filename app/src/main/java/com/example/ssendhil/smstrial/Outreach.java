package com.example.ssendhil.smstrial;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Observable;
import java.util.Observer;

public class Outreach extends AppCompatActivity implements Observer {

    private View mContentView;
    private View mContentOutView;
    private Model mModel;
    private Boolean breathIn = true;
    private FrameLayout myView;
    MediaPlayer player;


    Button btnPlay, btnPause, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_outreach);
        Intent intent = getIntent();
        final long waitTime = (long) intent.getIntExtra("duration", 0);

        mContentView = findViewById(R.id.breath_in_content);
        mContentOutView = findViewById(R.id.breath_out_content);

        mModel = Model.getInstance();
        mModel.addObserver(this);

        myView = findViewById(R.id.view);
        play();
        final Handler h = new Handler();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread
                Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object
                h.postDelayed(this, 4000);
                crossfade();
            }
        };
        h.post(runnableCode);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                player.stop();
                finish();
            }
        }, waitTime);
    }

    private void crossfade() {
        View fadeInView = mContentView;
        View fadeOutView = mContentOutView;
        if (breathIn) {
            fadeInView.setVisibility(View.VISIBLE);
            fadeOutView.setVisibility(View.GONE);
            myView.setBackgroundColor(Color.parseColor("#a9e5e2"));
            breathIn = false;
        } else {
            fadeOutView.setVisibility(View.VISIBLE);
            fadeInView.setVisibility(View.GONE);
            myView.setBackgroundColor(Color.parseColor("#efc4dd"));
            breathIn = true;
        }
    }

    private void play() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.calming);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    private void pause() {

        if (player == null) {
            player.pause();
        }
    }

    private void stop() {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d("Update", "Came here");
    }
}
