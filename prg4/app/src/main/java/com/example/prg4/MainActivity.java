
package com.example.prg4;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void onClick(View v) {
        if(!running){
            new Timer().schedule(new MyTimer(),0,3000);
            running=true;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    class MyTimer extends TimerTask {

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