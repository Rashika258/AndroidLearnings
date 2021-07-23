package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MusicLibActivity extends AppCompatActivity {

    ImageView MusicLibraryTV, SubscriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_lib);

        MusicLibraryTV=(ImageView)findViewById(R.id.musicLibraryTv);
        SubscriptionTv=(ImageView)findViewById(R.id.subscriptionTv);

        MusicLibraryTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MusicLibActivity.this, SongsListActivity.class);
                startActivity(i);
            }
        });

        SubscriptionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MusicLibActivity.this, SubscriptionActivity.class);
            }
        });
    }


}