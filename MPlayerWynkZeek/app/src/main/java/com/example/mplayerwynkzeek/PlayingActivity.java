package com.example.mplayerwynkzeek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.File;
import java.util.ArrayList;

import static com.example.mplayerwynkzeek.R.color.purple_700;

public class PlayingActivity extends AppCompatActivity {

    Button btnPlay,btnNext, btnPrevious, btnFastBackward,btnFastForward;
    TextView txtSongName, txtSongStart, txtSongEnd;
    SeekBar seekMusicBar;
    BarVisualizer barVisualizer;

    ImageView imageView;
    String songName;
    public static final String EXTRA_NAME="song_name";
    static MediaPlayer mediaPlayer;
    int position;
    ArrayList<File> mySongs;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if(barVisualizer != null) {
            barVisualizer.release();
        }
        super.onDestroy();
    }

    Thread updateSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

//        getSupportActionBar().setTitle("WynkZeek");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnPrevious=findViewById(R.id.btnPrevious);
        btnNext=findViewById(R.id.btnPrevious);
        btnFastBackward=findViewById(R.id.btnFastBackward);
        btnFastForward=findViewById(R.id.btnFastForward);
        btnPlay=findViewById(R.id.btnPlay);

        txtSongName=findViewById(R.id.textPlaySongName11);
//        txtSongName=findViewById(R.id.txtPlay);
        txtSongStart=findViewById(R.id.txtSongStart);
        txtSongEnd=findViewById(R.id.txtSongEnd);

        seekMusicBar=findViewById(R.id.seekBar);
        barVisualizer=findViewById(R.id.wave);

        imageView=findViewById(R.id.imgView);

        //starts playing by default
        if(mediaPlayer != null) {
            //Successful invoke of this method in a valid state transfers the object to the Started state. Calling this method in an invalid state transfers the object to the Error state.
            mediaPlayer.start();
            //After release(), the object is no longer available.
            mediaPlayer.release();
        }

        Intent intent=getIntent();
        //to get extra data that is passed along with intent bundle is used
        Bundle bundle=intent.getExtras();

        //songs, songname and pos are received from the main activity
        mySongs=(ArrayList)bundle.getParcelableArrayList("songs");

        String sName=intent.getStringExtra("songname");
        //position is integer value
        position=bundle.getInt("pos",0);
        //txtSongName is txtView
        txtSongName.setSelected(true);
        //parses the given encoded uri string
        Uri uri= Uri.parse(mySongs.get(position).toString());
        //songName is string
        songName=mySongs.get(position).getName();
        txtSongName.setText(songName);

        mediaPlayer=MediaPlayer.create(getApplicationContext(),uri);
        mediaPlayer.start();

        //updateSeekBar is the thread to update
        updateSeekBar=new Thread() {
            @Override
            public void run() {
                int totalDuration=mediaPlayer.getDuration();
                int currentPosition=0;

                while (currentPosition<totalDuration) {
                    try{
                        sleep(500);
                        currentPosition=mediaPlayer.getCurrentPosition();
                        seekMusicBar.setProgress(currentPosition);
                    }
                    catch (IllegalStateException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                super.run();
            }
        };

        seekMusicBar.setMax(mediaPlayer.getDuration());
        //start the thread
        updateSeekBar.start();

        seekMusicBar.getProgressDrawable().setColorFilter(getResources().getColor(purple_700), PorterDuff.Mode.MULTIPLY);
        seekMusicBar.getThumb().setColorFilter(getResources().getColor(purple_700), PorterDuff.Mode.SRC_IN);

        seekMusicBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });


        String endTime=createTime(mediaPlayer.getDuration());
        txtSongEnd.setText(endTime);

        final Handler handler=new Handler();
        final int delay=1000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentTime=createTime(mediaPlayer.getCurrentPosition());
                txtSongStart.setText(currentTime);
                handler.postDelayed(this,delay);
            }
        },delay);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    btnPlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayer.pause();
                }
                else {
                    btnPlay.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                    mediaPlayer.start();

                    //changes the position of object
                    TranslateAnimation moveAnim=new TranslateAnimation(-25,25,-25,25);
                    moveAnim.setInterpolator(new AccelerateInterpolator());
                    moveAnim.setDuration(600);
                    moveAnim.setFillEnabled(true);
                    moveAnim.setFillAfter(true);
                    moveAnim.setRepeatMode(Animation.REVERSE);
                    moveAnim.setRepeatCount(1);
                    imageView.startAnimation(moveAnim);
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //when one song is completed the next song will play
                btnNext.performClick();
            }
        });

        //Get the audio session id for the player used by this VideoView.
        int audioSessionId=mediaPlayer.getAudioSessionId();
        if(audioSessionId != -1) {
            barVisualizer.setAudioSessionId(audioSessionId);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.stop();
                mediaPlayer.release();
//                int n=position+1;
//                n=n%mySongs.size();
                position= (position+1)% mySongs.size();
                /* position=n; */
                Uri uri=Uri.parse(mySongs.get(position).toString());
                mediaPlayer=MediaPlayer.create(getApplicationContext(),uri);
                songName=mySongs.get(position).getName();
                txtSongName.setText(songName);
                mediaPlayer.start();

                startAnimation(imageView, 360f);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position=((position-1)<0)?(mySongs.size()-1):position-1;
                Uri uri=Uri.parse(mySongs.get(position).toString());
                mediaPlayer=MediaPlayer.create(getApplicationContext(),uri);
                songName=mySongs.get(position).getName();
                txtSongName.setText(songName);
                mediaPlayer.start();

                startAnimation(imageView,-360f);
            }
        });

        btnFastForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
                }
            }
        });

        btnFastBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-10000);
                }
            }
        });

    }

    public void startAnimation(View view, Float degree) {
        //subclass of ValueAnimator, provides support for animating properties on target objects. The constructors of this class take parameters to define the target object that will be animated as well as the name of the property that will be animated. Appropriate set/get functions are then determined internally and the animation will call these functions as necessary to animate the property.
        //parameters-target, name, value
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(imageView,"rotation",0f,degree);
        objectAnimator.setDuration(1000);

        //This class plays a set of Animator objects in the specified order. Animations can be set up to play together, in sequence, or after a specified delay.
        //playTogether Sets up this AnimatorSet to play all of the supplied animations at the same time.
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(objectAnimator);
        animatorSet.start();
    }

    public String createTime(int duration) {
        String time="";
        int min=duration/1000/60;
        int sec=duration/1000%60;

        time=time+min+":";
        //two digits
        if(sec<10) {
            time+="0";
        }
        time+=sec;
        return time;
    }
}