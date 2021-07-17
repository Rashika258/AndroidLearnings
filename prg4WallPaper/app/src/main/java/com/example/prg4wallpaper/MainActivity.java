package com.example.prg4wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button btnChangeWallPaper;
    boolean running;
    int[] imagesArray=new int[]{
            R.drawable.i1,
            R.drawable.i2,
            R.drawable.i3,
            R.drawable.i4,
            R.drawable.i5,
            R.drawable.i6,
            R.drawable.i7,
            R.drawable.i8,
            R.drawable.i9,
            R.drawable.i10,
            R.drawable.i11,
            R.drawable.i12,
            R.drawable.i13,
            R.drawable.i14,
            R.drawable.i15,
            R.drawable.i16,
            R.drawable.i17,
            R.drawable.i18,
            R.drawable.i19,
            R.drawable.i20
    };
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChangeWallPaper=(Button)findViewById(R.id.btn_start_change_wallpaper);
        btnChangeWallPaper.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View v) {
        if(!running){
            new Timer().schedule(new MyTimer(),0,3000);
            running=true;
        }
    }
    class MyTimer extends TimerTask{

        @Override
        public void run() {
            try{
                WallpaperManager wallpaperManager=WallpaperManager.getInstance(getBaseContext());
                if(i==12)
                    i=1;
                if(i==11)
                    i=2;
                if(i==10)
                    i=3;
                if(i==9)
                    i=4;
                if(i==8)
                    i=5;
                if(i==7)
                    i=6;
                if(i==6)
                    i=7;
                if(i==5)
                    i=8;
                if(i==4)
                    i=9;
                if(i==3)
                    i=10;
                wallpaperManager.setBitmap(BitmapFactory.decodeResource(getResources(),imagesArray[i]));
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}