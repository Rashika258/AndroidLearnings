package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
//Initialise variable
    TextView playerPosition, playerDuration;
    SeekBar seekBar;
    ImageView btRew, btPlay, btPause, btFf;

    MediaPlayer mediaPlayer;
    Handler handler=new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign variable
        playerPosition=findViewById(R.id.player_position);
        playerDuration=findViewById(R.id.player_duration);
        seekBar=findViewById(R.id.seek_bar);
        btRew=findViewById(R.id.bt_rew);
        btPlay=findViewById(R.id.bt_play);
        btPause=findViewById(R.id.bt_pause);
        btFf=findViewById(R.id.bt_ff);

        //initialise media player
        mediaPlayer=MediaPlayer.create(this, R.raw.music);

        //Initialize runnable
        runnable=new Runnable() {
            @Override
            public void run() {
                //set progress on seek bar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                //handler post delay for 0.5 second
                handler.postDelayed(this, 500);
                }
        };

        //get duration of media player
        int duration=mediaPlayer.getDuration();
        //convert millisecond to minute and second
        String sDuration=convertFormat(duration);
        //set duration on text view
        playerDuration.setText(sDuration);

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide play button
                btPlay.setVisibility(View.GONE);
                //show pause button
                btPause.setVisibility(View.VISIBLE);
                //start media player
                mediaPlayer.start();
                //set max on seek bar
                seekBar.setMax(mediaPlayer.getDuration());
                //start handler - this will call the runnable after 0 milliseconds
                handler.postDelayed(runnable,0);
            }
        });

        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide pause button
                btPause.setVisibility(View.GONE);
                //show play button
                btPlay.setVisibility(View.VISIBLE);
                //pause media player
                mediaPlayer.pause();
                //stop handler - it calls the runnable interface itself to unregister all the callbacks
                handler.removeCallbacks(runnable);
            }
        });

        btFf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get current position of media player
                int currentPosition=mediaPlayer.getCurrentPosition();
                //get duration of media player
                int duration=mediaPlayer.getDuration();
                //check condition
                if(mediaPlayer.isPlaying() && duration != currentPosition) {
                    //when media is playing and duration is not equal to current position
                    //fast forward for 5 second
                    currentPosition=currentPosition+5000;
                    //set current position and text view
                    playerPosition.setText(convertFormat(currentPosition));
                    //set progress on seek bar
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });

        btRew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get current position of media player
                int currentPosition=mediaPlayer.getCurrentPosition();
                //check condition
                if(mediaPlayer.isPlaying() && currentPosition > 5000) {
                    //when media is playing and current position in greater than 5 seconds
                    //rewind for 5 seconds
                   currentPosition=currentPosition-5000;
                   //get current position on textview
                    playerPosition.setText(convertFormat(currentPosition));
                    //set progress on seek bar
                    mediaPlayer.seekTo(currentPosition);

                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //check condition
                if(fromUser) {
                    //when drag the seek bar
                    //set progress on seek bar
                    mediaPlayer.seekTo(progress);
                }
                //set current position on text view
                playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //hide pause button
                btPause.setVisibility(View.GONE);
                //show play button
                btPlay.setVisibility(View.VISIBLE);
                //set media player to initial position
                mediaPlayer.seekTo(0);
            }
        });
    }
    private String convertFormat(int duration) {
        //5:03 --> 5min : (5:03)sec-(5)sec
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration), TimeUnit.MILLISECONDS.toSeconds(duration)- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }
}