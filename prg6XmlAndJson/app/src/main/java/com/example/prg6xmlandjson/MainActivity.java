package com.example.prg6xmlandjson;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bjson,bxml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bjson=(Button)findViewById(R.id.bjson);
        bxml=(Button)findViewById(R.id.bxml);
        bjson.setOnClickListener(this);
        bxml.setOnClickListener(this);
    } @Override
    public void onClick(View v) {
        if(v.equals(bjson))
        {
            Intent i=new Intent(this,ViewActivity.class);
            i.putExtra("mode",1);
            startActivity(i);
        }
        else if(v.equals(bxml))
        {
            Intent it=new Intent(this,ViewActivity.class);
            it.putExtra("mode",2);
            startActivity(it);
        }
    }
}