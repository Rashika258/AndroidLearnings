package com.example.smsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //in mainactivity we write code for asking permission
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if the permission isn't granted
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED) {
            //if the permission isn't granted then check if the user has denied the permission
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                //do nothing as user has denied

            } else {
                //pop up will appear asking for required permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
    }//onCreate
    //after getting the result of permission requests the result will be passed through this method
    //@Override
    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults) {
        //will check the requestCode
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS:
            {
                //check whether the length of the grantResults is greater than 0 and is equal to PERMISSION_GRANTED
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    //now broadcast recever will work in background
                    Toast.makeText(this, "Thank you for permitting", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this, "Well I can't do anything until you permit me", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}