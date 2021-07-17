package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout L1,L2;
    TextView tv,textView;

    Animation DownToTop,Fade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L1=(LinearLayout)findViewById(R.id.l1);
        L2=(LinearLayout)findViewById(R.id.l2);

        tv=(TextView)findViewById(R.id.tag);
        textView=(TextView)findViewById(R.id.app_title);
//
//        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Handle-Regular.ttf");
//        textView.setTypeface(type);

        DownToTop= AnimationUtils.loadAnimation(this,R.anim.downtotop);
        Fade=AnimationUtils.loadAnimation(this, R.anim.fade);

        L2.setAnimation(DownToTop);
        tv.setAnimation(Fade);

        Intent i=new Intent(MainActivity.this, HomeActivity.class);

        Thread thread=new Thread() {
            @Override
            public void run() {
                try {
                    // to stay on splash screen for 5 seconds
                    sleep(5000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        thread.start();
    }
}