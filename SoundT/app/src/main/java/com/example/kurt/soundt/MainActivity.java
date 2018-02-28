package com.example.kurt.soundt;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.BtnPlay);
        final MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.ring);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Play(mediaPlayer);
            }
        });
    }

    public void Play(MediaPlayer mediaPlayer){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
        }
    }
}
