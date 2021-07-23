package com.example.dialerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bdot, bhash, call, save, bdel;
    EditText phno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b0 = (Button) findViewById(R.id.b0);
        bdot = (Button) findViewById(R.id.bdot);
        bhash = (Button) findViewById(R.id.bhash);
        call = (Button) findViewById(R.id.call);
        save = (Button) findViewById(R.id.save);
        bdel = (Button) findViewById(R.id.bdel);
        phno = (EditText) findViewById(R.id.etphno);
        phno.setText("");
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        bdot.setOnClickListener(this);
        bdel.setOnClickListener(this);
        bhash.setOnClickListener(this);
        call.setOnClickListener(this);
        save.setOnClickListener(this);
    }
    @Override public void onClick(View v) {
        if (v.equals(b1))
            phno.append("1");
        else if (v.equals(b2))
            phno.append("2");
        else if (v.equals(b3))
            phno.append("3");
        else if (v.equals(b4))
            phno.append("4");
        else if (v.equals(b5))
            phno.append("5");
        else if (v.equals(b6))
            phno.append("6");
        else if (v.equals(b7))
            phno.append("7");
        else if (v.equals(b8))
            phno.append("8");
        else if (v.equals(b9))
            phno.append("9");
        else if (v.equals(b0))
            phno.append("0");
        else if (v.equals(bdot))
            phno.append("*");
        else if (v.equals(bhash))
            phno.append("#");
        else if (v.equals(save)) {
            Intent i = new Intent(ContactsContract.Intents.Insert.ACTION);
            i.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            i.putExtra(ContactsContract.Intents.Insert.NAME, "Unknown");
            i.putExtra(ContactsContract.Intents.Insert.PHONE,
                    phno.getText().toString());
            startActivity(i);
        }
        else if(v.equals(bdel))
        {
            String data=phno.getText().toString();
            if(data.length()>0)
            {
                phno.setText(data.substring(0,data.length()-1));
            }
            else {
                phno.setText("");
            }
        }
        call.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view) {
                                        String data=phno.getText().toString();
                                        Intent callintent=new Intent(Intent.ACTION_DIAL);
                                        callintent.setData(Uri.parse("tel:"+data));
                                        startActivity(callintent);
                                    }
                                }
        ); }
}
