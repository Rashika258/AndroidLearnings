package com.example.smsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG="SmsBroadcastReceiver";
    String msg,phoneNo;

    @Override
    public void onReceive(Context context, Intent intent) {
      //retrive the general action to be performed and display on log
        Log.i(TAG, "Intent Received : "+intent.getAction());
        if(intent.getAction()==SMS_RECEIVED) {
            //retrive a map of extended data from the intent
            Bundle dataBundle=intent.getExtras();
            if(dataBundle!=null) {
                //creating PDU(Protocol Data Unit) object which is a protocol for transferring message
                Object[] mypdu=(Object[])dataBundle.get("pdus");
                final SmsMessage[] message=new SmsMessage[mypdu.length];
              for(int i=0;i< mypdu.length;i++) {
                  //for build versions >= API level 23
                  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                      String format=dataBundle.getString("format");
                      //From PDU we get all object and SmsMessage object and using following line of code
                      message[i]=SmsMessage.createFromPdu((byte[]) mypdu[i]);
                  }
                  msg=message[i].getMessageBody();
                  phoneNo=message[i].getOriginatingAddress();


              }
                Toast.makeText(context, "Message : "+msg+"\nNumber : "+phoneNo,Toast.LENGTH_LONG).show();
            }
        }
    }
}